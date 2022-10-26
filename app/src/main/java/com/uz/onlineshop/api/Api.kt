package com.uz.onlineshop.api

import com.uz.onlineshop.model.BaseResponse
import com.uz.onlineshop.model.CategoryModel
import com.uz.onlineshop.model.OfferModel
import com.uz.onlineshop.model.ProductModel
import com.uz.onlineshop.model.request.GetProductsByIdsRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
interface Api {
    @GET("get_offers")
    fun getOffers(): Call<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Call<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts(): Call<BaseResponse<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id") categoryId: Int):
        Call<BaseResponse<List<ProductModel>>>

    @POST("get_products_by_ids")
    fun getProductsByIds(@Body request: GetProductsByIdsRequest):
        Call<BaseResponse<List<ProductModel>>>
}