package com.miss.lib_common.common

import android.annotation.SuppressLint
import android.content.Context

/**
 * @author hw_tang
 * @time 2023/11/20 19:07
 * @description
 */
@SuppressLint("StaticFieldLeak")
object AppUtil {

    private var sContext: Context? = null

    /**
     *      初始化工具类
     * @param context
     */
    fun init(context: Context) {
        sContext = context.applicationContext
    }

    /**
     *      获取ApplicationContext
     * @return
     */
    fun getContext(): Context {
        if (sContext != null) {
            return sContext!!
        }
        throw NullPointerException("should be initialized in application")
    }
}