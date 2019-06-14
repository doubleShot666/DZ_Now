package com.dznow.project.presentation.presenter

import com.dznow.project.domain.interactor.ChangeLanguage
import com.dznow.project.domain.interactor.GetLanguage
import com.dznow.project.domain.repository.LanguageAccessor
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.LanguageView
import com.dznow.project.presentation.utils.LanguageAccessorImp
import io.reactivex.observers.DisposableObserver

class LanguagePresenter(languageView: LanguageView ,val getLanguage: GetLanguage , val changeLanguage: ChangeLanguage) : BasePresenter<LanguageView>(languageView){


    fun getLanguage() {
        getLanguage.execute(object : DisposableObserver<String>(){
            override fun onComplete() {

            }

            override fun onNext(language: String) {
                if(language == "AR")
                    view.selectSwitch()
            }

            override fun onError(e: Throwable) {

            }

        })
    }

    fun setLanguage(language: String) {
        changeLanguage.execute(object : DisposableObserver<Boolean>() {
            override fun onComplete() {
                view.finishView()
            }

            override fun onNext(t: Boolean) {
                if(t)
                    view.finishView()
                else
                    view.showError()
            }

            override fun onError(e: Throwable) {
                view.showError()
            }

        }, params = *arrayOf(language))
    }

}