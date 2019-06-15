package com.dznow.project.presentation.activity

import android.content.Intent
import android.os.Handler
import com.dznow.project.R
import com.dznow.project.domain.interactor.GetLanguage
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.contract.SplashScreenView
import com.dznow.project.presentation.presenter.SplashScreenPresenter
import com.dznow.project.device.LanguageAccessorImp


/**
 * This Class shows splash screen and authenticates the user
 */
class SplashScreenActivity : BaseActivity<SplashScreenPresenter>(), SplashScreenView {

    override fun instantiatePresenter(): SplashScreenPresenter {
        return SplashScreenPresenter(this, GetLanguage(LanguageAccessorImp(this)))
    }

    override fun finishView() {
        val intentFirstScreenActivity = Intent(applicationContext, MainActivity::class.java)
        intentFirstScreenActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intentFirstScreenActivity)
    }

    override fun initComponents() {
        Handler().postDelayed({
            presenter.getLanguage()
        }, DELAY_MILIS)
    }

    override fun getContentLayout(): Int {
        return R.layout.splash_screen_activity_layout
    }

    companion object{
        const val DELAY_MILIS : Long = 2000
    }

}
