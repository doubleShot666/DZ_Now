package com.dznow.project.presentation.contract

import com.dznow.project.presentation.base.BaseView
import com.dznow.project.presentation.model.Theme

interface ThemeView : BaseView {
    fun initThemes(themeList: List<Theme>)
}
