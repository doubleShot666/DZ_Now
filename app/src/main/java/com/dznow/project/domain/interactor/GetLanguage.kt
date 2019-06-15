package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.LanguageAccessor
import io.reactivex.Observable

/**
 * Cette class implémente [UseCase] et créer un observable sur le résultat du cas d'utilisation de
 * récupération de la langue.
 * @see UseCase
 * @see LanguageAccessor
 */
class GetLanguage(private val languageAccessor: LanguageAccessor) : UseCase<String, Any?>() {

    override fun buildUseCaseObservable(vararg params: Any?): Observable<String> {
        return languageAccessor.getLanguage()
    }
}
