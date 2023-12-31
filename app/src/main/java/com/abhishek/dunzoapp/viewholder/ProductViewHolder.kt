package com.abhishek.dunzoapp.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.dunzoapp.clickLitener.ProductItemClickListener
import com.abhishek.dunzoapp.dataModel.Products
import com.bumptech.glide.Glide
import com.masai.dunzoclone.ClickLitener.ProductItemClickListener
import com.masai.dunzoclone.DataModels.Products
import kotlinx.android.synthetic.main.product_item_layout.view.*
import kotlinx.android.synthetic.main.product_item_layout.view.ivProductImage

class ProductViewHolder(
    view: View,
    var productItemClickListener: ProductItemClickListener,
    var cartProductId: HashMap<String, String>
): RecyclerView.ViewHolder(view) {

    fun setProductData(productList: Products, position: Int){
        itemView.apply {
            if(cartProductId.containsKey(productList.product_id)) {
                cvProductAdd.visibility = View.VISIBLE
                tvButtonAdd.visibility = View.GONE
            }
            setData(productList)


            tvButtonAdd.setOnClickListener {
                cvProductAdd.visibility = View.VISIBLE
                tvButtonAdd.visibility = View.GONE
                productItemClickListener.onAddButtonClick(productList, position)
            }

            ivButtonPlus.setOnClickListener {
                var qty: Int  = tvProductQty.text.toString().toInt()
                val plusQty = ++qty
                tvProductQty.text = plusQty.toString()
                productItemClickListener.onPlusButtonClick(productList, position)
            }

            ivButtonMinus.setOnClickListener {
                var qty: Int  = tvProductQty.text.toString().toInt()
                val minusQty = qty
                if(qty ==1) {
                    cvProductAdd.visibility = View.GONE
                    tvButtonAdd.visibility = View.VISIBLE
                    productItemClickListener.onMinusButtonClick(productList, position)
                } else {
                    tvProductQty.text = minusQty.toString()
                    productItemClickListener.onMinusButtonClick(productList, position)
                }

            }
        }
        itemView.productCardView.setOnClickListener {
            productItemClickListener.onItemClickListener(productList, position)
        }
    }

    private fun setData(productList: Products) {
        itemView.apply {
            tvProductQty.text = productList.quantity.toString()
            tvProductName.text = productList.name
            tvProductPrice.text = "₹"+productList.price
            Glide.with(ivProductImage).load(productList.image).into(ivProductImage)
        }

    }
}