package com.uz.onlineshop.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uz.onlineshop.api.repository.ShopRepository
import com.uz.onlineshop.model.CategoryModel
import com.uz.onlineshop.model.OfferModel
import com.uz.onlineshop.model.ProductModel

/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
class MainViewModel : ViewModel() {
    private val repository = ShopRepository()

    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val offersData = MutableLiveData<List<OfferModel>>()
    val categoriesData = MutableLiveData<List<CategoryModel>>()
    val productsData = MutableLiveData<List<ProductModel>>()

    fun getOffers() {
        repository.getOffers(error, progress, offersData)
    }

    fun getCategories() {
        repository.getCategories(error, categoriesData)
    }

    fun getTopProducts() {
        repository.getTopProducts(error, productsData)
    }
}