package com.dznow.project.presentation.contract

import com.dznow.project.presentation.base.BaseView

interface LanguageView : BaseView {
    fun selectSwitch()
    fun finishView()
    fun showError()
}