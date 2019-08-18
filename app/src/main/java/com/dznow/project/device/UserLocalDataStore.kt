package com.dznow.project.device

import com.dznow.project.DzNowApp
import com.dznow.project.data.entity.UserEntity
import com.dznow.project.data.repository.UserDataStore
import com.dznow.project.presentation.model.Article
import io.reactivex.Observable

class UserLocalDataStore : UserDataStore{

    val dao = DzNowDatabase.getDatabase(DzNowApp.applicationContext())!!.userDao()

    override fun authenticate(): Observable<Boolean> {
        return Observable.create { e ->
            if(DzNowApp.prefs!!.user_id != null){
                val user : UserEntity? =  dao.getUser(DzNowApp.prefs!!.user_id!!)
                e.onNext(user != null)
            }else{
                e.onNext(false)
            }
        }
    }

    override fun saveUser() {
        dao.insertUser(UserEntity(DzNowApp.prefs!!.user_id!!))
    }

    override fun getSavedArticles(): Observable<List<Article>> {
        return dao.getSavedArticles().filter { it.isNotEmpty() }.toObservable()
    }

    override fun saveArticle(article: Article): Observable<Boolean> {
        return Observable.create { e ->
            dao.insertSavedArticle(article)
            e.onNext(true)
        }
    }

}