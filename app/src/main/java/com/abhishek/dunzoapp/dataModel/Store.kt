package com.abhishek.dunzoapp.dataModel

class Store(
    var store_name: String = "",
    var store_id: String = "",
    var address: String = "",
    var store_image: String = "",
    var categories: List<Categories> = ArrayList<Categories>()
) {

}