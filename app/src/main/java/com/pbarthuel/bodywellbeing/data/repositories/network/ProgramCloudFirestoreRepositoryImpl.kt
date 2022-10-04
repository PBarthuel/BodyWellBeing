package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import com.pbarthuel.bodywellbeing.data.vendors.network.ProgramCloudFirestoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.ProgramCloudFirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ProgramCloudFirestoreRepositoryImpl @Inject constructor(
    private val programCloudFirestoreDao: ProgramCloudFirestoreDao,
) : ProgramCloudFirestoreRepository {

    override fun getAllPrograms(): Flow<List<WsProgram>> = programCloudFirestoreDao.getAllPrograms()

    override fun createProgram(program: WsProgram) = programCloudFirestoreDao.createProgram(program)
}