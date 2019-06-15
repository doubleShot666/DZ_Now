package com.dznow.project.domain.repository

import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.model.Theme
import io.reactivex.Observable

/**
 * Cette interface permet d'accéder aux données de l'application.
 */
interface ArticlesRepository {

    /**
     * Cette fonction retourne un observable de l'article avec un id égale à [articleId].
     * @param articleId l'id de l'article
     * @return Observable<Article>
     */
    fun getArticle(articleId: String): Observable<Article>

    /**
     * Cette fonction retourne un observable d'une liste d'articles selon les pramêtres de sélection.
     * @param selectionParam une liste de pair de type de filtre et sa valeur
     * @return Observable<List<Article>>
     */
    fun getArticlesList(selectionParam: List<Pair<String, String>>): Observable<List<Article>>

    /**
     * Cette fonction ajoute un article à la liste des articles à consultation antérieur de l'utilisateur.
     * Retourne vrai si la sauvegarde est réussi, faux sinon .
     * @param article L'article à sauvegarder
     * @return Observable<Boolean>
     */
    fun saveArticle(article: Article): Observable<Boolean>

    /**
     * Cette fonction retourne un observable de la liste des thèmes et l'état de leur activation par l'utilisateur.
     * @return Observable<List<Pair<Boolean, Theme>>>
     */
    fun getThemesList(): Observable<List<Pair<Boolean, Theme>>>

    /**
     * Cette fonction sauvgarde l'état d'activation des thèmes par l'utilisateur.
     * @param themesList themes activés
     * @return Observable<Boolean> résultat de l'opération
     */
    fun saveSelectedThemes(themesList: List<String>): Observable<Boolean>
}