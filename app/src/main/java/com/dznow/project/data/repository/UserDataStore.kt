package com.dznow.project.data.repository

import com.dznow.project.presentation.model.Article
import io.reactivex.Observable

interface UserDataStore {
    fun authenticate(): Observable<Boolean>
    fun saveUser(){}
    fun saveArticle(article: Article): Observable<Boolean>
    fun getSavedArticles(): Observable<List<Article>>
}