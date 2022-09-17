package com.uz.onlineshop.model


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
data class BaseResponse<T>(
    val succes: Boolean,
    val data: T,
    val message: String,
    val error_code: Int
)
