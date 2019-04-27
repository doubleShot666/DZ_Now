package com.dznow.project.presentation.contract

import com.dznow.project.presentation.base.BaseView
import com.dznow.project.presentation.model.Theme

interface ThemeSelectionView : BaseView {
    fun initThemes(themeList: List<Pair<Boolean, Theme>>)
    fun finishView()
}