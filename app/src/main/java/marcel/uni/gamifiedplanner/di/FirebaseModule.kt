package marcel.uni.gamifiedplanner.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

class FirebaseModule {

    val firebaseModule = module {

        single { FirebaseAuth.getInstance() }
        single { FirebaseFirestore.getInstance() }
    }


}

