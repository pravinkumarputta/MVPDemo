package com.pravinkumarp.mvpdemo.presenter

import com.pravinkumarp.mvpdemo.MainApplication
import com.pravinkumarp.mvpdemo.contract.DetailActivityContract
import com.pravinkumarp.mvpdemo.model.bean.Fruit

class DetailActivityPresenter(private val view: DetailActivityContract.View, private val fruitId:Int):DetailActivityContract.Presenter {
    private val mainModelImplementor = MainApplication.getMainModelImplementor()

    init {
        view.setPresenter(this)
    }

    override fun onFruitUpdateButtonClicked(fruit: Fruit) {
        try {
            mainModelImplementor.updateFruit(fruit)
            view.updateViewOnUpdate(mainModelImplementor.getFruit(fruit.id!!));
        } catch (ex: Exception) {
            view.showError(ex.message!!)
        }
    }

    override fun onFruitDeleteButtonClicked(fruit: Fruit) {
        try {
            mainModelImplementor.deleteFruit(fruit)
            view.updateViewOnDelete(fruit);
            view.navigateToMainActivityOnDelete()
        } catch (ex: Exception) {
            view.showError(ex.message!!)
        }
    }

    override fun start() {
        view.showSelectedFruit(mainModelImplementor.getFruit(fruitId))
    }
}