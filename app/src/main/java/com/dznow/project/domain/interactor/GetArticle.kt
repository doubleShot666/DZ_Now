/**
 * file name : GetAutomobiliste
 *
 * Author : Sami DJOUADI
 *
 * Copyright © 2019 Trend-Tech
 */
package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.Article
import io.reactivex.Observable


class GetArticle(private val articlesRepository: ArticlesRepository) : UseCase<Article, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<Article> {
        return articlesRepository.getArticle(params[0] as String)
    }
}
