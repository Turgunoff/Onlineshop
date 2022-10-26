package com.uz.onlineshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uz.onlineshop.R
import com.uz.onlineshop.model.ProductModel
import com.uz.onlineshop.utils.Constants

/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
class CartAdapter(val items: List<ProductModel>) : RecyclerView.Adapter<CartAdapter.ItemHolder>() {

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        val tvCount: TextView = view.findViewById(R.id.idCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cart_item_layout,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = item.name
        holder.tvPrice.text = item.price
        Glide.with(holder.itemView.context)
            .load(Constants.HOST_IMAGE + item.image).into(holder.imgProduct)
        holder.tvCount.text = item.cartCount.toString()
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}