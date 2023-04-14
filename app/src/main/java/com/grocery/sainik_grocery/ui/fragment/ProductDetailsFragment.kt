package com.grocery.sainik_grocery.ui.fragment

import android.content.ContentValues
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.application.GlideApp
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.addtowishlist.AddWishlistRequestModel
import com.grocery.sainik_grocery.data.model.cartmodel.CartRequestModel
import com.grocery.sainik_grocery.data.model.categoryitem.categoryresponse.Categorysub
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentProductDetailsBinding
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.ui.adapter.PackSizeAdapter
import com.grocery.sainik_grocery.ui.adapter.ProductAdapter
import com.grocery.sainik_grocery.ui.adapter.ProductItemAdapter
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel
import com.squareup.picasso.Picasso

class ProductDetailsFragment : Fragment(), ProductAdapter.OnItemClickListener,
    ProductItemAdapter.OnImageItemClickListener, PackSizeAdapter.OnPackItemClickListener {
    lateinit var mainActivity: MainActivity
    lateinit var fragmentProductDetailsBinding: FragmentProductDetailsBinding
    private var productAdapter: ProductAdapter? = null
    private var productItemAdapter: ProductItemAdapter? = null
    private var packSizeAdapter: PackSizeAdapter? = null
    val categoryModelArrayList: ArrayList<Categorysub> = arrayListOf()

    private lateinit var viewModel: CommonViewModel
    private var productId: Int = 0
    private var wishlistId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProductDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)
        val root = fragmentProductDetailsBinding.root
        mainActivity = activity as MainActivity
        fragmentProductDetailsBinding.topnav.tvNavtitle.text = ""

        val intent = arguments
        if (intent != null && intent.containsKey("productid")) {
            productId = intent.getInt("productid", 0)
        }

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter(mainActivity, this@ProductDetailsFragment)
        fragmentProductDetailsBinding.rvSimilarproducts.adapter = productAdapter
        fragmentProductDetailsBinding.rvSimilarproducts.layoutManager =
            LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)

        productItemAdapter = ProductItemAdapter(mainActivity, this@ProductDetailsFragment)
        fragmentProductDetailsBinding.rvItemImg.adapter = productItemAdapter
        fragmentProductDetailsBinding.rvItemImg.layoutManager =
            LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)

        packSizeAdapter = PackSizeAdapter(mainActivity, this@ProductDetailsFragment)
        fragmentProductDetailsBinding.rvPackSize.adapter = packSizeAdapter
        fragmentProductDetailsBinding.rvPackSize.layoutManager =
            LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)


        fragmentProductDetailsBinding.topnav.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }


        viewModel.cartListItem.observe(viewLifecycleOwner, Observer {count->
            if (count>0) {
                fragmentProductDetailsBinding.topnav.tvCartCount.text = count.toString()
                fragmentProductDetailsBinding.topnav.cvCartCount.visibility=View.VISIBLE
            }else{
                fragmentProductDetailsBinding.topnav.cvCartCount.visibility=View.GONE
            }

            HomeFragment.cartCount=count
        })

        fragmentProductDetailsBinding.topnav.clCart.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_cart)

        }

        fragmentProductDetailsBinding.btnAddressChange.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_address)

        }

        fragmentProductDetailsBinding.topnav.ivNotification.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_notification)

        }

        fragmentProductDetailsBinding.topnav.btnWishlist.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_wishlist)

        }

        fragmentProductDetailsBinding.llPackTxt.setOnClickListener {
            if (fragmentProductDetailsBinding.rvPackSize.isVisible) {
                fragmentProductDetailsBinding.rvPackSize.visibility = View.GONE
                fragmentProductDetailsBinding.ivPackArrow.rotation = 90f

            } else {
                fragmentProductDetailsBinding.rvPackSize.visibility = View.VISIBLE
                fragmentProductDetailsBinding.ivPackArrow.rotation = 270f
            }
        }
        fragmentProductDetailsBinding.btnAddtoproduct.setOnClickListener {

            var packSizeId = 0
            packSizeAdapter?.getList()?.forEach { item ->
                if (item.is_primary == 1) {
                    packSizeId = item.id
                }
            }

            println("PACK SIZE $packSizeId")
            productaddtoCart(it, packSizeId = packSizeId.toString(), true)

        }



        fragmentProductDetailsBinding.btnAddWishlist.setOnClickListener {

            addtoWishlist(wishlistId.toString(), it)

        }

        getProductDetails()

        setProductDetailsView(fragmentProductDetailsBinding)

        productCartList()

        getAddressList()
    }

    private fun setProductDetailsView(binding: FragmentProductDetailsBinding) {

        with(binding) {

            btnaboutproductExpand.setOnClickListener {

                btnaboutproductExpand.visibility = View.GONE
                btnaboutproductClose.visibility = View.VISIBLE
                llaboutproduct.visibility = View.VISIBLE

                btnbenifitproductExpand.visibility = View.VISIBLE
                btnbenifitproductClose.visibility = View.GONE
                llbenifits.visibility = View.GONE


                btnusesExpand.visibility = View.VISIBLE
                btnusesClose.visibility = View.GONE
                llusages.visibility = View.GONE


            }


            btnaboutproductClose.setOnClickListener {

                btnaboutproductExpand.visibility = View.VISIBLE
                btnaboutproductClose.visibility = View.GONE
                llaboutproduct.visibility = View.GONE
            }




            btnbenifitproductExpand.setOnClickListener {

                btnbenifitproductExpand.visibility = View.GONE
                btnbenifitproductClose.visibility = View.VISIBLE
                llbenifits.visibility = View.VISIBLE

                btnaboutproductExpand.visibility = View.VISIBLE
                btnaboutproductClose.visibility = View.GONE
                llaboutproduct.visibility = View.GONE

                btnusesExpand.visibility = View.VISIBLE
                btnusesClose.visibility = View.GONE
                llusages.visibility = View.GONE
            }


            btnbenifitproductClose.setOnClickListener {

                btnbenifitproductExpand.visibility = View.VISIBLE
                btnbenifitproductClose.visibility = View.GONE
                llbenifits.visibility = View.GONE
            }



            btnusesExpand.setOnClickListener {

                btnusesExpand.visibility = View.GONE
                btnusesClose.visibility = View.VISIBLE
                llusages.visibility = View.VISIBLE


                btnbenifitproductExpand.visibility = View.VISIBLE
                btnbenifitproductClose.visibility = View.GONE
                llbenifits.visibility = View.GONE


                btnaboutproductExpand.visibility = View.VISIBLE
                btnaboutproductClose.visibility = View.GONE
                llaboutproduct.visibility = View.GONE

            }


            btnusesClose.setOnClickListener {

                btnusesExpand.visibility = View.VISIBLE
                btnusesClose.visibility = View.GONE
                llusages.visibility = View.GONE
            }
        }
    }

    private fun getAddressList() {


        if (Utilities.isNetworkAvailable(mainActivity)) {


            viewModel.addressList()
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.let {itResponse->

                                    if (itResponse.status) {
                                        itResponse.data.address.forEach {item->
                                            if (item.isPrimary==1){
                                                fragmentProductDetailsBinding.tvAddress.text= item.apartmentName + " " +item.houseNo +
                                                        " " + item.streetDetails + "\n" +
                                                        item.city + ", " + item.pincode
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
    private fun getProductDetails() {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.productDetails(

                productId.toString()
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            resource.data?.let { itProfileInfo ->


                                Picasso.get()
                                    .load(itProfileInfo.productImageUrl+"/"+itProfileInfo.data.productDetails.product.productImages[0].image)
                                    .error(R.drawable.item)
                                    .placeholder(R.drawable.item)
                                    .into(fragmentProductDetailsBinding.ivProductmain)
                                productItemAdapter?.updateData(itProfileInfo.data.productDetails.product.productImages,itProfileInfo.productImageUrl)
                                packSizeAdapter?.updateData(itProfileInfo.data.productDetails.productPackSize)

                                productAdapter?.updateData(itProfileInfo.data.similarProducts,itProfileInfo.productImageUrl)
                                with(fragmentProductDetailsBinding) {

                                    if (itProfileInfo.data.productDetails.productPackSize.isNullOrEmpty()) {
                                        llPackTxt.visibility = View.GONE
                                    } else {
                                        llPackTxt.visibility = View.VISIBLE
                                    }
                                    tvProductName.setText(itProfileInfo.data.productDetails.product.name)
                                   // tvProductPrice.setText("Price: ₹ ${itProfileInfo.data.productDetails.sellingPrice.toString()}")
                                    llaboutproduct.setText(itProfileInfo.data.productDetails.product.about)
                                    llbenifits.setText(itProfileInfo.data.productDetails.product.benifits)
                                    llusages.setText(itProfileInfo.data.productDetails.product.storageAndUses)
                                    if (itProfileInfo.data.productDetails.discount>0){

                                        tvDiscount.text= "${itProfileInfo.data.productDetails.discount}% Off"
                                        llDiscount.visibility=View.VISIBLE
                                    }else{
                                        llDiscount.visibility=View.GONE
                                    }

                                    tvPrice.text =
                                        " ₹ ${itProfileInfo.data.productDetails.sellingPrice.toString()}/${itProfileInfo.data.productDetails.product.unitsOfMeasurementTypes}"
                                    tvPriceOld.text =
                                        "₹ ${itProfileInfo.data.productDetails.product.price.toString()}/${itProfileInfo.data.productDetails.product.unitsOfMeasurementTypes}"

                                    tvPriceOld.paintFlags = tvPriceOld.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                                    wishlistId = itProfileInfo.data.productDetails.id
                                    rateProduct.rating =
                                        itProfileInfo.data.productDetails.avgRating.toFloat()
                                }
                                // categoryListAdapter?.updateData(itProfileInfo.data.category as ArrayList<Category>)

                            }

                        }
                        Status.ERROR -> {
                            mainActivity.hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.status)

//                            if (it.message!!.contains("401", true)) {
//                                val builder = AlertDialog.Builder(this@LoginemailActivity)
//                                builder.setMessage("Invalid Employee Id / Password")
//                                builder.setPositiveButton(
//                                    "Ok"
//                                ) { dialog, which ->
//
//                                    dialog.cancel()
//
//                                }
//                                val alert = builder.create()
//                                alert.show()
//                            } else
//                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

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

    private fun productaddtoCart(view: View, packSizeId: String, mIsAdded: Boolean) {


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

    private fun addtoWishlist(productid:String, view: View){

        if (Utilities.isNetworkAvailable(mainActivity)) {
            viewModel.addtoWishlist(AddWishlistRequestModel(product_id = productid))
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                if (resource.data?.status == true) {
                                    val builder = android.app.AlertDialog.Builder(mainActivity)
                                    builder.setMessage(resource.data.message)
                                    builder.setPositiveButton(
                                        "Ok"
                                    ) { dialog, which ->

                                        dialog.dismiss()
                                        val navController = Navigation.findNavController(view)
                                        navController.navigate(R.id.nav_wishlist)
                                    }
                                    val alert = builder.create()
                                    alert.setOnShowListener { arg0 ->
                                        alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                                            .setTextColor(resources.getColor(R.color.orange))
                                    }
                                    alert.show()

                                } else {

                                    Toast.makeText(mainActivity, resource.data?.message, Toast.LENGTH_SHORT).show()

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
            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show() }

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

                                        if (itResponse.data.cart.isNullOrEmpty()) {
                                            viewModel.cartListItem.value = 0
                                        }else
                                        viewModel.cartListItem.value= itResponse.data.cart.size

                                    } else {

                                        viewModel.cartListItem.value=0
                                        /*Toast.makeText(
                                            mainActivity,
                                            resource.data?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()*/

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

    override fun onClick(position: Int, view: View, id: Int) {
        val bundle = Bundle()
        bundle.putInt("productid", id)
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.nav_productdetails, bundle)
        /*val navController = Navigation.findNavController(view)
        navController.navigate(R.id.nav_productlist)*/
    }


    override fun onImgItemClick(position: Int, view: View, imgUrl: String) {


        Picasso.get()
            .load(imgUrl)
            .error(R.drawable.item)
            .placeholder(R.drawable.item)
            .into(fragmentProductDetailsBinding.ivProductmain)

    }

    override fun onPackClick(position: Int, view: View) {


    }

}