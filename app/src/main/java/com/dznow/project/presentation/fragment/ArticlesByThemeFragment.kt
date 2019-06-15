package com.dznow.project.presentation.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.dznow.project.R
import com.dznow.project.presentation.adapter.MultiThemeArticleAdapter
import com.dznow.project.presentation.base.BaseFragment
import com.dznow.project.presentation.contract.ArticlesBythemeView
import com.dznow.project.presentation.model.Article
import com.dznow.project.presentation.presenter.ArticlesByThemePresenter
import com.dznow.project.presentation.utils.RxBus
import com.dznow.project.presentation.utils.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.main_articles_fragment_layout.recyclerView
import kotlinx.android.synthetic.main.themes_articles_fragment_layout.*

class ArticlesByThemeFragment : BaseFragment<ArticlesByThemePresenter>() , ArticlesBythemeView, MultiThemeArticleAdapter.AdapterListner {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.themes_articles_fragment_layout, container, false)
    }

    override fun instantiatePresenter(): ArticlesByThemePresenter {
        return ArticlesByThemePresenter(this)
    }

    override fun initComponents() {
        fragment_title.text = getString(R.string.articles_by_theme)
        presenter.getArticlesPerTheme()
    }

    override fun initArticles(articleList: HashMap<String,List<Article>>){
        val adapter = MultiThemeArticleAdapter(articleList,context!!,R.layout.main_articles_fragment_layout,this)
        val controller : LayoutAnimationController = AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.layout_slide_from_side)
        recyclerView.layoutManager = LinearLayoutManager(
            retrieveContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(32))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        recyclerView.layoutAnimation = controller
        recyclerView.scheduleLayoutAnimation()
    }

    override fun onResume() {
        super.onResume()
        presenter.getArticlesPerTheme()
    }

    override fun onThemeClicked(theme: String) {
        RxBus.publish(Pair(RxBus.MSG_THEME_SELECTED,theme))
    }

    override fun onArticleClicked(article: Article) {
        RxBus.publish(Pair(RxBus.MSG_ARTICLE_SELECTED,article.id))
    }


}