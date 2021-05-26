package com.pravinkumarp.mvpdemo.model

import com.pravinkumarp.mvpdemo.model.bean.Fruit
import com.pravinkumarp.mvpdemo.model.db.DBHelper

class MainModelImplementor(private val dbHelper: DBHelper) : MainModel {
    private var fruits = ArrayList<Fruit>()

    init {
        fruits = dbHelper.getAllFruits()
    }

    private fun refresh() {
        fruits.clear()
        fruits = dbHelper.getAllFruits()
    }

    override fun addFruit(fruit: Fruit): Boolean {
        if (fruit.name.isEmpty()) {
            throw Exception("Please enter fruit name.")
        }
        if (fruit.description.isEmpty()) {
            throw Exception("Please enter fruit description.")
        }
        val success = dbHelper.addFruit(fruit)
        if (!success) {
            throw Exception("Failed to add fruit.")
        }
        refresh()
        return success
    }

    override fun getAllFruits(): ArrayList<Fruit> {
        val fruits = dbHelper.getAllFruits()
        if (fruits.isEmpty()) {
            throw Exception("No fruits to show.")
        }
        return fruits
    }

    override fun getFruit(id: Int): Fruit {
        return dbHelper.getFruit(id) ?: throw Exception("No fruit found with this id.")
    }

    override fun updateFruit(fruit: Fruit): Boolean {
        val success = dbHelper.updateFruit(fruit)
        if (!success) {
            throw Exception("Failed to update fruit.")
        }
        refresh()
        return success
    }

    override fun deleteFruit(fruit: Fruit): Boolean {
        val success = dbHelper.deleteFruit(fruit)
        if (!success) {
            throw Exception("Failed to delete fruit.")
        }
        refresh()
        return success
    }
}