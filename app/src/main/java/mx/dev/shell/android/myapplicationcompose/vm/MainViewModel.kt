package mx.dev.shell.android.myapplicationcompose.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: MainRepository
) : ViewModel() {

    val data = MutableLiveData<DataBO>()

    fun something() {
        viewModelScope.launch(dispatcher) {
            repository.getData()
                .collect {
                    val value = it.getOrNull()
                    data.postValue(value)
                }
        }
    }
}