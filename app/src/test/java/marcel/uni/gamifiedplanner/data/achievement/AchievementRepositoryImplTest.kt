package marcel.uni.gamifiedplanner.data.achievement


import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.mockk.slot

class AchievementRepositoryImplTest {

    private val firestore = mockk<FirebaseFirestore>()
    private val logger = mockk<AppLogger>(relaxed =true)
    private val collectionRef = mockk<CollectionReference>()

    private lateinit var repository:AchievementRepositoryImpl

    @Before
    fun setup(){
       repository = AchievementRepositoryImpl(firestore,logger)

        mockkStatic("marcel.uni.gamifiedplanner.util.FirestoreExtKt")

    }


    @Test
    fun `observeAchievements should return list of Achievement objects`() = runTest {
        val mockAchievement = Achievement(id = "1", name = "Fast Learner")
        val mockList = listOf(mockAchievement)
        val collectionRef = mockk<CollectionReference>()
        val snapshot = mockk<QuerySnapshot>()

        val listenerSlot = slot<EventListener<QuerySnapshot>>()

        every { firestore.collection(any()) } returns collectionRef

        every { collectionRef.addSnapshotListener(capture(listenerSlot)) } answers {
            listenerSlot.captured.onEvent(snapshot, null)

            mockk { every { remove() } returns Unit }
        }

        every { snapshot.toObjects(Achievement::class.java) } returns mockList

        val repo = AchievementRepositoryImpl(firestore, logger)
        val result = repo.observeAchievements("uid").first()

        assertEquals(1, result.size)
        assertEquals("Fast Learner", result[0].name)
    }


}