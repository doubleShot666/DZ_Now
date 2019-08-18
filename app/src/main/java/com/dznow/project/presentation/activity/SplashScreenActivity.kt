package com.dznow.project.presentation.activity

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.view.View
import com.dznow.project.R
import com.dznow.project.domain.interactor.GetLanguage
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.contract.SplashScreenView
import com.dznow.project.presentation.presenter.SplashScreenPresenter
import com.dznow.project.device.LanguageAccessorImp
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.splash_screen_activity_layout.*


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
            presenter.authenticateUser()
        }, DELAY_MILIS)


        google_btn.setOnClickListener {
            signInWithGmail()
        }

    }

    override fun showLoginButton(){
        google_btn.visibility = View.VISIBLE
    }

    private fun signInWithGmail(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                presenter.authenticateUser()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                if(response != null)
                    println(response.error!!.errorCode)
                else
                    println("Response is null")
            }
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.splash_screen_activity_layout
    }

    companion object{
        const val RC_SIGN_IN = 12345
        const val DELAY_MILIS : Long = 2000
    }

}
