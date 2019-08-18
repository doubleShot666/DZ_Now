package com.dznow.project.data.net

import com.dznow.project.DzNowApp
import com.dznow.project.data.repository.UserDataStore
import com.dznow.project.presentation.model.Article
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import io.reactivex.Observable
import java.util.ArrayList

class UserRemoteDataStore : UserDataStore {

    private val userRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
    private val articlesRef = FirebaseDatabase.getInstance().getReference("savedArticles")

    override fun authenticate(): Observable<Boolean> {
        return Observable.create { e ->
            val user = FirebaseAuth.getInstance().currentUser
            when (user) {
                null -> e.onNext(false)
                else -> {
                    DzNowApp.prefs!!.user_id = getUserID()
                    e.onNext(true)
                }
            }
        }
    }

    override fun getSavedArticles(): Observable<List<Article>> {
        return Observable.create { e ->
            val userId = getUserID()
            articlesRef.child(userId!!).addListenerForSingleValueEvent(
                object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {}
                    override fun onDataChange(p0: DataSnapshot) {
                        val articleArray = ArrayList<ArticleEntity>()
                        for(d in p0.children){
                            articleArray.add(d.getValue(ArticleEntity::class.java)!!)
                        }
                        e.onNext(articleArray.map { it -> Article(
                            it.id,
                            it.title,
                            it.readTime,
                            it.source,
                            it.sourceImg,
                            it.elapsedTime,
                            it.image,
                            it.url
                        ) })
                    }
                })
        }
    }

    override fun saveArticle(article: Article): Observable<Boolean> {
        return Observable.create { e ->
            val userId = getUserID()
            val newEventRef = articlesRef.child(userId!!).child(article.id)
            newEventRef.setValue(article)
            e.onNext(true)
        }
    }


    private fun getUserID(): String? {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }



    internal class User() {

        /*lateinit var email : String
        lateinit var birthday: String
        var gender: Int = 0
        lateinit var profession: String
        lateinit var codePostalDom : String

        constructor(email : String,
                    birthday: String,
                    gender: Int,
                    profession: String,
                    codePostalDom : String) : this() {
            this.birthday = birthday
            this.email = email
            this.gender = gender
            this.profession = profession
            this.codePostalDom = codePostalDom
        }*/
    }

    internal class ArticleEntity(){
        lateinit var id: String
        lateinit var title: String
        lateinit var readTime : String
        lateinit var source: String
        lateinit var sourceImg: String
        lateinit var elapsedTime: String
        lateinit var image: String
        lateinit var url : String

        constructor(id: String,
                    title: String,
                    readTime : String,
                    source: String,
                    sourceImg: String,
                    elapsedTime: String,
                    image: String,
                    url : String):this(){
            this.id = id
            this.title = title
            this.readTime = readTime
            this.source = source
            this.sourceImg = sourceImg
            this.elapsedTime = elapsedTime
            this.url = url
            this.image = image
        }
    }


}