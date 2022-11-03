package mx.dev.shell.android.myapplicationcompose.vm

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import mx.dev.shell.android.myapplicationcompose.BaseUnitTest
import mx.dev.shell.android.myapplicationcompose.captureValues
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MainViewModelShould: BaseUnitTest() {

    private val expectedData = DataBO("name")
    private val repository = mock(MainRepository::class.java)

    @Test
    fun getDataFromRepository() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val vm = MainViewModel(dispatcher, repository)
        vm.something()
        advanceUntilIdle()
        verify(repository, times(1)).getData()
    }

    @Test
    fun emitDataFromRepository() = runTest {
        `when`(repository.getData()).thenReturn(
            flow { emit(Result.success(expectedData)) }
        )
        val dispatcher = StandardTestDispatcher(testScheduler)
        val vm = MainViewModel(dispatcher, repository)
        vm.data.captureValues {
            vm.something()
            advanceUntilIdle()
            assertEquals(expectedData, this.values.last())
        }
    }
}