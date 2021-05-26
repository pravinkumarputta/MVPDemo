package com.pravinkumarp.mvpdemo

interface BaseView<T> {
    fun setPresenter(presenter: T)
}