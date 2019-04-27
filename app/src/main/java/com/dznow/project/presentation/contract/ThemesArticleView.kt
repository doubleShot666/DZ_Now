package com.dznow.project.presentation.contract

import com.dznow.project.presentation.base.BaseView
import com.dznow.project.presentation.model.Article

interface ThemesArticleView : BaseView {
    fun initArticles(articleList: HashMap<String,List<Article>>)
}