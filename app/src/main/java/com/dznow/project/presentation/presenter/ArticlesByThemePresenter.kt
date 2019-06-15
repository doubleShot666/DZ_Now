package com.dznow.project.presentation.presenter


import com.dznow.project.domain.interactor.GetThemesList
import com.dznow.project.injection.Injection
import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ArticlesBythemeView
import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.model.Theme
import io.reactivex.observers.DisposableObserver

class ArticlesByThemePresenter(themesArticleView: ArticlesBythemeView) : BasePresenter<ArticlesBythemeView>(themesArticleView) {

    val getArticlesList = GetThemesList(Injection.getArticlesRepository)

    fun getArticlesPerTheme() {

        getArticlesList.execute(ArticlesObserver())
    }

    private inner class ArticlesObserver : DisposableObserver<List<Pair<Boolean, Theme>>>() {


        override fun onComplete() {

        }

        override fun onNext(t: List<Pair<Boolean, Theme>>) {
            val hashMap = HashMap<String,List<Article>>()
            for(element in t) {
                if(element.first){
                    if (hashMap[element.second.name] == null)
                        hashMap[element.second.name] = ArrayList()
                    hashMap[element.second.name] = element.second.articles
                }
            }
            view.initArticles(hashMap)
        }

        override fun onError(e: Throwable) {

        }

    }


}