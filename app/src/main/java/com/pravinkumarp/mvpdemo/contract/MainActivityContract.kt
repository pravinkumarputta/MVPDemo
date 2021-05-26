package com.pravinkumarp.mvpdemo.contract

import com.pravinkumarp.mvpdemo.BasePresenter
import com.pravinkumarp.mvpdemo.BaseView
import com.pravinkumarp.mvpdemo.model.bean.Fruit

interface MainActivityContract {

    interface View: BaseView<Presenter>{
        fun showAllFruits(fruits: ArrayList<Fruit>)
        fun updateViewOnAddFruit(fruits: ArrayList<Fruit>)
        fun showError(message: String)
        fun navigateToDetailsActivity(id: Int)
    }

    interface Presenter: BasePresenter {
        fun onAddFruitButtonClicked(fruit: Fruit)
        fun onFruitListItemClickListener(id: Int)
    }
}