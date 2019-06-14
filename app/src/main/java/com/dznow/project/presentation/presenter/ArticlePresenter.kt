package com.dznow.project.presentation.presenter

import com.dznow.project.domain.interactor.GetArticle
import com.dznow.project.domain.interactor.SaveArticle
import com.dznow.project.injection.Injection
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ArticleView
import com.dznow.project.presentation.model.Article
import io.reactivex.observers.DisposableObserver

class ArticlePresenter(articleView: ArticleView) : BasePresenter<ArticleView>(articleView) {

    val getArticle = GetArticle(Injection.getArticlesRepository)
    val saveArticle = SaveArticle(Injection.getArticlesRepository)

    fun getArticle(articleId: String) {
        getArticle.execute(object: DisposableObserver<Article>(){
            override fun onComplete() {

            }

            override fun onNext(t: Article) {
                view.displayArticle(t)
            }

            override fun onError(e: Throwable) {

            }

        },params = * kotlin.arrayOf(articleId))
    }

    fun save(article: Article) {
        saveArticle.execute(object : DisposableObserver<Boolean>(){
            override fun onComplete() {

            }

            override fun onNext(t: Boolean) {

            }

            override fun onError(e: Throwable) {

            }
        },params = * kotlin.arrayOf(article))
    }
}