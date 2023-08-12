package com.abhishek.dunzoapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.dunzoapp.R
import com.abhishek.dunzoapp.adapters.SearchAdapter
import com.abhishek.dunzoapp.dataModel.Products
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.ArrayList


class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var auth: FirebaseAuth
    private var searchList = ArrayList<Products>()

    //firestore
    private val db = Firebase.firestore
    private val productsRef = db.collection("products");

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProductsData()
        search()

    }

    private fun getProductsData() {
        searchList.clear()
        productsRef
            .addSnapshotListener { snapshot, e ->
                if (snapshot != null && !snapshot.isEmpty) {
                    for (doc in snapshot) {
                        Log.d("abhishek", doc.data.toString())
                        var storeListObject = doc.toObject(Products::class.java)
                        searchList.add(storeListObject)

                    }
                } else {
                    Log.d(tag, "Current data: null")
                }
            }
    }

    private fun search() {
        val adapter = context?.let { SearchAdapter(searchList, it) }
        searchRecyclerView.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = adapter


        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.getFilter()?.filter(newText)
                return true
            }

        })
    }

}