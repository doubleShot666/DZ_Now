package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.LanguageAccessor
import io.reactivex.Observable

/**
 * This class implements [UserCase] and builds an observable on changing language operation result
 * @author brenco tech team
 * @see UserCase
 * @see LanguageAccessor
 */
class ChangeLanguage(private val languageAccessor: LanguageAccessor) : UseCase<Boolean, Any?>() {
    override fun buildUseCaseObservable(vararg params: Any?): Observable<Boolean> {
        return languageAccessor.changeLanguage(params[0] as String)
    }
}
