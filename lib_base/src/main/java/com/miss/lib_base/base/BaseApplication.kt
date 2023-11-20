package com.miss.lib_base.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.miss.lib_common.common.AppUtil

/**
 * @author hw_tang
 * @time 2023/11/20 19:00
 * @description
 *      生命周期监听
 */
class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        setApplication(this)
    }


    companion object{

        private var sInstance: Application? = null

        fun setApplication(application: Application) {
            sInstance = application
            //  初始化工具类
            AppUtil.init(application)

            application.registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    AppManager.addActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {
                    
                }

                override fun onActivityResumed(activity: Activity) {
                    
                }

                override fun onActivityPaused(activity: Activity) {
                    
                }

                override fun onActivityStopped(activity: Activity) {
                    
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                    
                }

                override fun onActivityDestroyed(activity: Activity) {
                    AppManager.removeActivity(activity)
                }

            })
        }
    }

}