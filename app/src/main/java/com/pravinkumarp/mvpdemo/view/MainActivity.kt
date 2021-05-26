package com.pravinkumarp.mvpdemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pravinkumarp.mvpdemo.R
import com.pravinkumarp.mvpdemo.contract.MainActivityContract
import com.pravinkumarp.mvpdemo.model.bean.Fruit
import com.pravinkumarp.mvpdemo.presenter.MainActivityPresenter
import com.pravinkumarp.mvpdemo.view.adapters.MainActivityFruitListAdapter

class MainActivity : AppCompatActivity(), MainActivityContract.View,
    MainActivityFruitListAdapter.OnFruitListItemClickListener {
    private lateinit var presenter: MainActivityContract.Presenter

    private lateinit var etFruitName: EditText
    private lateinit var etFruitDescription: EditText
    private lateinit var btAddFruit: Button
    private lateinit var recyclerViewFruits: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)

        etFruitName = findViewById(R.id.etFruitName)
        etFruitDescription = findViewById(R.id.etFruitDescription)
        btAddFruit = findViewById(R.id.btAddFruit)
        recyclerViewFruits = findViewById(R.id.recyclerViewFruits)
        recyclerViewFruits.layoutManager = LinearLayoutManager(this)
        recyclerViewFruits.itemAnimator = DefaultItemAnimator()

        btAddFruit.setOnClickListener {
            presenter.onAddFruitButtonClicked(
                Fruit(
                    etFruitName.text.toString(),
                    etFruitDescription.text.toString()
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun showAllFruits(fruits: ArrayList<Fruit>) {
        recyclerViewFruits.adapter = MainActivityFruitListAdapter(fruits, this)
    }

    override fun updateViewOnAddFruit(fruits: ArrayList<Fruit>) {
        showAllFruits(fruits)
        clearEditTexts()
    }

    override fun showError(message: String) {
        if (message == "No fruits to show.") {
            clearList()
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun navigateToDetailsActivity(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id)
        startActivity(intent)
    }

    override fun setPresenter(presenter: MainActivityContract.Presenter) {
        this.presenter = presenter
    }

    override fun onFruitListItemClicked(fruit: Fruit) {
        presenter.onFruitListItemClickListener(fruit.id!!)
    }

    private fun clearEditTexts() {
        etFruitName.setText("")
        etFruitDescription.setText("")
    }

    private fun clearList() {
        recyclerViewFruits.adapter = MainActivityFruitListAdapter(ArrayList(), this)
    }
}