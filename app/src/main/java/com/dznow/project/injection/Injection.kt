package com.dznow.project.injection

import com.dznow.project.data.ArticlesRepositoryImp

object Injection {
    val getArticlesRepository = ArticlesRepositoryImp.instance
}