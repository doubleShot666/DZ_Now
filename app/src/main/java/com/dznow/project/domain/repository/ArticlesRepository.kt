package com.dznow.project.domain.repository

import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.model.Theme
import io.reactivex.Observable

interface ArticlesRepository {
    fun getArticle(articleId: String): Observable<Article>
    fun getArticlesList(selectionParam: List<Pair<String, String>>): Observable<List<Article>>
    fun saveArticle(article: Article): Observable<Boolean>
    fun getThemesList(): Observable<List<Pair<Boolean, Theme>>>
    fun saveSelectedThemes(themesList: List<String>): Observable<Boolean>
}