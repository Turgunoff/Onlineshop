package com.uz.onlineshop.api

import com.uz.onlineshop.model.BaseResponse
import com.uz.onlineshop.model.CategoryModel
import com.uz.onlineshop.model.OfferModel
import com.uz.onlineshop.model.ProductModel
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
interface Api {
    @GET("get_offers")
    fun getOffers(): Observable<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Observable<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts(): Observable<BaseResponse<List<ProductModel>>>
}