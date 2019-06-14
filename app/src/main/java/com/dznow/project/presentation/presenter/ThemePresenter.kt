package com.dznow.project.presentation.presenter

import com.dznow.project.data.ArticlesRepositoryImp
import com.dznow.project.domain.interactor.GetThemesList
import com.dznow.project.injection.Injection
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ThemeView
import com.dznow.project.presentation.model.Theme
import io.reactivex.observers.DisposableObserver

class ThemePresenter(themeView: ThemeView) : BasePresenter<ThemeView>(themeView) {

    val getThemes = GetThemesList(Injection.getArticlesRepository)

    fun getThemes() {
        getThemes.execute(object : DisposableObserver<List<Pair<Boolean, Theme>>>(){
            override fun onComplete() {

            }

            override fun onNext(t: List<Pair<Boolean, Theme>>) {
                val themesList = ArrayList<Theme>()
                for(e in t)
                    themesList.add(e.second)

                view.initThemes(themesList)
            }

            override fun onError(e: Throwable) {

            }
        })
    }


}