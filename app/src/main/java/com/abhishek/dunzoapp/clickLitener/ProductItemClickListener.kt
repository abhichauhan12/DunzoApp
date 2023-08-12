package com.abhishek.dunzoapp.clickLitener

import com.abhishek.dunzoapp.dataModel.Products

interface ProductItemClickListener {
    fun onItemClickListener(productModel: Products, storePosition: Int)
    fun onAddButtonClick(productModel: Products, storePosition: Int)
    fun onPlusButtonClick(productModel: Products, storePosition: Int)
    fun onMinusButtonClick(productModel: Products, storePosition: Int)

}