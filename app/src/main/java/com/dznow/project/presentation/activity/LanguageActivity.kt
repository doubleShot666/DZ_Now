package com.dznow.project.presentation.activity

import android.content.Intent
import com.dznow.project.R
import com.dznow.project.domain.interactor.ChangeLanguage
import com.dznow.project.domain.interactor.GetLanguage
import com.dznow.project.presentation.base.BaseActivity
import com.dznow.project.presentation.contract.LanguageView
import com.dznow.project.presentation.presenter.LanguagePresenter
import com.dznow.project.presentation.utils.LanguageAccessorImp
import kotlinx.android.synthetic.main.language_activity_layout.*

class LanguageActivity : BaseActivity<LanguagePresenter>(), LanguageView {

    override fun instantiatePresenter(): LanguagePresenter {
        return LanguagePresenter(
            this ,
            GetLanguage(LanguageAccessorImp(this)),
            ChangeLanguage(LanguageAccessorImp(this))
        )
    }

    override fun getContentLayout(): Int {
        return R.layout.language_activity_layout
    }

    override fun initComponents() {
        presenter.getLanguage()

        validate_btn.setOnClickListener {
            when(switch1.isChecked){
                true -> presenter.setLanguage(getString(R.string.LangAR))
                false -> presenter.setLanguage(getString(R.string.LangFR))
            }
        }
    }

    override fun selectSwitch() {
        switch1.isChecked = true
    }

    override fun showError() {
        println("error")
    }

    override fun finishView() {
        val intentMainActivity = Intent(applicationContext,MainActivity::class.java)
        intentMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intentMainActivity)
    }


}