package com.miss.lib_base.base.model

import io.reactivex.disposables.CompositeDisposable

/**
 * @author hw_tang
 * @time 2023/11/20 19:38
 * @description
 *       model层负责数据提供：
 *  *      1. 本地数据，包括数据库
 *  *      2. 网络数据，网络请求
 */
open class BaseModel:IModel {

    private val mCompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.clear()
        }
    }
}