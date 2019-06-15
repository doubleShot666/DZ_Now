package com.dznow.project.presentation.presenter

import com.dznow.project.domain.interactor.GetThemesList
import com.dznow.project.domain.interactor.SavePrefThemes
import com.dznow.project.injection.Injection
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ThemeSelectionView
import com.dznow.project.presentation.model.Theme
import io.reactivex.observers.DisposableObserver

class ThemeSelectionPresenter(themeSelectionView: ThemeSelectionView) : BasePresenter<ThemeSelectionView>(themeSelectionView) {

    val getThemes = GetThemesList(Injection.getArticlesRepository)
    val savePrefThemes = SavePrefThemes(Injection.getArticlesRepository)

    fun getSelectedThemes() {

        getThemes.execute(object : DisposableObserver<List<Pair<Boolean, Theme>>>(){
            override fun onComplete() {

            }

            override fun onNext(t: List<Pair<Boolean, Theme>>) {
                view.initThemes(t)
            }

            override fun onError(e: Throwable) {

            }
        })
    }

    fun setSelectedThemes(selectedThemes: ArrayList<String>) {
        savePrefThemes.execute(object : DisposableObserver<Boolean>(){
            override fun onComplete() {

            }

            override fun onNext(t: Boolean) {
                if(t)
                    view.finishView()
            }

            override fun onError(e: Throwable) {

            }
        },params = * kotlin.arrayOf(selectedThemes))
    }
}