package com.grocery.sainik_grocery.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.cartlist_model.Cart
import com.grocery.sainik_grocery.data.model.cartmodel.CartRequestModel
import com.grocery.sainik_grocery.data.model.productitem.ProductModel
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentCartBinding
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.ui.adapter.CartListAdapter
import com.grocery.sainik_grocery.utils.ItemOffsetDecoration
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel
import kotlin.math.roundToInt


class CartFragment : Fragment(), CartListAdapter.OnItemClickListener {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentCartBinding: FragmentCartBinding
    private var cartAdapter: CartListAdapter? = null
    private lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCartBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        val root = fragmentCartBinding.root
        mainActivity = activity as MainActivity

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentCartBinding.topnav.tvNavtitle.text = "Cart"
        with(fragmentCartBinding) {
            cartAdapter = CartListAdapter(mainActivity, this@CartFragment)
            rvProductList.adapter = cartAdapter
            rvProductList.layoutManager = GridLayoutManager(mainActivity, 1)
            val itemDecoration = ItemOffsetDecoration(mainActivity, R.dimen.photos_list_spacing)
            // rvProductList.addItemDecoration(itemDecoration)

            topnav.btnBack.setOnClickListener {

                mainActivity.onBackPressedDispatcher.onBackPressed()
            }


            /*fragmentCartBinding.topnav.clCart.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_cart)


            }*/
            fragmentCartBinding.topnav.ivNotification.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_notification)

            }


            topnav.btnWishlist.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_wishlist)

            }

            viewModel.cartListItem.observe(viewLifecycleOwner, Observer {count->
                if (count>0) {
                    fragmentCartBinding.topnav.tvCartCount.text = count.toString()
                    fragmentCartBinding.topnav.cvCartCount.visibility=View.VISIBLE
                    fragmentCartBinding.llTotalPice.visibility=View.VISIBLE
                    fragmentCartBinding.btnGo.visibility=View.VISIBLE
                }else{
                    fragmentCartBinding.topnav.cvCartCount.visibility=View.GONE
                    fragmentCartBinding.llTotalPice.visibility=View.GONE
                    fragmentCartBinding.btnGo.visibility=View.GONE
                }

                HomeFragment.cartCount=count
            })
            productCartList()

            btnGo.setOnClickListener {

                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.nav_order_summery)
            }
        }
    }


    private fun productDeleteFromCart( cartId: String) {


        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.cartDelete(
                cartId
            )
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                if (resource.data?.status == true) {

                                    productCartList()
                                    val builder = android.app.AlertDialog.Builder(mainActivity)
                                    builder.setMessage(resource.data.message)
                                    builder.setPositiveButton(
                                        "Ok"
                                    ) { dialog, which ->

                                        /*val navController = Navigation.findNavController(view)
                                        navController.navigate(R.id.nav_cart)*/
                                        dialog.dismiss()
                                    }
                                    val alert = builder.create()
                                    alert.setOnShowListener { arg0 ->
                                        alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                                            .setTextColor(resources.getColor(R.color.orange))
                                    }
                                    alert.show()

                                } else {

                                    Toast.makeText(
                                        mainActivity,
                                        resource.data?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

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
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.orange))
                                }
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

    private fun productaddtoCart(packSizeId: String, mIsAdded: Boolean) {


        if (Utilities.isNetworkAvailable(mainActivity)) {

            val cartRequestModel = if (mIsAdded) {
                CartRequestModel(
                    product_pack_size_id = packSizeId,
                    is_added = "1"
                )
            } else {
                CartRequestModel(
                    product_pack_size_id = packSizeId,
                    is_added = "0"
                )
            }
            viewModel.cartadd(
                cartRequestModel
            )
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                if (resource.data?.status == true) {

                                    productCartList()
                                    val builder = android.app.AlertDialog.Builder(mainActivity)
                                    builder.setMessage(resource.data.message)
                                    builder.setPositiveButton(
                                        "Ok"
                                    ) { dialog, which ->

                                        /*val navController = Navigation.findNavController(view)
                                        navController.navigate(R.id.nav_cart)*/
                                        dialog.dismiss()
                                    }
                                    val alert = builder.create()
                                    alert.setOnShowListener { arg0 ->
                                        alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                                            .setTextColor(resources.getColor(R.color.orange))
                                    }
                                    alert.show()

                                } else {

                                    Toast.makeText(
                                        mainActivity,
                                        resource.data?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

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
    private fun productCartList() {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.cartList()
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data.let {itResponse->

                                    if (itResponse?.status == true) {


                                        itResponse.let {itItem->
                                            if (itItem.data.cart.isNullOrEmpty()) {
                                                viewModel.cartListItem.value = 0
                                                cartAdapter?.updateData(arrayListOf(), "")
                                            }
                                            cartAdapter?.updateData(itItem.data.cart,itItem.productImageUrl)
                                            var totalPrice= 0
                                            itItem.data.cart.forEach { item->
                                                totalPrice += item.price.roundToInt()
                                            }

                                            fragmentCartBinding.tvItemprice.text= "₹ $totalPrice"
                                            fragmentCartBinding.tvItemQty.text= "${itItem.data.cart.size} Items"
                                        }
                                        viewModel.cartListItem.value= itResponse.data.cart.size


                                    } else {
                                        viewModel.cartListItem.value=0
                                        cartAdapter?.updateData(arrayListOf(),"")
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

    override fun onClick(
        position: Int,
        view: View,
        clickType: Int,
        cartData: Cart,
        count: Int,
        textView: TextView
    ) {

        when(clickType){
            0->{
                textView.text = count.toString()
                productaddtoCart(cartData.product_pack_size.id.toString(),false)
            }
            1->{
                textView.text = count.toString()
                productaddtoCart(cartData.product_pack_size.id.toString(),true)
            }
            2->{
                val builder = AlertDialog.Builder(mainActivity)
                builder.setTitle("Delete ${cartData.product.name}")
                builder.setMessage("Are you sure you delete ${cartData.product.name} from cart?")
                builder.setPositiveButton(
                    "Yes"
                ) { dialog, which ->

                    productDeleteFromCart(cartData.id.toString())
                    dialog.dismiss()
                }
                builder.setNegativeButton(
                    "Cancel"
                ) { dialog, which ->

                    dialog.dismiss()
                }
                val alert = builder.create()
                alert.setOnShowListener { arg0 ->
                    alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(resources.getColor(R.color.orange))
                    alert.getButton(android.app.AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(resources.getColor(R.color.orange))
                }
                alert.show()
            }
        }

    }

}