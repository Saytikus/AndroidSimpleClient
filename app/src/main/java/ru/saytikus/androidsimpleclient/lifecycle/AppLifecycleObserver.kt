package ru.saytikus.androidsimpleclient.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import org.koin.core.annotation.Single

@Single
class AppLifecycleObserver(

    private val dispatcher: AppLifecycleDispatcher

) : LifecycleEventObserver {

    override fun onStateChanged(
        source: LifecycleOwner,
        event: Lifecycle.Event
    ) {
        when(event) {

            Lifecycle.Event.ON_START -> dispatcher.dispatchResume()

            Lifecycle.Event.ON_STOP -> dispatcher.dispatchPause()

            else -> { /* NO-OP */ }
        }
    }
}