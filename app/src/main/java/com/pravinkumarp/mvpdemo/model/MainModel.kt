package com.pravinkumarp.mvpdemo.model

import com.pravinkumarp.mvpdemo.model.bean.Fruit

interface MainModel {
    fun addFruit(fruit: Fruit): Boolean
    fun getAllFruits(): ArrayList<Fruit>
    fun getFruit(id: Int): Fruit?
    fun updateFruit(fruit: Fruit): Boolean
    fun deleteFruit(fruit: Fruit): Boolean
}