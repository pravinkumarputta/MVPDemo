package com.pravinkumarp.mvpdemo.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pravinkumarp.mvpdemo.R
import com.pravinkumarp.mvpdemo.contract.DetailActivityContract
import com.pravinkumarp.mvpdemo.model.bean.Fruit
import com.pravinkumarp.mvpdemo.presenter.DetailActivityPresenter

class DetailActivity : AppCompatActivity(), DetailActivityContract.View {

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }

    private var fruitId: Int = -1

    private lateinit var presenter: DetailActivityContract.Presenter

    private lateinit var etFruitName: EditText
    private lateinit var etFruitDescription: EditText
    private lateinit var btUpdateFruit: Button
    private lateinit var btDeleteFruit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        fruitId = intent.getIntExtra(EXTRA_ID, -1)

        presenter = DetailActivityPresenter(this, fruitId)

        etFruitName = findViewById(R.id.etFruitName)
        etFruitDescription = findViewById(R.id.etFruitDescription)
        btUpdateFruit = findViewById(R.id.btUpdateFruit)
        btDeleteFruit = findViewById(R.id.btDeleteFruit)

        btUpdateFruit.setOnClickListener {
            presenter.onFruitUpdateButtonClicked(
                Fruit(
                    fruitId,
                    etFruitName.text.toString(),
                    etFruitDescription.text.toString()
                )
            )
        }

        btDeleteFruit.setOnClickListener {
            presenter.onFruitDeleteButtonClicked(
                Fruit(
                    fruitId,
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

    override fun showSelectedFruit(fruit: Fruit) {
        etFruitName.setText(fruit.name)
        etFruitDescription.setText(fruit.description)
    }

    override fun updateViewOnUpdate(fruit: Fruit) {
        showSelectedFruit(fruit)
        Toast.makeText(this, "Updated fruit.", Toast.LENGTH_LONG).show()
    }

    override fun updateViewOnDelete(fruit: Fruit) {
        Toast.makeText(this, "Deleted ${fruit.name}.", Toast.LENGTH_LONG).show()
    }

    override fun navigateToMainActivityOnDelete() {
        finish()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: DetailActivityContract.Presenter) {
        this.presenter = presenter
    }
}