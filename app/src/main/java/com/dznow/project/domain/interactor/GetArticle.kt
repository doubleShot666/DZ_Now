package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.Article
import io.reactivex.Observable

/**
 * Cette class implémente [UseCase] et créer un observable sur le résultat du cas d'utilisation
 * de récupération d'un article.
 * @see UseCase
 * @see ArticlesRepository
 */
class GetArticle(private val articlesRepository: ArticlesRepository) : UseCase<Article, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<Article> {
        return articlesRepository.getArticle(params[0] as String)
    }
}
