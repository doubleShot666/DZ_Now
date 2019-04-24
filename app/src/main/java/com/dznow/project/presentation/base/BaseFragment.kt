/**
 * file name : BaseFragment
 *
 * Author : Sami DJOUADI
 *
 * Copyright © 2019 Trend-Tech
 */
package com.dznow.project.presentation.base


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View


/**
 * Fragment all Fragment classes must extend. It provides required methods and presenter
 * instantiation and calls.
 * @param P the type of the presenter the Fragment is based on
 */
abstract class BaseFragment <P : BasePresenter<BaseView>> : Fragment(), BaseView {

    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    override fun retrieveContext(): Context {
        return context!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }


    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    /**
     * Initializes the components used in the activity
     */
    abstract fun initComponents()
}
