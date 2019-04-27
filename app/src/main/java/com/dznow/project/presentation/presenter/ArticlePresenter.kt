package com.dznow.project.presentation.presenter

import com.dznow.project.presentation.base.BasePresenter
import com.dznow.project.presentation.contract.ArticleView
import com.dznow.project.presentation.model.Article

class ArticlePresenter(articleView: ArticleView) : BasePresenter<ArticleView>(articleView) {
    fun getArticle(articleId: String) {
        val article = Article(
            "3",
            "Trump lève l’exemption sur l’achat du pétrole iranien : Washington et Riyad veulent briser Téhéran",
            "El Watan",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScklu-ilxBiiGNvcclXgKMGH1IaRtQpkDOzoaCK6gYfc5vFDgEEg",
            "4 heures",
            "https://www.elwatan.com/wp-content/uploads/2019/04/trump--e1556071431509.jpg"
        )

        view.displayArticle(article)
    }
}