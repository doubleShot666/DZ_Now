package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.Theme
import io.reactivex.Observable

/**
 * Cette class implémente [UseCase] et créer un observable sur le résultat du cas d'utilisation
 * de récupération de la liste de thèmes.
 * @see UseCase
 * @see ArticlesRepository
 */
class GetThemesList(private val articlesRepository: ArticlesRepository) : UseCase<List<Pair<Boolean, Theme>>, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<List<Pair<Boolean, Theme>>> {
        return articlesRepository.getThemesList()
    }
}
