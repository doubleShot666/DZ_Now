package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.LanguageAccessor
import io.reactivex.Observable

/**
 * Cette class implémente [UseCase] et créer un observable sur le résultat du cas d'utilisation de
 * changement de langue.
 * @see UseCase
 * @see LanguageAccessor
 */
class ChangeLanguage(private val languageAccessor: LanguageAccessor) : UseCase<Boolean, Any?>() {
    override fun buildUseCaseObservable(vararg params: Any?): Observable<Boolean> {
        return languageAccessor.changeLanguage(params[0] as String)
    }
}
