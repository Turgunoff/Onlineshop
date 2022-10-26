package com.uz.onlineshop.screen.productDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.uz.onlineshop.R
import com.uz.onlineshop.databinding.ActivityProductDetailBinding
import com.uz.onlineshop.model.ProductModel
import com.uz.onlineshop.utils.Constants
import com.uz.onlineshop.utils.PrefUtils

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    lateinit var item: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = intent.getSerializableExtra(Constants.EXTRA_DATA) as ProductModel

        binding.cardViewBack.setOnClickListener {
            finish()
        }
        if (PrefUtils.getCartCount(item) > 0) {
            binding.btnAddToCart.visibility = View.GONE
        }

        binding.btnAddToCart.setOnClickListener {
            item.cartCount = 1
            PrefUtils.setCart(item)
            Toast.makeText(this, "Product added to Cart", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.cardViewFavorite.setOnClickListener {
            PrefUtils.setFavorite(item)
            if (PrefUtils.checkFavorite(item)) {
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite_add)
            } else {
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
            }
        }

        binding.tvTitle.text = item.name
        binding.tvProductName.text = item.name
        binding.tvProductPrice.text = item.price

        if (PrefUtils.checkFavorite(item)) {
            binding.imgFavorite.setImageResource(R.drawable.ic_favorite_add)
        } else {
            binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
        }
        Glide.with(this).load(Constants.HOST_IMAGE + item.image).into(binding.imgProduct)

        addFavorite()
    }

    fun addFavorite() {
    }
}