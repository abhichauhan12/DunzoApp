package com.abhishek.dunzoapp.dataModel

import com.abhishek.dunzoapp.dataModel.ProductModel

class ProductCategory(
    var image: String = "",
    val name: String ="",
    val storeName:String = "",
    var specificProductCat: ArrayList<ProductModel> = ArrayList<ProductModel>()
) {
}