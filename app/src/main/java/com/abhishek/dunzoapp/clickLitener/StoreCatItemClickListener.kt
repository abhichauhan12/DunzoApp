package com.abhishek.dunzoapp.clickLitener

import com.abhishek.dunzoapp.dataModel.Categories


interface StoreCatItemClickListener {
    fun onItemClickListener(productCategory: Categories, storePosition: Int);
}