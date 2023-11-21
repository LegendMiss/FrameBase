package com.miss.lib_common.common

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager
import com.elvishew.xlog.XLog
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

    /**
     *      通过 large、xlarge判断当前设备是否为平板
     *
     * @return
     */
    fun isPad(): Boolean {
        val xlarge =
            AppUtil.getContext().resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE
        val large =
            AppUtil.getContext().resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        return xlarge || large
    }


    /**
     *      屏幕参数
     */
    fun getScreenProperty() {

        val wm = AppUtil.getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(displayMetrics)

        //  屏幕宽度像素
        val width = displayMetrics.widthPixels
        // 屏幕高度（像素）
        val height = displayMetrics.heightPixels
        // 屏幕密度（0.75 / 1.0 / 1.5）
        val density = displayMetrics.density
        // 屏幕密度dpi（120 / 160 / 240）
        val densityDpi = displayMetrics.densityDpi
        // 屏幕宽度(dp)
        val screenWidth = width / density
        // 屏幕高度(dp)
        val screenHeight = height / density

        XLog.d(" width = $width ")
        XLog.d(" height = $height ")
        XLog.d(" density = $density ")
        XLog.d("densityDpi = $densityDpi ")
        XLog.d(" screenWidth = $screenWidth")
        XLog.d(" screenHeight = $screenHeight ")

    }

}