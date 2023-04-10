package ru.dadetail.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dadetail.data.api.PositionApi

class PositionRepository(private val positionApi: PositionApi) {

    suspend fun getPositions() = withContext(Dispatchers.IO) {
        positionApi.getPositions()
    }
}