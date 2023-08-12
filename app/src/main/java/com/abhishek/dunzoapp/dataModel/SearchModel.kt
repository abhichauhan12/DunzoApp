package com.abhishek.dunzoapp.dataModel

data class SearchModel(
    var image_url: String,
    val product_name: String,
    var product_desc: String,
    var quantity: String,
    var price: String
) { }