package com.grocery.sainik_grocery.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.wishlist_model.DataWishListItem
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentWishListBinding
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.ui.adapter.WishListAdapter
import com.grocery.sainik_grocery.utils.ItemOffsetDecoration
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel

class WishListFragment : Fragment(), WishListAdapter.OnItemClickListener {

    lateinit var fragmentWishListBinding: FragmentWishListBinding
    lateinit var mainActivity: MainActivity
    private lateinit var viewModel: CommonViewModel
    private var wishListAdapter:WishListAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentWishListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_wish_list, container, false)
        val root = fragmentWishListBinding.root
        mainActivity = activity as MainActivity

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wishListAdapter = WishListAdapter(mainActivity, this@WishListFragment)
        fragmentWishListBinding.rvWishlist.adapter = wishListAdapter
        fragmentWishListBinding.rvWishlist.layoutManager =
            GridLayoutManager(mainActivity, 2)
//        fragmentWishListBinding.rvWishlist.addOnScrollListener(recyclerOnScroll)
        //productAdapter?.updateData(categoryModelArrayList)
        val itemDecoration = ItemOffsetDecoration(mainActivity, R.dimen.photos_list_spacing1)
        fragmentWishListBinding.rvWishlist.addItemDecoration(itemDecoration)
        fragmentWishListBinding.topnav.tvNavtitle.text = "Wish List"



        fragmentWishListBinding.topnav.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }


        if (HomeFragment.cartCount>0) {
            fragmentWishListBinding.topnav.tvCartCount.text = HomeFragment.cartCount.toString()
            fragmentWishListBinding.topnav.cvCartCount.visibility=View.VISIBLE
        }else{
            fragmentWishListBinding.topnav.cvCartCount.visibility=View.GONE
        }


        fragmentWishListBinding.topnav.clCart.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_cart)


        }

        fragmentWishListBinding.topnav.ivNotification.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_notification)


        }


        wishList()
    }



    private fun wishList(){

        if (Utilities.isNetworkAvailable(mainActivity)) {


            viewModel.wishlist()
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.let {itResponse->

                                    if (itResponse.status) {

                                        wishListAdapter?.updateData(itResponse.data.wishlist.data,itResponse.productImageUrl)
                                        fragmentWishListBinding.tvsubtitle.text = "Total ${itResponse.data.totalData} products"
                                    } else {

                                        Toast.makeText(
                                            mainActivity,
                                            resource.data.message,
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

    override fun onClick(position: Int, view: View, id: Int, s: String, mWishListModelArrayList:ArrayList<DataWishListItem>, isDelete:Boolean) {

        if (isDelete){
            deleteDialog(mWishListModelArrayList[position])
        }else{

            val bundle = Bundle()
            bundle.putInt("productid", id)
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.nav_productdetails, bundle)
        }


    }


    private fun deleteDialog(mWishlistdata: DataWishListItem){
        val builder = AlertDialog.Builder(mainActivity)
        builder.setMessage("Are you sure you delete this item from the list?")
        builder.setPositiveButton(
            "Yes"
        ) { dialog, which ->

            deleteWishList(mWishlistdata.id.toString())
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



    private fun deleteWishList(id:String){

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.wishlistDelete(id)
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.let {itResponse->

                                    if (itResponse.status) {
                                        wishList()
                                        val builder = android.app.AlertDialog.Builder(mainActivity)
                                        builder.setMessage(itResponse.message)
                                        builder.setPositiveButton(
                                            "Ok"
                                        ) { dialog, which ->

                                            dialog.dismiss()
                                        }
                                        val alert = builder.create()
                                        alert.setOnShowListener { arg0 ->
                                            alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                                                .setTextColor(resources.getColor(R.color.orange))
                                        }
                                        alert.show()

                                    } else {

                                        Toast.makeText(mainActivity, resource.data.message, Toast.LENGTH_SHORT).show()

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




}