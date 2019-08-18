package com.dznow.project.device

import android.arch.persistence.room.*
import com.dznow.project.data.entity.UserEntity
import com.dznow.project.presentation.model.Article
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id : String) : UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSavedArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): Single<List<Article>>

}