package com.uz.onlineshop.model

import java.io.Serializable


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
data class ProductModel(
    val id: Int,
    val name: String,
    val price: String,
    val image: String
):Serializable
