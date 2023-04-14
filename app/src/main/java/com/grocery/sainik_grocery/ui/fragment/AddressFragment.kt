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
import androidx.recyclerview.widget.GridLayoutManager
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.address.address_list.Addres
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentAddressBinding
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.ui.adapter.AddressAdapter
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel

class AddressFragment : Fragment(), AddressAdapter.OnItemClickListener {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentAddressBinding: FragmentAddressBinding
    private var addressAdapter:AddressAdapter?=null
    //val addressModelArrayList: ArrayList<Addres> = arrayListOf()
    private lateinit var viewModel: CommonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAddressBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        val root = fragmentAddressBinding.root
        mainActivity = activity as MainActivity

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(fragmentAddressBinding) {
            topnav.tvNavtitle.text = "Address"
            addressAdapter = AddressAdapter(mainActivity, this@AddressFragment)
            rvAddress.adapter = addressAdapter
            rvAddress.layoutManager = GridLayoutManager(mainActivity, 1)

            topnav.btnBack.setOnClickListener {

                mainActivity.onBackPressedDispatcher.onBackPressed()
            }

            if (HomeFragment.cartCount>0) {
                topnav.tvCartCount.text = HomeFragment.cartCount.toString()
                topnav.cvCartCount.visibility=View.VISIBLE
            }else{
                topnav.cvCartCount.visibility=View.GONE
            }
            topnav.ivNotification.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_notification)


            }
            topnav.clCart.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_cart)

            }

            topnav.btnWishlist.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_wishlist)

            }

            btnContinue.setOnClickListener {
                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_addaddress)
            }
        }


        getAddressList()
    }

    override fun onClick(position: Int, view: View, mAddressModelArrayList: ArrayList<Addres>, isDelete:Boolean) {

       if (isDelete){
           deleteDialog(mAddressModelArrayList[position])
       }else {
           /*mAddressModelArrayList.forEach { item ->
               item.isPrimary = 0
           }
           mAddressModelArrayList[position].isPrimary = 1
           addressAdapter?.updateData(mAddressModelArrayList)*/
           primaryAddress(mAddressModelArrayList[position].id.toString())
       }

    }

    private fun deleteDialog(mAddressData: Addres){
        val builder = AlertDialog.Builder(mainActivity)
        builder.setTitle("Delete ${mAddressData.type}")
        builder.setMessage("Are you sure you delete ${mAddressData.type} from the list?")
        builder.setPositiveButton(
            "Yes"
        ) { dialog, which ->

            deleteAddress(mAddressData.id.toString())
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

                                        addressAdapter?.updateData(itResponse.data.address)

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

    private fun deleteAddress(id:String) {


        if (Utilities.isNetworkAvailable(mainActivity)) {


            viewModel.deleteAddress(id)
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.let {itResponse->

                                    if (itResponse.status) {
                                        getAddressList()
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

    private fun primaryAddress(id:String) {


        if (Utilities.isNetworkAvailable(mainActivity)) {


            viewModel.primaryAddress(id)
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.let {itResponse->

                                    if (itResponse.status) {
                                        getAddressList()

                                        Toast.makeText(
                                            mainActivity,
                                            itResponse.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        /*val builder = android.app.AlertDialog.Builder(mainActivity)
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
                                        alert.show()*/

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


}