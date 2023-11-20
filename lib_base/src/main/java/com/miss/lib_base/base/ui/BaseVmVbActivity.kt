package com.miss.lib_base.base.ui

import android.view.View
import androidx.viewbinding.ViewBinding
import com.miss.lib_base.base.viewmodel.BaseViewModel
import com.miss.lib_base.utils.inflateBindingWithGeneric

/**
 *  author : 唐鹏聪
 *  date : 2022/6/16 15:57
 *  description :
 *
 *         在绑定VM的基础上绑定 ViewBinding
 */
abstract class BaseVmVbActivity<VM : BaseViewModel, VB : ViewBinding> : BaseVmActivity<VM>() {

    lateinit var mViewBind: VB


    override fun layoutId(): Int = 0

    /**
     * 创建DataBinding
     */
    override fun initViewBind(): View? {
        mViewBind = inflateBindingWithGeneric(layoutInflater)
        return mViewBind.root

    }

}