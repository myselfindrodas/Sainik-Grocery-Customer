package com.grocery.sainik_grocery.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.add_order.AddOrderRequestModel
import com.grocery.sainik_grocery.data.model.productitem.ProductModel
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentOrderSummaryBinding
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.ui.adapter.OrderDetailsListAdapter
import com.grocery.sainik_grocery.utils.ItemOffsetDecoration
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel


class OrderSummaryFragment : Fragment(), OrderDetailsListAdapter.OnItemClickListener {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentOrderSummaryBinding: FragmentOrderSummaryBinding
    private lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentOrderSummaryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_order_summary, container, false)
        val root = fragmentOrderSummaryBinding.root
        mainActivity = activity as MainActivity

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentOrderSummaryBinding.topnav.tvNavtitle.text = "Order summary"

        with(fragmentOrderSummaryBinding) {


            topnav.btnBack.setOnClickListener {

                mainActivity.onBackPressedDispatcher.onBackPressed()
            }

            topnav.clCart.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_cart)


            }

            topnav.btnWishlist.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_wishlist)

            }
            btnAddressChange.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_address)

            }

            if (HomeFragment.cartCount > 0) {
                topnav.tvCartCount.text = HomeFragment.cartCount.toString()
                topnav.cvCartCount.visibility = View.VISIBLE
            } else {
                topnav.cvCartCount.visibility = View.GONE
            }

            btnAddressChange.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_address)

            }
            topnav.ivNotification.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_notification)

            }
            btnCheckout.setOnClickListener {

                postOrderDetails(it)

            }
        }
        getAddressList()
        getOrderSummery()
    }

    private fun getAddressList() {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.addressList()
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                // mainActivity.hideProgressDialog()
                                resource.data?.let { itResponse ->

                                    if (itResponse.status) {
                                        itResponse.data.address.forEach { item ->
                                            if (item.isPrimary == 1) {
                                                fragmentOrderSummaryBinding.tvAddress.text =
                                                    item.apartmentName + " " + item.houseNo +
                                                            " " + item.streetDetails + "\n" +
                                                            item.city + ", " + item.pincode
                                                fragmentOrderSummaryBinding.tvAddress.tag = item.id
                                                return@forEach
                                            }
                                        }

                                        // addressAdapter?.updateData(itResponse.data.address)

                                    } else {

                                        Toast.makeText(
                                            mainActivity,
                                            resource.data?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                }


                            }
                            Status.ERROR -> {
                                mainActivity.hideProgressDialog()
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setMessage(it.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
                            }

                            Status.LOADING -> {
                                mainActivity.showProgressDialog()
                            }

                        }

                    }
                }

        } else {
            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun getOrderSummery() {


        if (Utilities.isNetworkAvailable(mainActivity)) {


            viewModel.orderSummeryDetails()
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.let { itResponse ->

                                    if (itResponse.status) {


                                        with(fragmentOrderSummaryBinding) {
                                            tvItemQtyTxt.text =
                                                if (itResponse.data.items > 1) "Price (${itResponse.data.items} Items)" else "Price (${itResponse.data.items} Item)"
                                            tvPrice.text = "₹ ${itResponse.data.price}"
                                            tvDeliveryCharge.text =
                                                "₹ ${itResponse.data.deliveryCharges}"
                                            tvDiscount.text = "₹ ${itResponse.data.discount}"
                                            val total =
                                                (itResponse.data.price - itResponse.data.discount) + itResponse.data.deliveryCharges
                                            tvTotalPrice.text = "₹ $total"
                                        }
                                        // addressAdapter?.updateData(itResponse.data.address)

                                    } else {

                                        Toast.makeText(
                                            mainActivity,
                                            resource.data?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                }


                            }
                            Status.ERROR -> {
                                mainActivity.hideProgressDialog()
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setMessage(it.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
                            }

                            Status.LOADING -> {
                                mainActivity.showProgressDialog()
                            }

                        }

                    }
                }

        } else {
            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun postOrderDetails(view: View) {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.addOrderDetails(
                AddOrderRequestModel(
                    fragmentOrderSummaryBinding.tvAddress.tag.toString().toInt()
                )
            )
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.let { itResponse ->

                                    if (itResponse.status) {

                                        with(fragmentOrderSummaryBinding) {
                                            tvItemQtyTxt.text =
                                                if (itResponse.data.items > 1) "Price (${itResponse.data.items} Items)" else "Price (${itResponse.data.items} Item)"
                                            tvPrice.text = "₹ ${itResponse.data.price}"
                                            tvDeliveryCharge.text =
                                                "₹ ${itResponse.data.deliveryCharges}"
                                            tvDiscount.text = "₹ ${itResponse.data.discount}"
                                            val total =
                                                (itResponse.data.price - itResponse.data.discount) + itResponse.data.deliveryCharges
                                            tvTotalPrice.text = "₹ $total"

                                            val bundle = Bundle()
                                            bundle.putString("orderId", itResponse.data.order.id.toString())
                                            bundle.putString("refId", itResponse.data.order.orderReferenceId)
                                            bundle.putString("price", total.toString())
                                             val navController = Navigation.findNavController(view)
                                            navController.navigate(R.id.nav_save_card,bundle)

                                        }
                                        // addressAdapter?.updateData(itResponse.data.address)

                                    } else {

                                        Toast.makeText(
                                            mainActivity,
                                            resource.data?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                }

                            }
                            Status.ERROR -> {
                                mainActivity.hideProgressDialog()
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setMessage(it.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
                            }

                            Status.LOADING -> {
                                mainActivity.showProgressDialog()
                            }

                        }

                    }
                }

        } else {
            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()
        }

    }

    override fun onClick(position: Int, view: View) {

    }

}