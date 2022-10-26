package com.uz.onlineshop.utils

import com.orhanobut.hawk.Hawk
import com.uz.onlineshop.model.CartModel
import com.uz.onlineshop.model.ProductModel

/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
object PrefUtils {
    private const val PREF_FAVORITES = "pref-favorites"
    private const val PREF_CART = "pref-cart"
    private val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())

    fun setFavorite(item: ProductModel) {
        if (items.filter { it == item.id }.firstOrNull() != null) {
            items.remove(item.id)
        } else {
            items.add(item.id)
        }

        Hawk.put(PREF_FAVORITES, items)
    }

    fun getFavoriteList(): ArrayList<Int> {
        return Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
    }

    fun checkFavorite(item: ProductModel): Boolean {
        return items.filter { it == item.id }.firstOrNull() != null
    }

    fun setCart(item: ProductModel) {
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf())
        val cart = items.filter {
            it.product_id == item.id
        }.firstOrNull()
        if (cart != null) {
            if (item.cartCount > 0) {
                cart.count = item.cartCount
            } else {
                items.remove(cart)
            }
        } else {
            val newCart = CartModel(item.id, item.cartCount)
            items.add(newCart)
        }

        Hawk.put(PREF_CART, items)
    }

    fun getCartList(): ArrayList<CartModel> {
        return Hawk.get(PREF_CART, arrayListOf())
    }

    fun getCartCount(item: ProductModel): Int {
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf())
        return items.filter {
            it.product_id == item.id
        }.firstOrNull()?.count ?: 0
    }
}