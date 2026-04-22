package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterUserAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterUserCommand

@KoinViewModel
class RegistrationViewModel(

    private val registerUserCase:
    IInputBoundary<MbResult<A1RegisterUserAnswer>, C1RegisterUserCommand>

) : ViewModel() {

    init {
        println("VMMVMVMVMVMVMMVMVMVMMVVMVMVMVMVMVMVMVMV")
        viewModelScope.launch {
            registerUserCase(C1RegisterUserCommand("UserTemp2", "govno2@gmail.com", "Vasya123456", "Vasyan2"))
        }

    }
}