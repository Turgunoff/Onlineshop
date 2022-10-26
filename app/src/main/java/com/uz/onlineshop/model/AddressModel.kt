package com.uz.onlineshop.model

import java.io.Serializable

/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
data class AddressModel(
    val address: String,
    val latitude: Double,
    val longitude: Double
) : Serializable