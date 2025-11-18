package marcel.uni.gamifiedplanner.data.cloud

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestoreDataSourceImpl(
    private val store: FirebaseFirestore
) : FirebaseFirestoreDataSource {
    override fun userTasks(uid: String): CollectionReference {
        return store
            .collection("users")
            .document(uid)
            .collection("tasks")
    }


}