package com.dznow.project.presentation.contract

import com.dznow.project.presentation.base.BaseView
import com.dznow.project.presentation.model.Article

interface ArticlesListingView : BaseView {
    fun initArticles(articleList: List<Article>)
}