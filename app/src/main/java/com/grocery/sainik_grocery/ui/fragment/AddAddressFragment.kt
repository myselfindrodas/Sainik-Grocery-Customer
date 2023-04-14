package com.grocery.sainik_grocery.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.address.AddAddressRequestModel
import com.grocery.sainik_grocery.data.model.address.address_list.Addres
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentAddAddressBinding
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel
import java.io.Serializable

class AddAddressFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentAddAddressBinding: FragmentAddAddressBinding
    private lateinit var viewModel: CommonViewModel
    private var addressType = ""
    var addressData: Addres? = null
    var isEdited = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAddAddressBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_address, container, false)
        val root = fragmentAddAddressBinding.root
        mainActivity = activity as MainActivity

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm
        val intent = arguments
        if (intent != null && intent.containsKey("viewtype")) {
            addressData = getDataSerializable(intent, "data", Addres::class.java)
            println(addressData!!.houseNo)
            isEdited = true
        }

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(fragmentAddAddressBinding) {

            topnav.tvNavtitle.text = "Add address"

            if (addressData != null) {
                addressData?.let { itAddressData ->
                    etHouseNo.setText(itAddressData.houseNo)
                    etApartmentName.setText(itAddressData.apartmentName)
                    etAreaDetails.setText(itAddressData.area)
                    etStreeDetails.setText(itAddressData.streetDetails)
                    etCity.setText(itAddressData.city)
                    etPinCode.setText(itAddressData.pincode)
                    btnAddAddress.text = "Edit Address"
                    topnav.tvNavtitle.text = "Edit address"
                    when (itAddressData.type) {
                        "Home" -> {
                            llenteraddress.visibility = View.GONE
                            changeTypeBackground(this, btnAddresshome)

                            addressType = "Home"
                        }
                        "Office" -> {
                            llenteraddress.visibility = View.GONE
                            changeTypeBackground(this, btnAddressoffice)
                            addressType = "Office"
                        }
                        else -> {
                            llenteraddress.visibility = View.VISIBLE
                            changeTypeBackground(this, btnOther)

                            etOtherType.setText(itAddressData.type)
                            addressType = "Other"
                        }
                    }

                }
            }



            if (HomeFragment.cartCount > 0) {
                topnav.tvCartCount.text = HomeFragment.cartCount.toString()
                topnav.cvCartCount.visibility = View.VISIBLE
            } else {
                topnav.cvCartCount.visibility = View.GONE
            }

            topnav.clCart.setOnClickListener {

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_cart)


            }
            topnav.btnBack.setOnClickListener {

                mainActivity.onBackPressedDispatcher.onBackPressed()
            }
            if (HomeFragment.cartCount > 0) {
                topnav.tvCartCount.text = HomeFragment.cartCount.toString()
                topnav.cvCartCount.visibility = View.VISIBLE
            } else {
                topnav.cvCartCount.visibility = View.GONE
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

            btnAddresshome.setOnClickListener {

                llenteraddress.visibility = View.GONE
                changeTypeBackground(this, btnAddresshome)

                addressType = "Home"

            }


            btnAddressoffice.setOnClickListener {

                llenteraddress.visibility = View.GONE
                changeTypeBackground(this, btnAddressoffice)
                addressType = "Office"

            }

            btnOther.setOnClickListener {

                llenteraddress.visibility = View.VISIBLE
                changeTypeBackground(this, btnOther)
                addressType = "Other"

            }

            btnAddAddress.setOnClickListener {
                if (!validate(fragmentAddAddressBinding)) {
                    return@setOnClickListener
                }
                if (addressType == "Other")
                    addressType = etOtherType.text.toString().trim()

                val addAddressRequestModel = if (isEdited) {
                    AddAddressRequestModel(
                        addressData?.id!!,
                        etHouseNo.text.toString().trim(),
                        etApartmentName.text.toString().trim(),
                        etStreeDetails.text.toString().trim(),
                        etAreaDetails.text.toString().trim(),
                        etCity.text.toString().trim(),
                        etPinCode.text.toString().trim(),
                        addressType
                    )
                } else {
                    AddAddressRequestModel(
                        0,
                        etHouseNo.text.toString().trim(),
                        etApartmentName.text.toString().trim(),
                        etStreeDetails.text.toString().trim(),
                        etAreaDetails.text.toString().trim(),
                        etCity.text.toString().trim(),
                        etPinCode.text.toString().trim(),
                        addressType
                    )
                }
                if (isEdited) {
                    postEditAddress(it, addAddressRequestModel)
                } else {
                    postAddAddress(it, addAddressRequestModel)
                }
            }

        }
    }

    private fun changeTypeBackground(
        mFragmentAddAddressBinding: FragmentAddAddressBinding,
        button: AppCompatButton
    ) {

        with(mFragmentAddAddressBinding) {
            btnAddresshome.setBackgroundResource(R.drawable.rounded_orange_border)
            btnAddressoffice.setBackgroundResource(R.drawable.rounded_orange_border)
            btnOther.setBackgroundResource(R.drawable.rounded_orange_border)
            btnAddresshome.setTextColor(ContextCompat.getColor(mainActivity, R.color.orange))
            btnAddressoffice.setTextColor(ContextCompat.getColor(mainActivity, R.color.orange))
            btnOther.setTextColor(ContextCompat.getColor(mainActivity, R.color.orange))
            button.setTextColor(ContextCompat.getColor(mainActivity, R.color.white))
            button.setBackgroundResource(R.drawable.rounded_orange_bg_btn)

        }
    }

    private fun <T : Serializable?> getDataSerializable(
        @Nullable bundle: Bundle?,
        @Nullable key: String?,
        clazz: Class<T>
    ): T? {
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                return bundle.getSerializable(key, clazz)
            } else {
                try {
                    return bundle.getSerializable(key) as T?
                } catch (ignored: Throwable) {
                }
            }
        }
        return null
    }

    private fun validate(mFragmentAddAddressBinding: FragmentAddAddressBinding): Boolean {
        with(mFragmentAddAddressBinding) {
            if (etHouseNo.text.toString().isEmpty()) {
                Toast.makeText(mainActivity, "Enter House no.", Toast.LENGTH_SHORT).show()
                etHouseNo.requestFocus()
                return false
            } else if (etApartmentName.text.toString().isEmpty()) {
                Toast.makeText(mainActivity, "Enter Apertment name.", Toast.LENGTH_SHORT).show()
                etApartmentName.requestFocus()
                return false
            } else if (etStreeDetails.text.toString().isEmpty()) {
                Toast.makeText(mainActivity, "Enter Street details.", Toast.LENGTH_SHORT).show()
                etHouseNo.requestFocus()
                return false
            } else if (etAreaDetails.text.toString().isEmpty()) {
                Toast.makeText(mainActivity, "Enter Area Details.", Toast.LENGTH_SHORT).show()
                etAreaDetails.requestFocus()
                return false
            } else if (etCity.text.toString().isEmpty()) {
                Toast.makeText(mainActivity, "Enter city name.", Toast.LENGTH_SHORT).show()
                etCity.requestFocus()
                return false
            } else if (etPinCode.text.toString().isEmpty()) {
                Toast.makeText(mainActivity, "Enter Pin code.", Toast.LENGTH_SHORT).show()
                etPinCode.requestFocus()
                return false
            } else if (addressType.isEmpty()) {
                Toast.makeText(
                    mainActivity,
                    "Choose a nickname for this address",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            } else if (addressType.equals("Other", true) && etOtherType.text.toString().isEmpty()) {
                Toast.makeText(
                    mainActivity,
                    "Enter a nickname for this address",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            } else {
                return true
            }
        }
    }

    private fun postAddAddress(view: View, addAddressRequestModel: AddAddressRequestModel) {


        if (Utilities.isNetworkAvailable(mainActivity)) {


            viewModel.addAddress(
                addAddressRequestModel
            )
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
                                        navController.navigate(R.id.nav_address)
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

    private fun postEditAddress(view: View, addAddressRequestModel: AddAddressRequestModel) {


        if (Utilities.isNetworkAvailable(mainActivity)) {


            viewModel.editAddress(
                addAddressRequestModel
            )
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
                                        navController.navigate(R.id.nav_address)

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
}