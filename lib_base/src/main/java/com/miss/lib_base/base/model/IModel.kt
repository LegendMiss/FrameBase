package com.miss.lib_base.base.model

/**
 * @author hw_tang
 * @time 2023/11/20 19:14
 * @description
 */
interface IModel {
    /**
     *      ViewModel销毁时清除Model，与ViewModel共消亡。Model层同样不能持有长生命周期对象
     */
    fun onCleared()

}