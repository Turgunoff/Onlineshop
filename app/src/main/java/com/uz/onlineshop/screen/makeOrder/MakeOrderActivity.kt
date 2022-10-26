package com.uz.onlineshop.screen.makeOrder

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.uz.onlineshop.MapsActivity
import com.uz.onlineshop.R
import com.uz.onlineshop.model.AddressModel
import com.uz.onlineshop.model.ProductModel
import com.uz.onlineshop.utils.Constants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MakeOrderActivity : AppCompatActivity() {
    lateinit var items: List<ProductModel>
    private var address: AddressModel? = null
    private lateinit var etAddress: EditText
    private lateinit var tvTotalAmount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)
        etAddress = findViewById(R.id.etAddress)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)

        items = intent.getSerializableExtra(Constants.EXTRA_DATA) as List<ProductModel>
        tvTotalAmount.setText(items.sumByDouble {
            it.cartCount.toDouble() * (it.price.replace(
                " ",
                ""
            ).toDoubleOrNull() ?: 0.0)
        }.toString())

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        etAddress.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onEvent(address: AddressModel) {
        this.address = address
        etAddress.setText("${address.latitude},${address.longitude}")
    }
}