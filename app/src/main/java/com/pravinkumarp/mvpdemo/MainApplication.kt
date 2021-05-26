package com.pravinkumarp.mvpdemo

import android.app.Application
import com.pravinkumarp.mvpdemo.model.MainModelImplementor
import com.pravinkumarp.mvpdemo.model.db.DBHelper

class MainApplication: Application() {

    companion object {
        private lateinit var dbHelper: DBHelper
        private lateinit var mainModelImplementor: MainModelImplementor

        fun getDBHelper(): DBHelper {
            return dbHelper
        }

        fun getMainModelImplementor(): MainModelImplementor {
            return mainModelImplementor
        }
    }

    override fun onCreate() {
        super.onCreate()

        dbHelper = DBHelper(applicationContext)
        mainModelImplementor = MainModelImplementor(dbHelper)
    }
}