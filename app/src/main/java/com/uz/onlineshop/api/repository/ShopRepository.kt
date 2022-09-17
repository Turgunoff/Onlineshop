package com.uz.onlineshop.api.repository

import androidx.lifecycle.MutableLiveData
import com.uz.onlineshop.api.NetworkManager
import com.uz.onlineshop.model.BaseResponse
import com.uz.onlineshop.model.CategoryModel
import com.uz.onlineshop.model.OfferModel
import com.uz.onlineshop.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
class ShopRepository {

    private val compositeDisposable = CompositeDisposable()

    fun getOffers(
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>,
        succes: MutableLiveData<List<OfferModel>>
    ) {
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<OfferModel>>>() {
                    override fun onNext(t: BaseResponse<List<OfferModel>>) {
                        progress.value = false
                        if (t.succes) {
                            succes.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value = false
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                })
        )


    }

    fun getCategories(
        error: MutableLiveData<String>,
        succes: MutableLiveData<List<CategoryModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoryModel>>>() {
                    override fun onNext(t: BaseResponse<List<CategoryModel>>) {
                        if (t.succes) {
                            succes.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                })
        )
    }

    fun getTopProducts(
        error: MutableLiveData<String>,
        succes: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getTopProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.succes) {
                            succes.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                })
        )
    }
}