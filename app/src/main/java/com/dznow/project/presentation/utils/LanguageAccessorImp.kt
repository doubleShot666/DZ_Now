package com.dznow.project.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import com.dznow.project.R
import com.dznow.project.domain.repository.LanguageAccessor
import io.reactivex.Observable
import java.util.*

/**
 * This class store and retrieve the language of the app from the shared preferences.
 * @author brenco tech team
 * @see LanguageAccessor
 */
class LanguageAccessorImp(private val activity: Activity) : LanguageAccessor {

    override fun changeLanguage(language: String): Observable<Boolean> {

        return Observable.create { event ->

            println("lang $language")
            val locale: Locale
            val p : SharedPreferences = activity.getSharedPreferences(
                    activity.resources.getString(R.string.PrefFile),
                    Context.MODE_PRIVATE)
            val e = p.edit()

            if (language == activity.resources.getString(R.string.LangAR)) {
                locale = Locale("AR")
                e.putString("LangCode", "AR")
            } else {
                locale = Locale("FR")
                e.putString("LangCode", "FR")
            }
            e.apply()
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            activity.baseContext.resources.updateConfiguration(
                    config,
                    activity.baseContext.resources.displayMetrics)

            event.onNext(true)
        }

    }

    override fun getLanguage(): Observable<String> {
        return Observable.create { event ->
            val p : SharedPreferences = activity.getSharedPreferences(
                    activity.resources.getString(R.string.PrefFile),
                    Context.MODE_PRIVATE)
            var language = p.getString("LangCode","")
            println(language)
            if(language == null)
                language = "FR"

            val locale: Locale
            locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            activity.baseContext.resources.updateConfiguration(
                    config,
                    activity.baseContext.resources.displayMetrics)
            event.onNext(language)
        }
    }
}
