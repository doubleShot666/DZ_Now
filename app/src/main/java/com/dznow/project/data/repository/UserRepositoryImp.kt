package com.dznow.project.data.repository

import com.dznow.project.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class UserRepositoryImp(val userDataStoreFactory: UserDataStoreFactory) : UserRepository {

    override fun authenticate(): Observable<Boolean> {
        return Observable.combineLatest(userDataStoreFactory.create(false).authenticate().doOnNext {
            if(it){
                initLocalUser()
            }
        }, userDataStoreFactory.create(true).authenticate(),
            BiFunction { res1, res2 ->
                res1 || res2
            })
    }

    private fun initLocalUser(){
        userDataStoreFactory.create(true).saveUser()
        val subscribe = userDataStoreFactory.create(false).getSavedArticles().subscribe {
            for(article in it)
                userDataStoreFactory.create(true).saveArticle(article).subscribe {
                    println("done")
                }
        }
    }


}