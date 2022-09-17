package com.uz.onlineshop.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.uz.onlineshop.R
import com.uz.onlineshop.databinding.ActivityMainBinding
import com.uz.onlineshop.screen.cart.CartFragment
import com.uz.onlineshop.screen.favorite.FavoriteFragment
import com.uz.onlineshop.screen.home.HomeFragment
import com.uz.onlineshop.screen.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment.newInstance()
    private val favoriteFragment = FavoriteFragment.newInstance()
    private val cartFragment = CartFragment.newInstance()
    private val profileFragment = ProfileFragment.newInstance()
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
        initViews()
    }

    private fun initViews() {

    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, homeFragment, homeFragment.tag).hide(homeFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, favoriteFragment, favoriteFragment.tag).hide(favoriteFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, cartFragment, cartFragment.tag).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, profileFragment, profileFragment.tag).hide(profileFragment)
            .commit()

        supportFragmentManager.beginTransaction().show(activeFragment).commit()

        binding.btNavView.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.home) {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment)
                    .commit()
                activeFragment = homeFragment
            } else if (it.itemId == R.id.favorite) {
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(favoriteFragment).commit()
                activeFragment = favoriteFragment
            } else if (it.itemId == R.id.cart) {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(cartFragment)
                    .commit()
                activeFragment = cartFragment
            } else if (it.itemId == R.id.profile) {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(profileFragment)
                    .commit()
                activeFragment = profileFragment
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}