package com.dznow.project.presentation.activity

import com.dznow.project.R
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.model.*
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.dznow.project.presentation.adapter.ArticleAdapter
import com.dznow.project.presentation.contract.ArticlesListingView
import com.dznow.project.presentation.fragment.ArticleDetailsFragment
import com.dznow.project.presentation.presenter.ArticlesListingPresenter
import com.dznow.project.presentation.utils.DividerItemDecoration
import com.dznow.project.presentation.utils.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.articles_activity_layout.*


class ArticlesListingActivity : BaseActivity<ArticlesListingPresenter>(), ArticlesListingView , ArticleAdapter.ArticleAdapterListner {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun instantiatePresenter(): ArticlesListingPresenter {
        return ArticlesListingPresenter(this)
    }

    override fun getContentLayout(): Int {
        return R.layout.articles_activity_layout
    }

    override fun initComponents() {

        if(article_details_container != null)
            twoPane = true

        val title : String
        when(intent.getStringExtra("filter")){
            "latest" ->{
                presenter.getLatestArticles()
                title = getString(R.string.article_of_the_day)
            }
            "top" ->{
                presenter.getTopArticles()
                title = getString(R.string.top_articles)
            }
            "local" ->{
                presenter.getLocalArticles()
                title = getString(R.string.local_articles)
            }
            "saved" ->{
                presenter.getSavedArticles()
                title = getString(R.string.saved_articles)
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

        if(twoPane)
            onArticleClicked(articleList[0])
    }

    override fun onArticleClicked(article: Article) {
        if(twoPane){
            val fragment = ArticleDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ArticleDetailsFragment.ARG_ARTICLE_ID, article.id)
                }
            }
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.article_details_container, fragment)
                .commit()
        }else{
            val intentArticleActivity = Intent(applicationContext, ArticleDetailsActivity::class.java)
            intentArticleActivity.putExtra(ArticleDetailsActivity.ARG_ARTICLE_ID,article.id)
            startActivity(intentArticleActivity)
        }

    }


}