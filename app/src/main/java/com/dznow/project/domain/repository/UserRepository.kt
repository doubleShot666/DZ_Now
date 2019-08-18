package com.dznow.project.domain.repository

import io.reactivex.Observable

interface UserRepository {
    fun authenticate(): Observable<Boolean>
}