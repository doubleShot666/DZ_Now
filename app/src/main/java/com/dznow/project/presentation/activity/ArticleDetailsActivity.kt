package com.dznow.project.presentation.activity

import android.Manifest
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.widget.*
import com.bumptech.glide.Glide
import com.dznow.project.R
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.contract.ArticleView
import com.dznow.project.presentation.model.*
import com.dznow.project.presentation.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.article_details_activity_layout.*
import android.view.WindowManager
import android.view.View
import android.content.Intent
import android.graphics.Bitmap
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.webkit.*
import java.io.ByteArrayOutputStream
import java.util.*


class ArticleDetailsActivity : BaseActivity<ArticlePresenter>(), ArticleView {

    var bitmap : Bitmap? = null

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

        presenter.getArticle(intent.getStringExtra("articleId"))

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
        readTime_textView.text = "2 minutes"



        Glide.with(applicationContext)
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
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        /*for(element in article.elementsList){
            when(element){
                is TextElement -> {
                    val myView = inflater.inflate(R.layout.article_text_container, null)
                    val text = myView!!.findViewById(R.id.textView) as TextView
                    text.text = element.text
                    listView.addView(myView)
                }

                is VideoElement -> {
                    val myView = inflater.inflate(R.layout.article_video_container, null)
                    val mc = MediaController(this)
                    val video = myView!!.findViewById(R.id.videoView) as VideoView
                    video.setMediaController(mc)
                    video.setVideoPath(element.videoUrl)
                    listView.addView(myView)
                }

                is AnnotationElement -> {
                    val myView = inflater.inflate(R.layout.article_annotation_container, null)
                    val annotation = myView!!.findViewById(R.id.annotaion_textView) as TextView
                    annotation.text = element.annotation
                    listView.addView(myView)
                }

                is ImageElement -> {
                    val myView = inflater.inflate(R.layout.article_image_container, null)
                    val image = myView!!.findViewById(R.id.image_article) as ImageView
                    Glide.with(this).load(element.imgUrl).into(image)
                    listView.addView(myView)
                }
            }
        }*/

        share_btn.setOnClickListener {

            if(bitmap != null){
                val uri = getImageUri(applicationContext,bitmap!!)

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

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)

        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            contentResolver,
            inImage,
            UUID.randomUUID().toString() + ".png",
            "drawing"
        )
        return Uri.parse(path)
    }


}