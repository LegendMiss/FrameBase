package com.miss.lib_base.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


/**
 *  author : 唐鹏聪
 *  date : 2022/6/15 19:15
 *  description :
 *      1.解决数据倒灌问题
 *      2.不允许多观察者消费
 */
class SingleLiveEvent<T> : MutableLiveData<T> {

    private val single: Boolean

    constructor() {
        this.single = false
    }

    constructor(single: Boolean) {
        this.single = single
    }

    companion object{
        private const val TAG = "SingleLiveEvent"
    }


    private val mPending: AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer:Observer<in T>){
        if (single) {
            if (hasActiveObservers()) {
                Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
            }
            super.observe(owner) {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(it)
                }
            }
        } else {
            super.observe(owner, observer)
        }
    }




    @MainThread
    override fun setValue(@Nullable t: T?) {
        if (single) {
            mPending.set(true)
        }
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }


}