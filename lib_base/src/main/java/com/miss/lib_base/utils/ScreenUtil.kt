package com.miss.lib_base.utils

import android.content.res.Configuration
import com.miss.lib_common.common.AppUtil

/**
 *  author : 唐鹏聪
 *  date : 2022/6/18 18:40
 *  description :
 *
 *          屏幕相关工具类
 *
 */
object ScreenUtil {

    /**
     * 是否是   ipad
     * @param screenLayout
     *
     */
    fun isPad(screenLayout: Int): Boolean {
        return screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun isPad(): Boolean {
        val xlarge =
            AppUtil.getContext().resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE
        val large =
            AppUtil.getContext().resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        return xlarge || large
    }


}