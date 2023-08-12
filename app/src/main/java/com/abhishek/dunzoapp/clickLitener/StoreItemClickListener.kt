package com.abhishek.dunzoapp.clickLitener

import com.abhishek.dunzoapp.dataModel.Store


interface StoreItemClickListener {
    fun onItemClickListener(storeListModel: Store, storePosition: Int);
}