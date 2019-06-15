package com.dznow.project.presentation.model

data class Theme(var id: String,
                 var name: String,
                 var articles : ArrayList<Article>,
                 var image: String){

    override fun equals(other: Any?): Boolean {
        return (other as Theme).hashCode() == this.hashCode()
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}