package mx.mariovaldez.elashstudioapp.ktx

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList

internal fun <T> Flow<T>.observe(lifecycleOwner: LifecycleOwner, collect: (T) -> Unit): Job =
    onEach { collect(it) }.launchIn(lifecycleOwner.lifecycleScope)

suspend fun <T> Flow<List<T>>.flattenToList() =
    flatMapConcat { it.asFlow() }.toList()
