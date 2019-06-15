package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.ArticlesRepository
import com.dznow.project.presentation.model.Article
import io.reactivex.Observable

/**
 * Cette class implémente [UseCase] et créer un observable sur le résultat du cas d'utilisation
 * de récupération d'une list d'article selon les pramêtres de sélection.
 * @see UseCase
 * @see ArticlesRepository
 */
class GetArticlesList(private val articlesRepository: ArticlesRepository) : UseCase<List<Article>, Any?>(){
    override fun buildUseCaseObservable(vararg params: Any?): Observable<List<Article>> {
        return articlesRepository.getArticlesList(params[0] as List<Pair<String,String>>)
    }
}
