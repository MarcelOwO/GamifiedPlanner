package marcel.uni.gamifiedplanner.data.cloud

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

interface FirebaseFirestoreDataSource {

    fun userTasks(uid: String) : CollectionReference
    fun userData(uid:String) : DocumentReference

    fun achievements() : CollectionReference

    fun shopItems() : CollectionReference
}