package com.uz.onlineshop.utils

import com.orhanobut.hawk.Hawk
import com.uz.onlineshop.model.ProductModel


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
object PrefUtils {
    private const val PREF_FAVORITES = "pref-favorites"
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
}