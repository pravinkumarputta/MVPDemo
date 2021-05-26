package com.pravinkumarp.mvpdemo.model.bean

class Fruit(var name: String,var description:String) {
    var id: Int? = null
    constructor(id:Int, name: String, description:String) : this(name, description) {
        this.id = id
    }
}