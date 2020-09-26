package com.xxx.wordingtech.ui.base;

import io.reactivex.disposables.CompositeDisposable;

interface BindLife {
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

/*            val compositeDisposable: CompositeDisposable

    fun Disposable.bindLife() = addDisposable(this)

    fun Single<*>.bindLife() = subscribe({ }, { Timber.e(it, "Single error") }).bindLife()

    fun Maybe<*>.bindLife() = subscribe({ }, { Timber.e(it, "Maybe error") }).bindLife()

    fun Observable<*>.bindLife() = subscribe({ }, { Timber.e(it, "Observable error") }).bindLife()

    fun Completable.bindLife() = subscribe({ }, { Timber.e(it, "Completable error") }).bindLife()

    fun Flowable<*>.bindLife() = subscribe({ }, { Timber.e(it, "Flowable  error") }).bindLife()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun removeDisposable(disposable: Disposable?) {
        if (disposable != null)
            compositeDisposable.remove(disposable)
    }

    fun destroyDisposable() = compositeDisposable.clear()*/
}
