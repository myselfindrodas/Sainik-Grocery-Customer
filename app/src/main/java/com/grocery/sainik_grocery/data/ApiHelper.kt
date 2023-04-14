package com.grocery.sainik_grocery.data

import com.grocery.sainik_grocery.data.model.add_order.AddOrderRequestModel
import com.grocery.sainik_grocery.data.model.addcard.AddcardRequestModel
import com.grocery.sainik_grocery.data.model.address.AddAddressRequestModel
import com.grocery.sainik_grocery.data.model.addtowishlist.AddWishlistRequestModel
import com.grocery.sainik_grocery.data.model.cartmodel.CartRequestModel
import com.grocery.sainik_grocery.data.model.categoryitem.CategoryRequestModel
import com.grocery.sainik_grocery.data.model.dashboardmodel.DashboardRequestModel
import com.shyamfuture.tantujayarnbank.data.ApiInterface
import com.grocery.sainik_grocery.data.model.login.LoginRequestModel
import com.grocery.sainik_grocery.data.model.orderpaymentmodel.OrderPaymentRequestModel
import com.grocery.sainik_grocery.data.model.product_list.ProductListRequestModel
import com.grocery.sainik_grocery.data.model.urcmodel.UrcRequestModel
import com.shyamfuture.tantujayarnbank.data.model.forget_password.ForgetPassRequestModel
import okhttp3.MultipartBody
import okhttp3.RequestBody


class ApiHelper(private val apiInterface: ApiInterface) {

    suspend fun login(requestBody: LoginRequestModel) = apiInterface.login(requestBody)
    suspend fun urclist(requestBody: UrcRequestModel) = apiInterface.urclist(requestBody)
    suspend fun categoryall(requestBody: CategoryRequestModel) = apiInterface.categoryall(requestBody)
    suspend fun getAllProduct(requestBody: ProductListRequestModel,page:String) = apiInterface.productList(requestBody,page)
    suspend fun getProductDetails(id:String) = apiInterface.getProductDetails(id)
    suspend fun dashboard(requestBody: DashboardRequestModel) = apiInterface.dashboard(requestBody)
    suspend fun logout() = apiInterface.logout()
    suspend fun forgotpassword(requestBody: ForgetPassRequestModel) = apiInterface.forgotpassword(requestBody)
    suspend fun cartadd(requestBody: CartRequestModel) = apiInterface.cartadd(requestBody)
    suspend fun cartDelete(id:String) = apiInterface.cartDelete(id)
    suspend fun cartList() = apiInterface.cartList()
    suspend fun addressList() = apiInterface.addressList()
    suspend fun addAddress(requestBody: AddAddressRequestModel) = apiInterface.addAddress(requestBody)
    suspend fun editAddress(requestBody: AddAddressRequestModel) = apiInterface.editAddress(requestBody)
    suspend fun deleteAddress(id:String) = apiInterface.addressDelete(id)
    suspend fun primaryAddress(id:String) = apiInterface.addressPrimary(id)
    suspend fun addtoWishlist(requestBody: AddWishlistRequestModel) = apiInterface.addtoWishlist(requestBody)
    suspend fun wishlist() = apiInterface.wishlist()
    suspend fun wishlistDelete(id:String) = apiInterface.wishlistDelete(id)
    suspend fun notificationlist() = apiInterface.notificationlist()
    suspend fun addpaymentcard(requestBody: AddcardRequestModel) = apiInterface.addpaymentcard(requestBody)
    suspend fun paymentcardlist() = apiInterface.paymentcardlist()
    suspend fun paymentcardPrimary(id:String) = apiInterface.paymentcardPrimary(id)
    suspend fun paymentcardDelete(id:String) = apiInterface.paymentcardDelete(id)
    suspend fun addOrderDetails(requestBody: AddOrderRequestModel) = apiInterface.addOrderDetails(requestBody)
    suspend fun orderSummeryDetails() = apiInterface.orderSummeryDetails()
    suspend fun myOrderList() = apiInterface.myOrderList()
    suspend fun orderDetails(id:String) = apiInterface.orderDetails(id)
    suspend fun getSupportAndFAQ(id:String) = apiInterface.getSupportAndFAQ(id)
    suspend fun orderpayment(requestBody: OrderPaymentRequestModel) = apiInterface.orderpayment(requestBody)

    suspend fun profileupdate(
        name: RequestBody,
        last_name: RequestBody,
        phone: RequestBody,
        gender: RequestBody,
        birthday: RequestBody,
        password: RequestBody,
        old_password: RequestBody,
        part: MultipartBody.Part) = apiInterface.profileupdate(name, last_name, phone, gender, birthday, password, old_password, part)


    suspend fun profileget() = apiInterface.profileget()
    suspend fun filterResponse() = apiInterface.filterResponse()
}