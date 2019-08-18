package com.dznow.project.presentation.presenter

import com.dznow.project.data.repository.UserDataStoreFactory
import com.dznow.project.data.repository.UserRepositoryImp
import com.dznow.project.domain.interactor.AuthenticateUser
import com.dznow.project.domain.interactor.GetLanguage
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.SplashScreenView
import io.reactivex.observers.DisposableObserver

class SplashScreenPresenter(splashScreenView: SplashScreenView, val getLanguage: GetLanguage) :
    BasePresenter<SplashScreenView>(splashScreenView){

    val authenticateUser = AuthenticateUser(UserRepositoryImp(UserDataStoreFactory()))

    fun getLanguage() {
        getLanguage.execute(object : DisposableObserver<String>(){
            override fun onComplete() {
            }

            override fun onNext(language: String) {

            }
            override fun onError(e: Throwable) {
            }
        })
    }

    fun authenticateUser() {
        authenticateUser.execute(object : DisposableObserver<Boolean>(){
            override fun onComplete() {

            }

            override fun onNext(t: Boolean) {
                println(t)
                if(t)
                    view.finishView()
                else
                    view.showLoginButton()
            }

            override fun onError(e: Throwable) {
                println(e)
                view.showLoginButton()
            }
        })
    }

}