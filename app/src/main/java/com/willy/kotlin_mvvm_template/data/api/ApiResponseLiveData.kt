package com.willy.kotlin_mvvm_template.data.api

import androidx.lifecycle.LiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by WillyYan on 2019/06/11.
 * 處理回傳值，統一轉為 ApiResponse
 */
class ApiResponseLiveData<T>(val single: Single<T>) : LiveData<ApiResponse<T>>() {

    private var disposables = CompositeDisposable()

    override fun onActive() {
        super.onActive()

        if (disposables.size() == 0) {
            disposables.add(single.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<T>() {
                        override fun onSuccess(t: T) {
                            value = ApiResponse(t)
                        }

                        override fun onError(e: Throwable) {
                            value = ApiResponse(ErrorCode.findErrorByThrowable(e))
                        }
                    })
            )
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (!disposables.isDisposed) disposables.dispose()
    }
}