package com.dznow.project.presentation.presenter

import com.dznow.project.domain.interactor.GetLanguage
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.SplashScreenView
import io.reactivex.observers.DisposableObserver

class SplashScreenPresenter(splashScreenView: SplashScreenView, val getLanguage: GetLanguage) :
    BasePresenter<SplashScreenView>(splashScreenView){


    fun getLanguage() {
        getLanguage.execute(object : DisposableObserver<String>(){
            override fun onComplete() {
            }

            override fun onNext(language: String) {
                view.finishView()
            }
            override fun onError(e: Throwable) {
            }

        })
    }

}