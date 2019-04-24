/**
 * file name : BaseActivity
 *
 * Author : Sami DJOUADI
 *
 * Copyright © 2019 Trend-Tech
 */
package com.dznow.project.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity



/**
 * Activity all Activity classes must extend. It provides required methods and presenter
 * instantiation and calls.
 * @param P the type of the presenter the Activity is based on
 */
abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity(){

    protected lateinit var presenter: P


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayout())
        presenter = instantiatePresenter()
        initComponents()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    override fun retrieveContext(): Context {
        return this
    }

    /**
     * Returns the android identifier of the layout used for the activity.
     */
    abstract fun getContentLayout(): Int

    /**
     * Initializes the components used in the activity
     */
    abstract fun initComponents()


}
