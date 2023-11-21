package com.miss.lib_base.base.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miss.lib_base.base.viewmodel.BaseViewModel
import com.miss.lib_common.common.ScreenUtil
import com.miss.lib_base.utils.getVmClazz
import com.miss.lib_base.view.isFastClick

/**
 *  author : 唐鹏聪
 *  date : 2022/6/16 15:13
 *  description :
 *
 *          MVVM activity 基类
 *          1.绑定ViewModel
 *
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity(), View.OnClickListener {

    lateinit var mViewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (initViewBind() != null) {
            setContentView(initViewBind())
        } else {
            setContentView(layoutId())
        }

        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        lifecycle.addObserver(mViewModel)
        initView(savedInstanceState)
        registerUiChange()
        createObserver()
    }


    /**
     *      register UI livedata
     */
    private fun registerUiChange() {
        //  show dialog
        mViewModel.uiChange.showDialog.observe(this) {
            showDialog()
        }
        //  close dialog
        mViewModel.uiChange.dismissDialog.observe(this) {
            dismissDialog()
        }
        mViewModel.uiChange.showLoading.observe(this) {
            showLoading(it)
        }
        mViewModel.uiChange.dismissLoading.observe(this) {
            dismissLoading()
        }
        // start activity
        mViewModel.uiChange.startActivityEvent.observe(this, Observer {
            val clz = it!![BaseViewModel.ParameterField.CLASS] as Class<*>?
            val bundle = it[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            startActivity(clz, bundle)
        })
        // finish activity
        mViewModel.uiChange.finishActivityEvent.observe(this) {
            finish()
        }
        mViewModel.uiChange.showToast.observe(this) {
            showToast(it)
        }
    }

    open fun showLoading(message: String) {}

    open fun dismissLoading() {}

    open fun showDialog() {}

    open fun dismissDialog() {}


    /**
     *      livedata 在此初始化
     */
    abstract fun createObserver()

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initViewBind(): View?

    @LayoutRes
    abstract fun layoutId(): Int


    private fun createViewModel(): VM = ViewModelProvider(this)[getVmClazz(this)]

    /**
     *      平板支持旋转，手机只支持竖屏
     */
    private fun setOrientation() {
        if (ScreenUtil.isPad()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        if (isFastClick()) {
            return
        }
        v?.let {
            onViewClick(it.id)
        }
    }

    /**
     *     有点击事件继承此方法即可，添加了快速点击拦截
     */
    open fun onViewClick(id: Int) {}

    /**
     *      show Toast
     */
    open fun showToast(msg: String) {}


}