package com.miss.lib_base.base.viewmodel

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.miss.lib_base.base.AppManager
import com.miss.lib_base.utils.SingleLiveEvent

/**
 * @author hw_tang
 * @time 2023/11/20 19:50
 * @description
 *       viewModel的基类 使用ViewModel类，放弃AndroidViewModel，原因：用处不大 完全有其他方式获取Application上下文
 */
open class BaseViewModel : ViewModel(), IBaseViewModel {

    val uiChange by lazy { UiLoadingChange() }

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { SingleLiveEvent<String>(true) }
        //隐藏加载框
        val dismissDialog by lazy { SingleLiveEvent<Boolean>(true) }
        val showLoading by lazy { SingleLiveEvent<String>(true) }
        val dismissLoading by lazy { SingleLiveEvent<String>(true) }
        val startActivityEvent by lazy { SingleLiveEvent<Map<String, Any>>(true) }
        val finishActivityEvent by lazy { SingleLiveEvent<Boolean>(true) }
        val showToast by lazy { SingleLiveEvent<String>(true)}
    }


    /**
     *      获取当前 Context
     * @return
     */
    fun getContext(): Context? = AppManager.currentActivity()

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>) {
        startActivity(clz, null)
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.CLASS] = clz
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uiChange.startActivityEvent.postValue(params)
    }


    object ParameterField {
        var CLASS = "CLASS"
        var CANONICAL_NAME = "CANONICAL_NAME"
        var BUNDLE = "BUNDLE"
    }

}