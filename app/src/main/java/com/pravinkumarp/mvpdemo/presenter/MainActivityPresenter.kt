package com.pravinkumarp.mvpdemo.presenter

import com.pravinkumarp.mvpdemo.MainApplication
import com.pravinkumarp.mvpdemo.contract.MainActivityContract
import com.pravinkumarp.mvpdemo.model.bean.Fruit

class MainActivityPresenter(private val view: MainActivityContract.View) :
    MainActivityContract.Presenter {
    private val mainModelImplementor = MainApplication.getMainModelImplementor()

    init {
        view.setPresenter(this)
    }

    override fun onAddFruitButtonClicked(fruit: Fruit) {
        try {
            mainModelImplementor.addFruit(fruit)
            view.updateViewOnAddFruit(mainModelImplementor.getAllFruits())
        } catch (ex: Exception) {
            view.showError(ex.message!!)
        }
    }

    override fun onFruitListItemClickListener(id: Int) {
        view.navigateToDetailsActivity(id)
    }

    override fun start() {
        try {
            view.showAllFruits(mainModelImplementor.getAllFruits())
        } catch (ex: Exception) {
            view.showError(ex.message!!)
        }
    }
}