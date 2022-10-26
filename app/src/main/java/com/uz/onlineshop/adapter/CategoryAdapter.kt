package com.uz.onlineshop.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uz.onlineshop.R
import com.uz.onlineshop.model.CategoryModel

/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */

class CategoryAdapter(val items: List<CategoryModel>, val callback: CategoryAdapterCallback) :
    RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val cardView: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            items.forEach {
                it.checked = false
            }
            item.checked = true

            callback.onClickItem(item)
            notifyDataSetChanged()
        }
        holder.tvTitle.text = item.title
        if (item.checked) {
            holder.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.colorPrimary
                )
            )
            holder.tvTitle.setTextColor(Color.WHITE)
        } else {
            holder.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray
                )
            )
            holder.tvTitle.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}

interface CategoryAdapterCallback {
    fun onClickItem(item: CategoryModel)
}
