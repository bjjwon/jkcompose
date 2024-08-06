package com.jobkorea.app.domain.repository

import com.jobkorea.app.data.api.ApiAdvertiseService
import com.jobkorea.app.domain.models.ResponseAppId
import com.jobkorea.app.domain.models.ResponseSplashAdvertise
import com.jobkorea.app.services.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val apiServiceAdvertise: ApiAdvertiseService,
    private val dispatcherProvider: DispatcherProvider,
) : SplashRepository() {


    override suspend fun requestSplashAdvertise() : ResponseSplashAdvertise {
        return withContext(dispatcherProvider.io) {
             apiServiceAdvertise.requestSplashAdvertise()
        }
    }

    override suspend fun requestAppNoCreate(): ResponseAppId {
        return withContext(dispatcherProvider.io) {
            apiServiceAdvertise.requestAppNoCreate()
        }
    }

}