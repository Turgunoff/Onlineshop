package com.uz.onlineshop.screen.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uz.onlineshop.R
import com.uz.onlineshop.adapter.CartAdapter
import com.uz.onlineshop.databinding.FragmentCartBinding
import com.uz.onlineshop.model.ProductModel
import com.uz.onlineshop.screen.MainViewModel
import com.uz.onlineshop.screen.makeOrder.MakeOrderActivity
import com.uz.onlineshop.utils.Constants
import com.uz.onlineshop.utils.PrefUtils
import java.io.Serializable

class CartFragment : Fragment(R.layout.fragment_cart) {
    lateinit var binding: FragmentCartBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        viewModel.progress.observe(requireActivity()) {
            binding.swipe.isRefreshing = it
        }

        viewModel.error.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.productsData.observe(
            requireActivity()
        ) {
            binding.recyclerView.adapter = CartAdapter(it)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.swipe.setOnRefreshListener {
            loadData()
        }

        binding.btnMakeOrder.setOnClickListener {
            val intent = Intent(requireActivity(), MakeOrderActivity::class.java)
            intent.putExtra(
                Constants.EXTRA_DATA,
                (viewModel.productsData.value ?: emptyList<ProductModel>())
            ) as Serializable
            startActivity(intent)
        }

        loadData()
    }

    private fun loadData() {
        viewModel.getProductsByIds(PrefUtils.getCartList().map { it.product_id })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}