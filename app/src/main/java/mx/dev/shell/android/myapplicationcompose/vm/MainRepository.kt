package mx.dev.shell.android.myapplicationcompose.vm

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getData(): Flow<Result<DataBO>>
}
