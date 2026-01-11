package marcel.uni.gamifiedplanner.util

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.collections.emptyList

// extensions to reduce all that boilerplate for writing repos

inline fun <reified T : Any> DocumentReference.observeModel(): Flow<T> = callbackFlow {
    val listener = addSnapshotListener { snapshot, error ->
        if (error != null) { close(error); return@addSnapshotListener }
        snapshot?.toObject(T::class.java)?.let { trySend(it) }
    }
    awaitClose { listener.remove() }
}

inline fun <reified T : Any> Query.observeList(): Flow<List<T>> = callbackFlow {
    val listener = addSnapshotListener { snapshot, error ->
        if (error != null) { close(error); return@addSnapshotListener }
        trySend(snapshot?.toObjects(T::class.java) ?: emptyList())
    }
    awaitClose { listener.remove() }
}

