package com.dznow.project.presentation.activity


import android.widget.*
import com.bumptech.glide.Glide
import com.dznow.project.R
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.contract.ArticleView
import com.dznow.project.presentation.model.*
import com.dznow.project.presentation.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.article_details_activity_layout.*
import android.view.WindowManager
import android.content.Intent


class ArticleDetailsActivity : BaseActivity<ArticlePresenter>(), ArticleView {


    override fun instantiatePresenter(): ArticlePresenter {
        return ArticlePresenter(this)
    }

    override fun getContentLayout(): Int {
        return R.layout.article_details_activity_layout
    }

    override fun initComponents() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        presenter.getArticle(intent.getStringExtra(ARG_ARTICLE_ID))

        scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY < 50){
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            } else if(scrollY > 50)
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
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
            shareArticle(article)
        }

        mark_btn.setOnClickListener {
            presenter.save(article)
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    fun shareArticle(article: Article){
        val text = article.url
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        shareIntent.type = "text/plain"
        startActivity(Intent.createChooser(shareIntent, "Partage d'article"))
    }


    companion object {
        const val ARG_ARTICLE_ID = "article_id"
    }


}