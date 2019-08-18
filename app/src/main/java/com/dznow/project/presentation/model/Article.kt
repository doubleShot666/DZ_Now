package com.dznow.project.presentation.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey var id: String,
    var title: String,
    var readTime : String,
    var source: String,
    var sourceImg: String,
    var elapsedTime: String,
    var image: String,
    var url : String){

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Article)
            other.hashCode() == this.hashCode()
        else
            false
    }
}