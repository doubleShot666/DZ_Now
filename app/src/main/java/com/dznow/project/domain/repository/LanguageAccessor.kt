package com.dznow.project.domain.repository

import io.reactivex.Observable

/**
 * Cette interface offre les fonctionnalités de gestion de la langue de l'application.
 */

interface LanguageAccessor {

    /**
     * Cette fonction change la langue de l'application avec la langue en param^tre.
     * @param language
     * @return Observable<Boolean>
     */
    fun changeLanguage(language : String) : Observable<Boolean>

    /**
     * Cette fonction récupère la langue d'utilisation de l'application.
     * @return Observable<String>
     */
    fun getLanguage(): Observable<String>
}
