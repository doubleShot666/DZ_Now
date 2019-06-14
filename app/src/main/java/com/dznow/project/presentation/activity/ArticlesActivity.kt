package com.dznow.project.presentation.activity

import com.dznow.project.R
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.model.*
import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.dznow.project.presentation.adapter.ArticleAdapter
import com.dznow.project.presentation.contract.ArticlesView
import com.dznow.project.presentation.presenter.ArticlesPresenter
import com.dznow.project.presentation.utils.DividerItemDecoration
import com.dznow.project.presentation.utils.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.articles_activity_layout.*


class ArticlesActivity : BaseActivity<ArticlesPresenter>(), ArticlesView , ArticleAdapter.ArticleAdapterListner {



    override fun instantiatePresenter(): ArticlesPresenter {
        return ArticlesPresenter(this)
    }

    override fun getContentLayout(): Int {
        return R.layout.articles_activity_layout
    }

    override fun initComponents() {
        val title : String
        when(intent.getStringExtra("filter")){
            "latest" ->{
                presenter.getLatestArticles()
                title = "Derniers articles"
            }
            "top" ->{
                presenter.getTopArticles()
                title = "Top articles"
            }
            "local" ->{
                presenter.getLocalArticles()
                title = "Articles locaux"
            }
            "saved" ->{
                presenter.getSavedArticles()
                title = "Vos articles sauvegardés"
            }
            else ->{
                presenter.getThemeArticles(intent.getStringExtra("filter"))
                title = intent.getStringExtra("filter")
            }
        }
        toolbar.title = title
        toolbar.setNavigationOnClickListener {
            this.finish()
        }
    }

    override fun initArticles(articleList: List<Article>) {
        val adapter = ArticleAdapter(articleList,this,R.layout.article_vertical_row_item,this)
        val controller : LayoutAnimationController = AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.layout_slide_from_side)
        recyclerView.layoutManager = LinearLayoutManager(
            retrieveContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        recyclerView.addItemDecoration(DividerItemDecoration(this,R.drawable.divider))
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(2))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        recyclerView.layoutAnimation = controller
        recyclerView.scheduleLayoutAnimation()
    }

    override fun onArticleClicked(article: Article) {
        val intentArticleActivity = Intent(applicationContext, ArticleDetailsActivity::class.java)
        intentArticleActivity.putExtra("articleId",article.id)
        startActivity(intentArticleActivity)
    }


}