package com.grocery.sainik_grocery.data.repository

import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.add_order.AddOrderRequestModel
import com.grocery.sainik_grocery.data.model.addcard.AddcardRequestModel
import com.grocery.sainik_grocery.data.model.address.AddAddressRequestModel
import com.grocery.sainik_grocery.data.model.addtowishlist.AddWishlistRequestModel
import com.grocery.sainik_grocery.data.model.cartmodel.CartRequestModel
import com.grocery.sainik_grocery.data.model.categoryitem.CategoryRequestModel
import com.grocery.sainik_grocery.data.model.dashboardmodel.DashboardRequestModel
import com.grocery.sainik_grocery.data.model.login.LoginRequestModel
import com.grocery.sainik_grocery.data.model.orderpaymentmodel.OrderPaymentRequestModel
import com.grocery.sainik_grocery.data.model.product_list.ProductListRequestModel
import com.grocery.sainik_grocery.data.model.urcmodel.UrcRequestModel
import com.shyamfuture.tantujayarnbank.data.model.forget_password.ForgetPassRequestModel
import okhttp3.MultipartBody
import okhttp3.RequestBody


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun login(requestBody: LoginRequestModel) = apiHelper.login(requestBody)
    suspend fun urclist(requestBody: UrcRequestModel) = apiHelper.urclist(requestBody)
    suspend fun categoryall(requestBody: CategoryRequestModel) = apiHelper.categoryall(requestBody)
    suspend fun getAllProduct(requestBody: ProductListRequestModel,page:String) = apiHelper.getAllProduct(requestBody,page)
    suspend fun getProductDetails(id:String) = apiHelper.getProductDetails(id)
    suspend fun dashboard(requestBody: DashboardRequestModel) = apiHelper.dashboard(requestBody)
    suspend fun logout() = apiHelper.logout()
    suspend fun forgotpassword(requestBody: ForgetPassRequestModel) = apiHelper.forgotpassword(requestBody)
    suspend fun cartadd(requestBody: CartRequestModel) = apiHelper.cartadd(requestBody)
    suspend fun cartDelete(id:String) = apiHelper.cartDelete(id)
    suspend fun cartList() = apiHelper.cartList()
    suspend fun addressList() = apiHelper.addressList()
    suspend fun addAddress(requestBody: AddAddressRequestModel) = apiHelper.addAddress(requestBody)
    suspend fun editAddress(requestBody: AddAddressRequestModel) = apiHelper.editAddress(requestBody)
    suspend fun deleteAddress(id:String) = apiHelper.deleteAddress(id)
    suspend fun primaryAddress(id:String) = apiHelper.primaryAddress(id)
    suspend fun addtoWishlist(requestBody: AddWishlistRequestModel) = apiHelper.addtoWishlist(requestBody)
    suspend fun wishlist() = apiHelper.wishlist()
    suspend fun wishlistDelete(id:String) = apiHelper.wishlistDelete(id)
    suspend fun notificationlist() = apiHelper.notificationlist()
    suspend fun addpaymentcard(requestBody: AddcardRequestModel) = apiHelper.addpaymentcard(requestBody)
    suspend fun paymentcardlist() = apiHelper.paymentcardlist()
    suspend fun paymentcardPrimary(id:String) = apiHelper.paymentcardPrimary(id)
    suspend fun paymentcardDelete(id:String) = apiHelper.paymentcardDelete(id)
    suspend fun addOrderDetails(requestBody: AddOrderRequestModel) = apiHelper.addOrderDetails(requestBody)
    suspend fun orderSummeryDetails() = apiHelper.orderSummeryDetails()
    suspend fun myOrderList() = apiHelper.myOrderList()
    suspend fun orderDetails(id:String) = apiHelper.orderDetails(id)
    suspend fun getSupportAndFAQ(id:String) = apiHelper.getSupportAndFAQ(id)
    suspend fun orderpayment(requestBody: OrderPaymentRequestModel) = apiHelper.orderpayment(requestBody)

    suspend fun profileupdate(
        name: RequestBody,
        last_name: RequestBody,
        phone: RequestBody,
        gender: RequestBody,
        birthday: RequestBody,
        password: RequestBody,
        old_password: RequestBody,
        part: MultipartBody.Part) = apiHelper.profileupdate(name, last_name, phone, gender, birthday, password, old_password, part)

    suspend fun profileget() = apiHelper.profileget()
    suspend fun filterResponse() = apiHelper.filterResponse()
}