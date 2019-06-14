/**
 * file name : GetAutomobiliste
 *
 * Author : Sami DJOUADI
 *
 * Copyright © 2019 Trend-Tech
 */
package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.Theme
import io.reactivex.Observable


class GetThemesList(private val articlesRepository: ArticlesRepository) : UseCase<List<Pair<Boolean, Theme>>, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<List<Pair<Boolean, Theme>>> {
        return articlesRepository.getThemesList()
    }
}
