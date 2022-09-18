package com.uz.onlineshop.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uz.onlineshop.R
import com.uz.onlineshop.model.ProductModel
import com.uz.onlineshop.screen.productDetail.ProductDetailActivity
import com.uz.onlineshop.utils.Constants

class ProductAdapter(val items: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ItemHolder>() {

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ProductDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA, item)
            it.context.startActivity(intent)
        }
        Glide.with(holder.itemView.context)
            .load(Constants.HOST_IMAGE + item.image).into(holder.imgProduct)
        holder.tvName.text = item.name
        holder.tvPrice.text = item.price
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}
