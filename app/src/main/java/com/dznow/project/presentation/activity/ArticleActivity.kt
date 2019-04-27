package com.dznow.project.presentation.activity

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
import android.os.Build
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN







class ArticleActivity : BaseActivity<ArticlePresenter>(), ArticleView {

    val elements = arrayListOf(TextElement("" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
        ImageElement("data:image/jpeg;base64,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"),
        TextElement("" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
        AnnotationElement("\" Ceci est une annotation \""),
        TextElement("" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        )



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

        scrollView.setOnScrollChangeListener(object : View.OnScrollChangeListener{
            override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                if(scrollY < 50){
                    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                else if(scrollY > 50)
                    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

            }

        })
    }

    override fun displayArticle(article: Article) {
        article_title.text = article.title
        article_editionTime.text = article.elapsedTime
        source_name.text = article.source
        readTime_textView.text = "2 minutes"
        Glide.with(this).load(article.sourceImg).into(source_imageView)
        Glide.with(this).load(article.image).into(article_imageview)
        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT--d305xVkd8wmuFGfAf6V0MSaryHm0eZF1WgFAX8LPssltybh").into(writer_imageView)
        writer_textView.text = "Yaker Amina Hamza"
        date_textView.text = "23/09/2018"
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for(element in elements){
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
        }
    }


}