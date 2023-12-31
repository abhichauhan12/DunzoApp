package com.abhishek.dunzoapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhishek.dunzoapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_detail_screen.*


class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_screen)
        initViews()
    }

    private fun initViews() {
        //tvItemName.text = intent.getStringExtra("storeName")
        tvItemName.text = intent.getStringExtra("productName")
        tvItemPrice.text = intent.getStringExtra("productPrice")
        tvItemQty.text = intent.getStringExtra("productQuantity")
        Glide.with(ivItemImage).load(intent.getStringExtra("productImage")).into(ivItemImage)
    }

}