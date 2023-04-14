package com.grocery.sainik_grocery.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.base.BaseActivity
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.orderpaymentmodel.OrderPaymentRequestModel
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.ActivityMainBinding
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel
import com.razorpay.*
import org.json.JSONObject

class MainActivity : BaseActivity(),
    NavController.OnDestinationChangedListener,
    PaymentResultWithDataListener,
    ExternalWalletListener  {

    lateinit var activityMainBinding: ActivityMainBinding
    var orderid = ""
    var refid = ""
    private lateinit var viewModel: CommonViewModel

    var mNavController: NavController? = null
    override fun resourceLayout(): Int {
        return R.layout.activity_main

    }

    companion object{
        var URCName = ""
    }
    override fun initializeBinding(binding: ViewDataBinding) {


        this.activityMainBinding = binding as ActivityMainBinding
    }

    override fun setFunction() {
        Checkout.preload(this)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        mNavController = findNavController(R.id.flFragment)

        mNavController?.addOnDestinationChangedListener(this)
        mNavController?.navigate(R.id.nav_home)
    }


    fun showProgressDialog() {
        activityMainBinding.rlLoading.visibility = View.VISIBLE
    }

    fun hideProgressDialog() {
        activityMainBinding.rlLoading.visibility = View.GONE

    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

        hideKeyboard()

    }

    override fun onDestroy() {
        findNavController(R.id.flFragment).removeOnDestinationChangedListener(this)
        super.onDestroy()
    }



    fun startPayment(orderId:String, amount:String, refId:String) {
        val co = Checkout()
        co.setKeyID("rzp_test_OaqqdS52ezrJuO")

        val totalamount: Double = amount.toDouble() * 100
        orderid = orderId
        refid = refId

        try {
            val options = JSONObject()
            options.put("name","Sainik Grocery Payment")
            options.put("description","Order Charges")
            options.put("image",R.mipmap.ic_launcher)
            options.put("theme.color", "#0F385A")
            options.put("currency","INR")
//            options.put("order_id", "order_DBJOWzybf0sJbb")
            options.put("amount",totalamount.toString())
            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(this,options)

        }catch (e: Exception){
            Toast.makeText(this,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }


    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, p0, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "paymentid-->"+p1?.data)
        orderpayment(orderid, p1?.paymentId.toString(), refid)

    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, p1, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Error-->"+p1)
    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {

        Toast.makeText(this, p0, Toast.LENGTH_SHORT).show()

        orderpayment(orderid, p1?.paymentId.toString(), refid)


    }



    private fun orderpayment(orderId: String, paymentid:String, refid:String){

        if (Utilities.isNetworkAvailable(this)) {
            viewModel.orderpayment(OrderPaymentRequestModel(order_id = orderId,
                payment_id = paymentid,
                transaction_id = refid))
                .observe(this) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                hideProgressDialog()
                                resource.data?.let {itResponse->

                                    if (itResponse.status) {
                                        Toast.makeText(this, itResponse.message, Toast.LENGTH_SHORT).show()
                                        mNavController?.navigate(R.id.nav_myorder)

                                    } else {
                                        Toast.makeText(this, resource.data.message, Toast.LENGTH_SHORT).show()
                                    }
                                }


                            }
                            Status.ERROR -> {
                                hideProgressDialog()
                                val builder = AlertDialog.Builder(this)
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
                                showProgressDialog()
                            }

                        }

                    }
                }

        } else {
            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()
        }


    }


}