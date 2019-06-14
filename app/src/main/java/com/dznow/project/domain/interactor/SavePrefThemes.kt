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


class SavePrefThemes(private val articlesRepository: ArticlesRepository) : UseCase<Boolean, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<Boolean> {
        return articlesRepository.saveSelectedThemes(params[0] as List<String>)
    }
}
