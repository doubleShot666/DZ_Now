package com.dznow.project.presentation.presenter

import com.dznow.project.data.ArticlesRepositoryImp
import com.dznow.project.domain.interactor.GetArticlesList
import com.dznow.project.injection.Injection
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ThemesArticleView
import com.dznow.project.presentation.model.Article
import io.reactivex.observers.DisposableObserver

class ThemesArticlesPresenter(themesArticleView: ThemesArticleView) : BasePresenter<ThemesArticleView>(themesArticleView) {

    val getArticlesList = GetArticlesList(Injection.getArticlesRepository)

    fun getArticlesPerTheme() {

        getArticlesList.execute(ArticlesObserver(),params = * kotlin.arrayOf(listOf(Pair("theme","Culture"),
            Pair("theme","Sport"))))
    }

    private inner class ArticlesObserver : DisposableObserver<List<Article>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Article>) {
            val hashMap = HashMap<String,List<Article>>()
            for(e in t){
                if(hashMap["Sport"] == null)
                    hashMap["Sport"] = ArrayList()
                (hashMap["Sport"] as ArrayList).add(e)

            }
            view.initArticles(hashMap)
        }

        override fun onError(e: Throwable) {

        }

    }


}