package com.dznow.project.domain.interactor

import com.dznow.project.domain.repository.LanguageAccessor
import io.reactivex.Observable

/**
 * This class implements [UserCase] and builds an observable on getting language operation result
 * @author brenco tech team
 * @see UserCase
 * @see LanguageAccessor
 */
class GetLanguage(private val languageAccessor: LanguageAccessor) : UseCase<String, Any?>() {

    override fun buildUseCaseObservable(vararg params: Any?): Observable<String> {
        return languageAccessor.getLanguage()
    }
}
