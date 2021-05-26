package com.pravinkumarp.mvpdemo.contract

import com.pravinkumarp.mvpdemo.BasePresenter
import com.pravinkumarp.mvpdemo.BaseView
import com.pravinkumarp.mvpdemo.model.bean.Fruit

interface DetailActivityContract {

    interface View:BaseView<Presenter>{
        fun showSelectedFruit(fruit: Fruit)
        fun updateViewOnUpdate(fruit: Fruit)
        fun updateViewOnDelete(fruit: Fruit)
        fun navigateToMainActivityOnDelete()
        fun showError(message: String)
    }

    interface Presenter:BasePresenter{
        fun onFruitUpdateButtonClicked(fruit: Fruit)
        fun onFruitDeleteButtonClicked(fruit: Fruit)
    }
}