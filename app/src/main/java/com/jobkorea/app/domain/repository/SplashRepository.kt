package com.jobkorea.app.domain.repository

import com.jobkorea.app.domain.models.ResponseAppId
import com.jobkorea.app.domain.models.ResponseSplashAdvertise

abstract class SplashRepository {

    abstract suspend fun requestSplashAdvertise() : ResponseSplashAdvertise

    abstract suspend fun requestAppNoCreate() : ResponseAppId



}