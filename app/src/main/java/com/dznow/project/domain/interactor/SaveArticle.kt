package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.Article
import io.reactivex.Observable

/**
 * Cette class implémente [UseCase] et créer un observable sur le résultat du cas d'utilisation
 * de sauvegarde d'un article pour une lecture ultérieur.
 * @see UseCase
 * @see ArticlesRepository
 */
class SaveArticle(private val articlesRepository: ArticlesRepository) : UseCase<Boolean, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<Boolean> {
        return articlesRepository.saveArticle(params[0] as Article)
    }
}
