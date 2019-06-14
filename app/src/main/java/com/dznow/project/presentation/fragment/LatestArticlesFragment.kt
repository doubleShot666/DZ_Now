package com.dznow.project.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.dznow.project.R
import com.dznow.project.presentation.activity.ArticlesActivity
import com.dznow.project.presentation.adapter.ArticleAdapter
import com.dznow.project.presentation.base.BaseFragment
import com.dznow.project.presentation.contract.ArticlesView
import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.presenter.ArticlesPresenter
import com.dznow.project.presentation.utils.RxBus
import kotlinx.android.synthetic.main.main_articles_fragment_layout.fragment_title
import kotlinx.android.synthetic.main.main_articles_fragment_layout.more_btn
import kotlinx.android.synthetic.main.main_articles_fragment_layout.recyclerView

class LatestArticlesFragment : BaseFragment<ArticlesPresenter>() , ArticlesView, ArticleAdapter.ArticleAdapterListner {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.main_articles_fragment_layout, container, false)
    }

    override fun instantiatePresenter(): ArticlesPresenter {
        return ArticlesPresenter(this)
    }

    override fun initComponents() {
        fragment_title.text = getString(R.string.article_of_the_day)
        more_btn.text = getString(R.string.all)

        more_btn.setOnClickListener {
            val intentArticleActivity = Intent(context, ArticlesActivity::class.java)
            intentArticleActivity.putExtra("filter","latest")
            startActivity(intentArticleActivity)
        }
        presenter.getLatestArticles()
    }

    override fun initArticles(articleList: List<Article>){
        val adapter = ArticleAdapter(articleList,context!!,R.layout.article_horizental_row_item,this)
        val controller : LayoutAnimationController = AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.layout_slide_from_side)
        recyclerView.layoutManager = LinearLayoutManager(
            retrieveContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        recyclerView.layoutAnimation = controller
        recyclerView.scheduleLayoutAnimation()
    }

    override fun onArticleClicked(article: Article) {
        RxBus.publish(Pair("ARTICLE_SELECTED",article.id))
    }


}