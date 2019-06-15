package com.dznow.project.presentation.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


object RxBus {

    private val publisher = PublishSubject.create<Any>()

    val MSG_THEME_SELECTED = "THEME_SELECTED"
    val MSG_ARTICLE_SELECTED = "ARTICLE_SELECTED"

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)



}
