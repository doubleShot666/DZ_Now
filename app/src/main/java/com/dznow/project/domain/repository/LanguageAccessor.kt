package com.dznow.project.domain.repository

import io.reactivex.Observable

/**
 * This interface must be implements in Data layer.
 * @author brenco tech team
 */

interface LanguageAccessor {

    fun changeLanguage(language : String) : Observable<Boolean>
    fun getLanguage(): Observable<String>
}
