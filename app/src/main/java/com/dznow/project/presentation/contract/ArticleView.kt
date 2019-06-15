package com.dznow.project.presentation.contract

import com.dznow.project.presentation.base.BaseView
import com.dznow.project.presentation.model.Article

interface ArticleView : BaseView {
    fun displayArticle(article: Article)
    fun showMessage(message: String)
}