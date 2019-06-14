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


class GetArticlesList(private val articlesRepository: ArticlesRepository) : UseCase<List<Article>, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<List<Article>> {
        return articlesRepository.getArticlesList(params[0] as List<Pair<String,String>>)
    }
}
