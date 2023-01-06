package metropolis.xtracted.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

abstract class ControllerBase<S, A: Action> (initialState: S){

    var state by mutableStateOf(initialState, policy = neverEqualPolicy())
        protected set

    protected lateinit var uiScope: CoroutineScope

    protected val defaultScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    protected val ioScope      = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val actionChannel = Channel<A>(Channel.UNLIMITED)

    fun initializeUiScope(scope : CoroutineScope){
        uiScope = scope

        uiScope.launch { // das Recompose sollte immer im uiScope passieren
            while (true) {
                val action = actionChannel.receive() // wartet auf die nächste auszuführende Action

                if (action.enabled) {
                    val deferredNewState = ioScope.async {
                        executeAction(action)  // liefert den neuen State, der natürlich asynchron berechnet wird
                    }
                    state = deferredNewState.await() // warten bis der neue State bekannt ist (ohne UI zu blockieren)
                }
            }
        }
    }

    fun triggerAction(action: A) {
        defaultScope.launch {
            actionChannel.send(action)  // die Action wird nur in den Channel gestellt. Dadurch wird die Reihenfolgetreue sichergestellt.
        }
    }

    abstract fun executeAction(action: A) : S
}