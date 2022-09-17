package com.uz.onlineshop.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uz.onlineshop.R
import com.uz.onlineshop.adapter.CategoryAdapter
import com.uz.onlineshop.adapter.ProductAdapter
import com.uz.onlineshop.databinding.FragmentHomeBinding
import com.uz.onlineshop.screen.MainViewModel
import com.uz.onlineshop.utils.Constants

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        getImage()
        initViews()
        loadData()
    }

    private fun initViews() {
        binding.recyclerProducts.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerCategories.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.swipe.setOnRefreshListener {
            loadData()
        }
    }

    private fun getImage() {
        viewModel.error.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.offersData.observe(requireActivity()) {
            binding.carouselView.setImageListener { position, imageView ->
                Glide.with(imageView)
                    .load(Constants.HOST_IMAGE + it[position].image)
                    .into(imageView)
            }
            binding.carouselView.pageCount = it.count()
        }
        viewModel.categoriesData.observe(requireActivity()) {
            binding.recyclerCategories.adapter = CategoryAdapter(it)
        }
        viewModel.productsData.observe(requireActivity()) {
            binding.recyclerProducts.adapter = ProductAdapter(it)
        }
        viewModel.progress.observe(requireActivity()) {
            binding.swipe.isRefreshing = it
        }
    }

    private fun loadData() {
        viewModel.getOffers()
        viewModel.getCategories()
        viewModel.getTopProducts()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}