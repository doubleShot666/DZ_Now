package com.dznow.project.presentation.presenter

import com.dznow.project.data.ArticlesRepositoryImp
import com.dznow.project.domain.interactor.GetArticlesList
import com.dznow.project.injection.Injection
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ArticlesView
import com.dznow.project.presentation.model.Article
import io.reactivex.observers.DisposableObserver

class ArticlesPresenter(articlesView: ArticlesView) : BasePresenter<ArticlesView>(articlesView) {

    val getArticlesList = GetArticlesList(Injection.getArticlesRepository)

    fun getTopArticles() {
        getArticlesList.execute(ArticlesObserver(),params = * kotlin.arrayOf(listOf(Pair("filter","top"))))
    }

    fun getLatestArticles() {
        getArticlesList.execute(ArticlesObserver(),params = * kotlin.arrayOf(listOf(Pair("filter","latest"))))
    }

    fun getLocalArticles() {
        getArticlesList.execute(ArticlesObserver(),params = * kotlin.arrayOf(listOf(Pair("filter","local"))))
    }

    fun getSavedArticles() {
        getArticlesList.execute(ArticlesObserver(),params = * kotlin.arrayOf(listOf(Pair("filter","saved"))))
    }

    fun getThemeArticles(theme: String){
        getArticlesList.execute(ArticlesObserver(),params = * kotlin.arrayOf(listOf(Pair("theme",theme))))
    }

    private inner class ArticlesObserver : DisposableObserver<List<Article>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Article>) {
            view.initArticles(t)
        }

        override fun onError(e: Throwable) {

        }

    }

}