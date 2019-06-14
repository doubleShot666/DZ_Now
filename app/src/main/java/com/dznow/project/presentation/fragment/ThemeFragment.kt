package com.dznow.project.presentation.fragment

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
import com.dznow.project.presentation.adapter.ThemeAdapter
import com.dznow.project.presentation.base.BaseFragment
import com.dznow.project.presentation.contract.ThemeView
import com.dznow.project.presentation.model.Theme
import com.dznow.project.presentation.presenter.ThemePresenter
import com.dznow.project.presentation.utils.RxBus
import kotlinx.android.synthetic.main.theme_fragment_layout.recyclerView

class ThemeFragment : BaseFragment<ThemePresenter>() , ThemeView, ThemeAdapter.ThemeAdapterListner {

    private var expanded  = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.theme_fragment_layout, container, false)
    }

    override fun instantiatePresenter(): ThemePresenter {
        return ThemePresenter(this)
    }

    override fun initComponents() {

        presenter.getThemes()
    }

    override fun initThemes(themeList: List<Theme>){
        val adapter = ThemeAdapter(themeList,context!!,this)
        val controller : LayoutAnimationController
        when(expanded){
            true -> {
                recyclerView.layoutManager = GridLayoutManager(
                    retrieveContext(),
                    3
                )
                controller = AnimationUtils.loadLayoutAnimation(recyclerView.context,
                    R.anim.grid_layout_animation_from_bottom)
            }
            false ->{
                recyclerView.layoutManager = LinearLayoutManager(
                    retrieveContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                controller = AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.layout_slide_from_side)
            }
        }
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        recyclerView.layoutAnimation = controller
        recyclerView.scheduleLayoutAnimation()
    }

    override fun onThemeClicked(theme: Theme) {
        RxBus.publish(Pair("THEME_SELECTED",theme.name))
    }


}