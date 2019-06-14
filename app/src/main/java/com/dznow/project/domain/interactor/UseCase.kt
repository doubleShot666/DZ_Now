/**
 * file name : UseCase
 *
 * Author : Sami DJOUADI
 *
 * Copyright © 2019 Trend-Tech
 */

package com.dznow.project.domain.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params> {
    private var disposables : CompositeDisposable = CompositeDisposable()

    /**
     * Builds an Observable which will be used when executing the current UseCase.
     */
    internal abstract fun buildUseCaseObservable(vararg params: Params): Observable<T>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * by [.buildUseCaseObservable] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DisposableObserver<T>, vararg params: Params) {

        val observable = this.buildUseCaseObservable(*params).
            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
