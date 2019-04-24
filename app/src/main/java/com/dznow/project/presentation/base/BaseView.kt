/**
 * file name : BaseView
 *
 * Author : Sami DJOUADI
 *
 * Copyright © 2019 Trend-Tech
 */
package com.dznow.project.presentation.base

import android.content.Context


/**
 * Base view any view must implement.
 */
interface BaseView {
    /**
     * Returns the context in which the application is running.
     * @return the context in which the application is running
     */
    fun retrieveContext(): Context
}
