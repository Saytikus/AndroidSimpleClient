package ru.saytikus.androidsimpleclient.data.product

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.source.remote.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import ru.saytikus.androidsimpleclient.domain.product.IProductGateway
import ru.saytikus.androidsimpleclient.domain.product.Product

@Single
class ProductGateway(
    private val serviceProvider: IRetrofitProvider

) : IProductGateway {

    private val _service: IProductService
        get() = serviceProvider.retrofit().create(IProductService::class.java)

    override suspend fun getUsers(): MbResult<List<Product>> {
        val response = _service.products()

        if(!response.isSuccessful) {
            // TODO log
            println("GET USERS ERROR!!! ${DomainError.GatewayError.RequestError(
                response.code(),
                response.errorBody().toString()
            )}")

            return MbResult.Failure(
                MbError(
                    DomainError.GatewayError.RequestError(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            )
        }

        // todo fix !!
        return MbResult.Success(
            response.body()
                ?.map {
                    it.toDomain()
                }!!
        )
    }
}