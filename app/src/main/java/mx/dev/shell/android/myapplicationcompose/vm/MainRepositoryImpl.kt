package mx.dev.shell.android.myapplicationcompose.vm

import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl : MainRepository {
    override suspend fun getData(): Flow<Result<DataBO>> {
        TODO("Not yet implemented")
    }
}