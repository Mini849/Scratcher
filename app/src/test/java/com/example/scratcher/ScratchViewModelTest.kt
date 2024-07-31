import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.scratcher.ui.ui.ScratchViewModel
import com.example.scratcher.ui.ui.ScratchViewModel.Companion.SCRATCH_ACTION
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ScratchViewModelTest {

    @get:org.junit.Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var workManager: WorkManager
    private lateinit var scratchViewModel: ScratchViewModel
    private lateinit var workResult: MutableLiveData<WorkInfo>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        workResult = MutableLiveData()
        `when`(workManager.getWorkInfoByIdLiveData(any())).thenReturn(workResult)
        scratchViewModel = ScratchViewModel(context, workManager)
    }

    @Test
    fun testGenerateUid(): Unit = runTest {
        scratchViewModel.generateUid()
        assert(scratchViewModel.uuid.value != null)
    }

    @Test
    fun testActivateCard() {
        val workInfo = mock(WorkInfo::class.java)
        `when`(workInfo.state).thenReturn(WorkInfo.State.SUCCEEDED)
        val observer = mock(Observer::class.java) as Observer<WorkInfo>
        scratchViewModel.workResult?.observeForever(observer)

        scratchViewModel.activateCard()

        workResult.postValue(workInfo)

        verify(workManager).enqueueUniqueWork(SCRATCH_ACTION, ExistingWorkPolicy.REPLACE, scratchViewModel.workRequest)
        verify(observer).onChanged(workInfo)
    }
}