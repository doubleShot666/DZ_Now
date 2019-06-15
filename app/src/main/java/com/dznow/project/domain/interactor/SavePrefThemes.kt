package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.ArticlesRepository
import io.reactivex.Observable

/**
 * Cette class implémente [UseCase] et créer un observable sur le résultat du cas d'utilisation
 * de sauvagrde de létat d'activation des thèmes.
 * @see UseCase
 * @see ArticlesRepository
 */
class SavePrefThemes(private val articlesRepository: ArticlesRepository) : UseCase<Boolean, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<Boolean> {
        return articlesRepository.saveSelectedThemes(params[0] as List<String>)
    }
}
