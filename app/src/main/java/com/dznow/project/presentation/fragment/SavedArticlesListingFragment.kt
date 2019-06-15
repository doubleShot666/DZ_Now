package com.dznow.project.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.dznow.project.R
import com.dznow.project.presentation.activity.ArticlesListingListingActivity
import com.dznow.project.presentation.adapter.ArticleAdapter
import com.dznow.project.presentation.base.BaseFragment
import com.dznow.project.presentation.contract.ArticlesListingView
import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.presenter.ArticlesListingPresenter
import com.dznow.project.presentation.utils.RxBus
import kotlinx.android.synthetic.main.main_articles_fragment_layout.fragment_title
import kotlinx.android.synthetic.main.main_articles_fragment_layout.more_btn
import kotlinx.android.synthetic.main.main_articles_fragment_layout.recyclerView

class SavedArticlesListingFragment() : BaseFragment<ArticlesListingPresenter>() , ArticlesListingView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.main_articles_fragment_layout, container, false)
    }

    override fun instantiatePresenter(): ArticlesListingPresenter {
        return ArticlesListingPresenter(this)
    }

    override fun initComponents() {
        fragment_title.text = getString(R.string.saved_articles)
        more_btn.text = getString(R.string.all)

        more_btn.setOnClickListener {
            val intentArticleActivity = Intent(context, ArticlesListingListingActivity::class.java)
            intentArticleActivity.putExtra("filter","saved")
            startActivity(intentArticleActivity)
        }
        presenter.getSavedArticles()
    }

    override fun initArticles(articleList: List<Article>){
        val adapter = ArticleAdapter(articleList,context!!,R.layout.article_horizental_row_item,
            object : ArticleAdapter.ArticleAdapterListner {
            override fun onArticleClicked(article: Article) {
                RxBus.publish(Pair(RxBus.MSG_ARTICLE_SELECTED,article.id))
            }
        })
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

    override fun onResume() {
        super.onResume()
        presenter.getSavedArticles()
    }
}