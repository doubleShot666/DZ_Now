package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.UserRepository
import io.reactivex.Observable

class AuthenticateUser(private val userRepository: UserRepository) : UseCase<Boolean, Any?>() {

    override fun buildUseCaseObservable(vararg params: Any?): Observable<Boolean> {
        return userRepository.authenticate()
    }
}