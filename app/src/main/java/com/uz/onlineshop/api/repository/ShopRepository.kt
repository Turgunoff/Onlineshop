package com.uz.onlineshop.api.repository

import androidx.lifecycle.MutableLiveData
import com.uz.onlineshop.api.NetworkManager
import com.uz.onlineshop.model.BaseResponse
import com.uz.onlineshop.model.CategoryModel
import com.uz.onlineshop.model.OfferModel
import com.uz.onlineshop.model.ProductModel
import com.uz.onlineshop.model.request.GetProductsByIdsRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
class ShopRepository {

//    private val compositeDisposable = CompositeDisposable()

    fun getOffers(
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>,
        succes: MutableLiveData<List<OfferModel>>
    ) {
        progress.value = true
        NetworkManager.getApiService().getOffers().enqueue(object :
            Callback<BaseResponse<List<OfferModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<OfferModel>>>,
                response: Response<BaseResponse<List<OfferModel>>>
            ) {
                progress.value = false
                if (response.isSuccessful) {
                    succes.value = response.body()!!.data
                } else {
                    error.value = response.body()?.message ?: response.message()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                error.value = t.localizedMessage
                progress.value = false
            }
        })
//        compositeDisposable.add(
//            NetworkManager.getApiService().getOffers()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : DisposableObserver<BaseResponse<List<OfferModel>>>() {
//                    override fun onNext(t: BaseResponse<List<OfferModel>>) {
//                        progress.value = false
//                        if (t.succes) {
//                            succes.value = t.data
//                        } else {
//                            error.value = t.message
//                        }
//                    }
//
//                    override fun onError(e: Throwable) {
//                        progress.value = false
//                        error.value = e.localizedMessage
//                    }
//
//                    override fun onComplete() {
//                    }
//                })
//        )
    }

    fun getCategories(
        error: MutableLiveData<String>,
        succes: MutableLiveData<List<CategoryModel>>
    ) {
        NetworkManager.getApiService().getCategories()
            .enqueue(object : Callback<BaseResponse<List<CategoryModel>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<CategoryModel>>>,
                    response: Response<BaseResponse<List<CategoryModel>>>
                ) {
                    if (response.isSuccessful) {
                        succes.value = response.body()!!.data
                    } else {
                        error.value = response.body()?.message ?: response.message()
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponse<List<CategoryModel>>>,
                    t: Throwable
                ) {
                    error.value = t.localizedMessage
                }
            })
    }

    fun getTopProducts(
        error: MutableLiveData<String>,
        succes: MutableLiveData<List<ProductModel>>
    ) {
        NetworkManager.getApiService().getTopProducts()
            .enqueue(object : Callback<BaseResponse<List<ProductModel>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<ProductModel>>>,
                    response: Response<BaseResponse<List<ProductModel>>>
                ) {
                    if (response.isSuccessful) {
                        succes.value = response.body()!!.data
                    } else {
                        error.value = response.body()?.message ?: response.message()
                    }
                }

                override fun onFailure(call: Call<BaseResponse<List<ProductModel>>>, t: Throwable) {
                    error.value = t.localizedMessage
                }
            })
    }

    fun getProductsByCategory(
        id: Int,
        error: MutableLiveData<String>,
        succes: MutableLiveData<List<ProductModel>>
    ) {
        NetworkManager.getApiService().getCategoryProducts(id)
            .enqueue(object : Callback<BaseResponse<List<ProductModel>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<ProductModel>>>,
                    response: Response<BaseResponse<List<ProductModel>>>
                ) {
                    if (response.isSuccessful) {
                        succes.value = response.body()!!.data
                    } else {
                        error.value = response.body()?.message ?: response.message()
                    }
                }

                override fun onFailure(call: Call<BaseResponse<List<ProductModel>>>, t: Throwable) {
                    error.value = t.localizedMessage
                }
            })
    }

    fun getProductsByIds(
        ids: List<Int>,
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>,
        succes: MutableLiveData<List<ProductModel>>
    ) {
        progress.value = true
        NetworkManager.getApiService().getProductsByIds(GetProductsByIdsRequest(ids))
            .enqueue(object : Callback<BaseResponse<List<ProductModel>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<ProductModel>>>,
                    response: Response<BaseResponse<List<ProductModel>>>
                ) {
                    progress.value = false
                    if (response.isSuccessful) {
                        succes.value = response.body()!!.data
                    } else {
                        error.value = response.body()?.message ?: response.message()
                    }
                }

                override fun onFailure(call: Call<BaseResponse<List<ProductModel>>>, t: Throwable) {
                    progress.value = false
                    error.value = t.localizedMessage
                }
            })
    }
}