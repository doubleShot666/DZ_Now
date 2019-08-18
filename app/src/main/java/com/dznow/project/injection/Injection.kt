package com.dznow.project.injection

import com.dznow.project.data.repository.ArticlesRepositoryImp

object Injection {
    val getArticlesRepository = ArticlesRepositoryImp.instance
}