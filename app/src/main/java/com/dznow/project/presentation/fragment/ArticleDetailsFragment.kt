package com.dznow.project.presentation.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.dznow.project.R
import com.dznow.project.presentation.base.BaseFragment
import com.dznow.project.presentation.contract.ArticleView
import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.article_details_activity_layout.*

class ArticleDetailsFragment : BaseFragment<ArticlePresenter>(), ArticleView {

    lateinit var articleId : String
    var bitmap : Bitmap? = null

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
        readTime_textView.text = "2 minutes"



        Glide.with(context!!)
            .asBitmap()
            .load(article.image)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }
            })


        Glide.with(this).load(article.sourceImg).into(source_imageView)
        Glide.with(this).load(article.image).into(article_imageview)
        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT--d305xVkd8wmuFGfAf6V0MSaryHm0eZF1WgFAX8LPssltybh").into(writer_imageView)
        writer_textView.text = "Yaker Amina Hamza"
        date_textView.text = "23/09/2018"

        /* display webView */



        article_webView.loadUrl("https://www.algerie360.com/feux-de-foret-trois-foyers-dincendie-enregistres-depuis-le-debut-de-juin-a-alger/")

        println("test")

        article_webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                println(url)
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                println(error?.description)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        share_btn.setOnClickListener {
            if(bitmap != null){

                val text = article.title
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "test")
                //shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                //shareIntent.type = "image/*"
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                startActivity(Intent.createChooser(shareIntent, "Share images..."))
            }


        }

        mark_btn.setOnClickListener {
            presenter.save(article)
        }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ARTICLE_ID = "article_id"
    }
}