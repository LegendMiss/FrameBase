package com.miss.lib_common.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.text.TextUtils
import android.util.Log
import com.miss.lib_common.common.AppUtil
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

/**
 * @author hw_tang
 * @time 2023/11/20 20:49
 * @description
 */
object BitmapUtil {

    const val TAG = "BitmapUtil"

    fun createIfNeed(filePath: String) {
        val file = File(filePath).parentFile
        if (!file.exists()) {
            file.mkdir()
        }
    }

    /**
     *      将 bitmap 存为 file
     *
     * @param bmp           资源
     * @param fileName      要保存的文件名
     * @param filePath      保存的路径（完整路径） or 缺省自动存在缓存
     * @return      成功返回 true
     */
    fun saveBitmap2File(bmp: Bitmap?, fileName: String, filePath: String?): Boolean {

        var result = true

        if (bmp == null || TextUtils.isEmpty(fileName)) {
            return false
        }
        var path = filePath
            ?: (AppUtil.getContext().cacheDir.absolutePath + "/.cache/" + fileName + ".jpg")

        createIfNeed(path)
        val file = File(path)
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(path)
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
        } catch (e: Exception) {

            //  如果图片写入失败，删除生成的文件
            if (file.exists()) {
                file.delete()
            }
            result = false
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (e: Exception) {
                    Log.d(TAG, "saveBitmap2file: outputStream e == " + e.message)
                }
            }
        }
        return result
    }

    /**
     * 读取文件为Bitmap
     *
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    fun getBitmapFromFile(filename: String?): Bitmap? {
        if (filename == null) {
            return null
        }
        try {
            val bitmap = BitmapFactory.decodeStream(FileInputStream(filename))
            val file = File(filename)
            if (file.exists()) {
                file.delete()
            }
            return bitmap
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return null
    }


    /**
     * 按比例缩放图片
     *
     * @param bitmap    原图
     * @param newWidth  新图片宽
     * @param newHeight 新图片高
     * @return 新的bitmap
     */
    fun scaleBitmap(bitmap: Bitmap, newWidth: Float, newHeight: Float): Bitmap? {
        val width = bitmap.width.toFloat()
        val height = bitmap.height.toFloat()
        var x = 0f
        var y = 0f
        var scaleWidth = 0f
        var scaleHeight = 0f
        val newBitmap: Bitmap
        if (newWidth > newHeight) {
            //  比例宽度大于高度的情况
            val scale = newWidth / newHeight
            val tempH = width / scale
            if (height > tempH) {
                x = 0f
                y = (height - tempH) / 2
                scaleWidth = width
                scaleHeight = tempH
            } else {
                scaleWidth = height * scale
                scaleHeight = height
                x = (width - scaleWidth) / 2
                y = 0f
            }
            Log.e(
                TAG,
                "scale:$scale scaleWidth:$scaleWidth scaleHeight:$scaleHeight"
            )
        } else if (newWidth < newHeight) {
            //  比例宽度小于高度的情况
            val scale = newHeight / newWidth
            val tempW = height / scale
            if (width > tempW) {
                y = 0f
                x = (width - tempW) / 2
                scaleWidth = tempW
                scaleHeight = height
            } else {
                scaleHeight = width * scale
                y = (height - scaleHeight) / 2
                x = 0f
                scaleWidth = width
            }
        } else {
            //  比例宽高相等的情况
            if (width > height) {
                x = (width - height) / 2
                y = 0f
                scaleHeight = height
                scaleWidth = height
            } else {
                y = (height - width) / 2
                x = 0f
                scaleHeight = width
                scaleWidth = width
            }
        }
        newBitmap = try {
            Bitmap.createBitmap(
                bitmap,
                x.toInt(),
                y.toInt(),
                scaleWidth.toInt(),
                scaleHeight.toInt(),
                null,
                false
            )
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "scaleBitmap error==$e")
            return null
        }
        return newBitmap
    }


    //按照指定角度旋转图片，达成自己想要的图片效果
    fun adjustPhotoRotation(bm: Bitmap?, orientationDegree: Int): Bitmap? {
        val m = Matrix()
        if (null == bm) {
            return null
        }
        try {
            m.setRotate(
                orientationDegree.toFloat(),
                bm.width.toFloat() / 2,
                bm.height.toFloat() / 2
            )
            return Bitmap.createBitmap(bm, 0, 0, bm.width, bm.height, m, true)
        } catch (ex: OutOfMemoryError) {
            Log.d(TAG,"adjustPhotoRotation : $ex")
        }
        return null
    }
}