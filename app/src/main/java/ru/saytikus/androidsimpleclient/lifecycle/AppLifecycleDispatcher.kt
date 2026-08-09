package ru.saytikus.androidsimpleclient.lifecycle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.presentation.core.IAppLifecycleAction

@Single
class AppLifecycleDispatcher(
    private val scope: CoroutineScope,

    private val actions: List<IAppLifecycleAction>
) {

    // TODO: logger
    fun dispatchResume() = scope.launch {
        println("application resumed")
        actions.forEach { action -> runCatching { action.onAppResume() }.onFailure { println("Received exception with DISPATCH RESUME on action: ${action.name}. Exception: $it") }  }
    }

    fun dispatchPause() = scope.launch {
        println("application paused")
        actions.forEach { action -> runCatching { action.onAppPause() }.onFailure { println("Received exception with DISPATCH PAUSE on action: ${action.name}. Exception: $it") } }
    }
}