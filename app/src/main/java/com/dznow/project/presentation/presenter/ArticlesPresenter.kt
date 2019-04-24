package com.dznow.project.presentation.presenter

import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ArticlesView
import com.dznow.project.presentation.model.Article

class ArticlesPresenter(articlesView: ArticlesView) : BasePresenter<ArticlesView>(articlesView) {
    fun getTopArticles() {
        val article = Article("1","Tech",34,"1 heure","data:image/jpeg;base64,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")
        val articleList = ArrayList<Article>()
        articleList.add(article)
        articleList.add(article)
        articleList.add(article)
        view.initArticles(articleList)
    }

    fun getLatestArticles() {
        val article = Article("1","Tech",34,"1 heure","data:image/jpeg;base64,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")
        val articleList = ArrayList<Article>()
        articleList.add(article)
        articleList.add(article)
        articleList.add(article)
        articleList.add(article)
        articleList.add(article)
        articleList.add(article)
        view.initArticles(articleList)
    }


}