package com.miss.lib_common.common

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

/**
 * @author hw_tang
 * @time 2023/11/23 10:02
 * @description
 *      工具类：截屏
 */
object ScreenShot {


    /**
     *      截屏
     * @param view  截取某个控件的视图
     * @return      bitmap
     */
    fun screenShotView(view: View):Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}