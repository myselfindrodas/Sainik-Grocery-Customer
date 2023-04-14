package com.grocery.sainik_grocery.ui.fragment

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.dashboardmodel.DashboardRequestModel
import com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse.Category
import com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse.TopSellingProduct
import com.grocery.sainik_grocery.data.model.product_list.responsemodel.DataProductList
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentHomeBinding
import com.grocery.sainik_grocery.ui.LoginActivity
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.ui.URCListActivity
import com.grocery.sainik_grocery.ui.adapter.*
import com.grocery.sainik_grocery.utils.ItemOffsetDecoration
import com.grocery.sainik_grocery.utils.Shared_Preferences
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel


class HomeFragment : Fragment(),
    TopsellingAdapter.OnItemClickListener, CategoryAdapter.OnItemClickListener {
    lateinit var mainActivity: MainActivity
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    var btnMyaccountExpand: ImageView? = null
    var btnMyaccountClose: ImageView? = null
    var llmyaccountsubmenu: LinearLayout? = null
    var btnNavorders: LinearLayout? = null
    var btnNavsupport: LinearLayout? = null
    var btnNavfaq: LinearLayout? = null
    var tvChangeURC: TextView? = null
    var tvUrcName: TextView? = null
    var tvEditProfile: TextView? = null
    var btnNavprofile: LinearLayout? = null
    var btnNavsavecard: LinearLayout? = null
    var btnNavsaveaddress: LinearLayout? = null
    var btnNavnotification: LinearLayout? = null
    private var categoryAdapter: CategoryAdapter? = null
    private var topsellingAdapter: TopsellingAdapter? = null
    private var featuredproductAdapter: TopsellingAdapter? = null
    private var dailyproductAdapter: TopsellingAdapter? = null
    var btnNavlogout: LinearLayout? = null
    var btnExpandaccount: LinearLayout? = null
    private lateinit var viewModel: CommonViewModel


    companion object{
        var cartCount=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root = fragmentHomeBinding.root
        mainActivity = activity as MainActivity

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        btnMyaccountExpand = root.findViewById(R.id.btnMyaccountExpand)
        btnMyaccountClose = root.findViewById(R.id.btnMyaccountClose)
        btnNavorders = root.findViewById(R.id.btnNavorders)
        llmyaccountsubmenu = root.findViewById(R.id.llmyaccountsubmenu)
        btnNavsupport = root.findViewById(R.id.btnNavsupport)
        btnNavfaq = root.findViewById(R.id.btnNavfaq)
        tvUrcName = root.findViewById(R.id.tvUrcName)
        tvEditProfile = root.findViewById(R.id.tvEditProfile)
        tvChangeURC = root.findViewById(R.id.tvChangeURC)
        btnNavprofile = root.findViewById(R.id.btnNavprofile)
        btnNavsavecard = root.findViewById(R.id.btnNavsavecard)
        btnNavnotification = root.findViewById(R.id.btnNavnotification)
        btnExpandaccount = root.findViewById(R.id.btnExpandaccount)
        btnNavsaveaddress = root.findViewById(R.id.btnNavsaveaddress)
        btnNavlogout = root.findViewById(R.id.btnNavlogout)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topsellingAdapter = TopsellingAdapter(mainActivity, this@HomeFragment)
        fragmentHomeBinding.rvTopsellingproduct.setAdapter(topsellingAdapter)
        fragmentHomeBinding.rvTopsellingproduct.setLayoutManager(
            LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)
        )



        dailyproductAdapter = TopsellingAdapter(mainActivity, this@HomeFragment)
        fragmentHomeBinding.rvDailyproduct.setAdapter(dailyproductAdapter)
        fragmentHomeBinding.rvDailyproduct.setLayoutManager(
            LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)
        )


        categoryAdapter = CategoryAdapter(mainActivity, this@HomeFragment)
        fragmentHomeBinding.idGVcourses.setAdapter(categoryAdapter)
        fragmentHomeBinding.idGVcourses.setLayoutManager(GridLayoutManager(mainActivity, 3))
        val itemDecoration = ItemOffsetDecoration(mainActivity, R.dimen.photos_list_spacing)
        fragmentHomeBinding.idGVcourses.addItemDecoration(itemDecoration)


        featuredproductAdapter = TopsellingAdapter(mainActivity, this@HomeFragment)
        fragmentHomeBinding.rvFeaturesellingproduct.setAdapter(featuredproductAdapter)
        fragmentHomeBinding.rvFeaturesellingproduct.setLayoutManager(
            GridLayoutManager(
                mainActivity,
                2
            )
        )
        val itemDecoration2 = ItemOffsetDecoration(mainActivity, R.dimen.product_list_spacing)
        fragmentHomeBinding.rvFeaturesellingproduct.addItemDecoration(itemDecoration2)

        viewModel.cartListItem.observe(viewLifecycleOwner, Observer {count->
            if (count>0) {
                fragmentHomeBinding.topnav.tvCartCount.text = count.toString()
                fragmentHomeBinding.topnav.cvCartCount.visibility=View.VISIBLE
            }else{
                fragmentHomeBinding.topnav.cvCartCount.visibility=View.GONE
            }
            cartCount=count
        })

        dashboard(Shared_Preferences.getUrcid().toString())

        productCartList()

        fragmentHomeBinding.topnav.clCart.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_cart)


        }


        fragmentHomeBinding.topnav.btnWishlist.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_wishlist)


        }

        fragmentHomeBinding.topnav.ivNotification.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_notification)


        }

        fragmentHomeBinding.topnav.iv.setOnClickListener {

            fragmentHomeBinding.dl.openDrawer(GravityCompat.START)

        }

        tvChangeURC?.setOnClickListener {

            startActivity(Intent(mainActivity, URCListActivity::class.java))
            mainActivity.finish()
        }

        tvUrcName?.text=MainActivity.URCName
        fragmentHomeBinding.topnav.tvUrcName.text=MainActivity.URCName
        fragmentHomeBinding.featureproductViewall.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("viewalltype", "features")
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_productlist, bundle)
        }


        fragmentHomeBinding.dailyproductViewall.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("viewalltype", "essentials")
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_productlist, bundle)
        }


        fragmentHomeBinding.topsellingViewall.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("viewalltype", "topselling")
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_productlist, bundle)
        }


        fragmentHomeBinding.shopbycategoryViewall.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("viewalltype", "category")
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_productlist, bundle)

        }


        fragmentHomeBinding.btnSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.length!! >1){
                    dashboard(Shared_Preferences.getUrcid().toString(),s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })




        btnMyaccountExpand?.setOnClickListener {

            btnMyaccountClose?.visibility = View.VISIBLE
            llmyaccountsubmenu?.visibility = View.VISIBLE
            btnMyaccountExpand?.visibility = View.GONE

        }
        tvEditProfile?.setOnClickListener {


            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_profile)

        }
        btnExpandaccount?.setOnClickListener {

            btnMyaccountClose?.visibility = View.VISIBLE
            llmyaccountsubmenu?.visibility = View.VISIBLE
            btnMyaccountExpand?.visibility = View.GONE

        }
        btnMyaccountClose?.setOnClickListener {

            btnMyaccountExpand?.visibility = View.VISIBLE
            btnMyaccountClose?.visibility = View.GONE
            llmyaccountsubmenu?.visibility = View.GONE

        }


        /*fragmentHomeBinding.btnCurrentLocaion.setOnClickListener {

            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            *//*val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_productlist)*//*
        }*/


        btnNavsupport?.setOnClickListener {
            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_support)

        }


        btnNavfaq?.setOnClickListener {

            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_faq)
        }
        btnNavorders?.setOnClickListener {

            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_myorder)
        }
        btnNavprofile?.setOnClickListener {

            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_profile)

        }
        btnNavsavecard?.setOnClickListener {

            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_save_card)

        }
        btnNavnotification?.setOnClickListener {

            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_notification)

        }

        btnNavlogout?.setOnClickListener {
            val builder = AlertDialog.Builder(mainActivity)
            builder.setMessage("Do you want to logout?")
            builder.setPositiveButton(
                "Ok"
            ) { dialog, which ->
                logout()
                dialog.cancel()
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            val alert = builder.create()
            alert.setOnShowListener { arg0: DialogInterface? ->
                alert.getButton(AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(resources.getColor(R.color.blue,resources.newTheme()))
                alert.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(resources.getColor(R.color.orange,resources.newTheme()))
            }
            alert.show()
        }
        btnNavsaveaddress?.setOnClickListener {

            fragmentHomeBinding.dl.closeDrawer(GravityCompat.START)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_address)
        }
    }

    private fun logout() {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.logout()
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                if (resource.data?.status == true) {

                                    Shared_Preferences.setLoginStatus(false)
                                    Shared_Preferences.clearPref()
                                    val intent = Intent(mainActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                    mainActivity.finish()

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

    private fun dashboard(urcid: String, keyword: String="") {


        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.dashboard(
                DashboardRequestModel(urc_id = urcid, keywords = keyword)
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let { itProfileInfo ->

                                with(fragmentHomeBinding){

                                    if (itProfileInfo.data.topSellingProducts.isNullOrEmpty()){
                                        llTopSellingName.visibility=View.GONE
                                    }else{
                                        llTopSellingName.visibility=View.VISIBLE
                                        topsellingAdapter?.updateData(itProfileInfo.data.topSellingProducts as ArrayList<DataProductList>,itProfileInfo.productImageUrl)
                                    }
                                    if (itProfileInfo.data.featuredProducts.isNullOrEmpty()){
                                        llFeatureProductName.visibility=View.GONE
                                    }else{
                                        llFeatureProductName.visibility=View.VISIBLE
                                        featuredproductAdapter?.updateData(itProfileInfo.data.featuredProducts as ArrayList<DataProductList>,itProfileInfo.productImageUrl)
                                    }
                                    if (itProfileInfo.data.essentialProducts.isNullOrEmpty()){
                                        llEssentialName.visibility=View.GONE
                                    }else{
                                        llEssentialName.visibility=View.VISIBLE
                                        dailyproductAdapter?.updateData(itProfileInfo.data.essentialProducts as ArrayList<DataProductList>,itProfileInfo.productImageUrl)
                                    }
                                    if (itProfileInfo.data.category.isNullOrEmpty()){
                                        llCategoryName.visibility=View.GONE
                                    }else{
                                        llCategoryName.visibility=View.VISIBLE
                                        categoryAdapter?.updateData(itProfileInfo.data.category as ArrayList<Category>, itProfileInfo.categoryImageUrl)
                                    }

                                }

                            }

                            mainActivity.hideProgressDialog()
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

    private fun productCartList() {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.cartList()
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                resource.data.let {itResponse->

                                    if (itResponse?.status == true) {

                                        //cartCount=itResponse.data.cart.size
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

                                mainActivity.hideProgressDialog()
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
    override fun onClickCategory(position: Int, view: View, catId:Int, catName:String) {

        val bundle = Bundle()
        bundle.putString("viewalltype", "categoryProduct")
        bundle.putString("catName", catName)
        bundle.putInt("catId", catId)
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.nav_productlist,bundle)
    }

    override fun onClick(position: Int, view: View, id:Int) {
        val bundle = Bundle()
        bundle.putInt("productid", id)
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.nav_productdetails,bundle)
    }

}