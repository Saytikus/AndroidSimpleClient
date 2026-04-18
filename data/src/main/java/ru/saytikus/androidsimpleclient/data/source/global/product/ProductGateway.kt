package ru.saytikus.androidsimpleclient.data.source.global.product

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import ru.saytikus.androidsimpleclient.domain.product.IProductGateway
import ru.saytikus.androidsimpleclient.domain.product.Product

@Single
class ProductGateway(private val productService: IProductService) : IProductGateway {

    override suspend fun getUsers(): MbResult<List<Product>> {
        val response = productService.products()

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
                        response.errorBody().toString()
                    )
                )
            )
        }

        return MbResult.Success(
            response.body()
                ?.map {
                    it.toDomain()
                }!!
        )
    }
}