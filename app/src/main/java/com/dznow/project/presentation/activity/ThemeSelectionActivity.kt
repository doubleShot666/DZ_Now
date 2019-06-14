package com.dznow.project.presentation.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.dznow.project.R
import com.dznow.project.presentation.adapter.ThemeSelectionAdapter
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.contract.ThemeSelectionView
import com.dznow.project.presentation.model.Theme
import com.dznow.project.presentation.presenter.ThemeSelectionPresenter
import com.dznow.project.presentation.utils.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.theme_selection_activity_layout.*


class ThemeSelectionActivity : BaseActivity<ThemeSelectionPresenter>(),ThemeSelectionView,
    ThemeSelectionAdapter.ThemeAdapterListner {

    private var selectedThemes = ArrayList<String>()


    override fun instantiatePresenter(): ThemeSelectionPresenter {
        return ThemeSelectionPresenter(this)
    }

    override fun getContentLayout(): Int {
        return R.layout.theme_selection_activity_layout
    }

    override fun initComponents() {
        presenter.getSelectedThemes()

        toolbar.setNavigationOnClickListener {
            this.finish()
        }

        validate_btn.setOnClickListener {
            presenter.setSelectedThemes(selectedThemes)
        }
    }

    override fun finishView() {
        this.finish()
    }

    override fun initThemes(themeList: List<Pair<Boolean,Theme>>){
        for(t in themeList)
            if(t.first)
                selectedThemes.add(t.second.id)

        val adapter = ThemeSelectionAdapter(themeList,this,this)
        val controller : LayoutAnimationController = AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.grid_layout_animation_from_bottom)
        recyclerView.layoutManager = GridLayoutManager(
            retrieveContext(),
            2
        )
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2,64,true))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        recyclerView.layoutAnimation = controller
        recyclerView.scheduleLayoutAnimation()
    }

    override fun onThemeClicked(theme: Theme) {
        if(selectedThemes.contains(theme.id))
            selectedThemes.remove(theme.id)
        else
            selectedThemes.add(theme.id)
    }
}