package com.uz.onlineshop.model

/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
data class CategoryModel(
    val id: Int,
    val title: String,
    val icon: String,
    var checked: Boolean = false
)
