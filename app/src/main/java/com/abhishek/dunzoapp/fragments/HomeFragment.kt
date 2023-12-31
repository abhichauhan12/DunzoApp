package com.abhishek.dunzoapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abhishek.dunzoapp.R
import com.abhishek.dunzoapp.activities.EmptyCartActivity
import com.abhishek.dunzoapp.activities.LocationActivity
import com.abhishek.dunzoapp.activities.StoresActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

    //firestore
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private val currentAddressRef = db.collection("users");
    private var cartTotalItem: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        getCurrentAddress()

    }

    override fun onStart() {
        super.onStart()
        getCartTotal()
    }

    private fun getCurrentAddress() {
        auth.currentUser?.uid?.let {
            currentAddressRef.document(it).get().addOnSuccessListener { doc->
                if (doc.data?.get("address") != null) {
                    tvToolbarLocation.text = doc.data?.get("address").toString()
                } else {
                    tvToolbarLocation.text = "Rasta path"
                }
            }
        }
    }

    private fun getCartTotal() {
        auth.currentUser?.uid?.let {
            currentAddressRef.document(it).get().addOnSuccessListener { document ->
                if (document.data?.get("totalItem") != null) {
                    cartTotalItem = document.data?.get("totalItem").toString().toInt()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.vf1))
        imageList.add(SlideModel(R.drawable.vf2))
        imageList.add(SlideModel(R.drawable.vf3))
        imageList.add(SlideModel(R.drawable.vf4))

        imageSlider.setImageList(imageList,ScaleTypes.FIT)
    }


    private fun initViews() {
        //Toolbar Views
        ivToolbarProfile.setOnClickListener(this)
        ivToolbarCart.setOnClickListener(this)
        tvToolbarLocation.setOnClickListener(this)


        //Categories Views
        ivFruits.setOnClickListener(this)
        ivGroceries.setOnClickListener(this)
        ivMeat.setOnClickListener(this)
        ivPickup.setOnClickListener(this)

    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivFruits -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "Fruits & Vegetables")
                startActivity(intent)
            }
            R.id.ivGroceries -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "Daily Grocery")
                startActivity(intent)
            }
            R.id.ivMeat -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "Meat and Fish")
                startActivity(intent)
            }
            R.id.ivPickup -> {
                Toast.makeText(context, "PickUp", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.ivToolbarProfile -> {
                startActivity(Intent(context, com.abhishek.dunzoapp.activities.AdminActivity::class.java))
            }
            R.id.ivToolbarCart -> {
                if(cartTotalItem>0) {
                    startActivity(Intent(context, com.abhishek.dunzoapp.activities.CartActivity::class.java))
                } else {
                    startActivity(Intent(context, EmptyCartActivity::class.java))
                }
            }
            R.id.tvToolbarLocation -> {
                startActivity(Intent(context, LocationActivity::class.java))
            }
        }
    }

}