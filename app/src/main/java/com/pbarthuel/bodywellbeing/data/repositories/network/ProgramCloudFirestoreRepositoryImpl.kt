package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.data.model.program.WsProgramDetail
import com.pbarthuel.bodywellbeing.data.vendors.network.program.ProgramCloudFirestoreDao
import com.pbarthuel.bodywellbeing.data.vendors.network.program.UserProgramCloudFirestoreDao
import com.pbarthuel.bodywellbeing.domain.model.program.ProgramDetail
import com.pbarthuel.bodywellbeing.domain.repositories.network.ProgramCloudFirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import org.joda.time.DateTime

class ProgramCloudFirestoreRepositoryImpl @Inject constructor(
    private val programCloudFirestoreDao: ProgramCloudFirestoreDao,
    private val userProgramCloudFirestoreDao: UserProgramCloudFirestoreDao
) : ProgramCloudFirestoreRepository {

    override fun getAllPrograms(): Flow<List<WsProgramDetail>> = programCloudFirestoreDao.getAllPrograms()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getProgramById(programId: String): Flow<ProgramDetail> =
        programCloudFirestoreDao.getProgramById(programId = programId).mapLatest { it.toDomain() }

    override fun createProgram(program: WsProgramDetail) = programCloudFirestoreDao.createProgram(program)

    override fun joinProgram(userId: String, program: ProgramDetail) {
        userProgramCloudFirestoreDao.joinProgram(
            userId = userId,
            joinedProgram = program.copy(startDate = DateTime.now().millis).toWs()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getJoinedProgram(userId: String): Flow<ProgramDetail?> =
        userProgramCloudFirestoreDao.getJoinedProgram(userId = userId).mapLatest { it?.toDomain() }

    override fun leaveProgram(userId: String) =
        userProgramCloudFirestoreDao.leaveProgram(userId = userId)
}