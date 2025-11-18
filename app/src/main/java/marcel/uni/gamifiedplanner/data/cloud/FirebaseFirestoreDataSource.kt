package marcel.uni.gamifiedplanner.data.cloud

import com.google.firebase.firestore.CollectionReference

interface FirebaseFirestoreDataSource {

    fun userTasks(uid: String) : CollectionReference
}