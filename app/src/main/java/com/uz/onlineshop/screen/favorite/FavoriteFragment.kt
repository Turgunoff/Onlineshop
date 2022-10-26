package com.uz.onlineshop.screen.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uz.onlineshop.R
import com.uz.onlineshop.adapter.ProductAdapter
import com.uz.onlineshop.databinding.FragmentFavoriteBinding
import com.uz.onlineshop.screen.MainViewModel
import com.uz.onlineshop.utils.PrefUtils

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    lateinit var binding: FragmentFavoriteBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        binding.recyclerProducts.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.productsData.observe(requireActivity()) {
            binding.recyclerProducts.adapter = ProductAdapter(it)
        }
        viewModel.error.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.progress.observe(requireActivity()) {
            binding.swipe.isRefreshing = it
        }
        binding.swipe.setOnRefreshListener {
            loadData()
        }
        loadData()
    }

    private fun loadData() {
        viewModel.getProductsByIds(PrefUtils.getFavoriteList())
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoriteFragment()
    }
}