package com.dznow.project.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dznow.project.R
import com.dznow.project.presentation.base.BaseFragment
import com.dznow.project.presentation.contract.ArticleView
import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.article_details_activity_layout.*

class ArticleDetailsFragment : BaseFragment<ArticlePresenter>(), ArticleView {

    lateinit var articleId : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ARTICLE_ID)) {
                articleId = it.getString(ARG_ARTICLE_ID)!!
            }
        }

        return inflater.inflate(R.layout.article_details_activity_layout, container, false)
    }

    override fun instantiatePresenter(): ArticlePresenter {
        return ArticlePresenter(this)
    }

    override fun initComponents() {
        presenter.getArticle(articleId)
    }

    override fun displayArticle(article: Article) {
        article_title.text = article.title
        article_editionTime.text = article.elapsedTime
        source_name.text = article.source
        readTime_textView.text = article.readTime
        Glide.with(this).load(article.sourceImg).into(source_imageView)
        Glide.with(this).load(article.image).into(article_imageview)

        article_webView.loadUrl(article.url)

        share_btn.setOnClickListener {
            val text = article.url
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, text)
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Partage d'article"))
        }

        mark_btn.setOnClickListener {
            presenter.save(article)
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_ARTICLE_ID = "article_id"
    }
}