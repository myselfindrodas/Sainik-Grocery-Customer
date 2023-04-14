package com.grocery.sainik_grocery.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.categoryitem.CategoryRequestModel
import com.grocery.sainik_grocery.data.model.categoryitem.categoryresponse.Category
import com.grocery.sainik_grocery.data.model.product_list.ProductListRequestModel
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.FragmentProductListBinding
import com.grocery.sainik_grocery.ui.MainActivity
import com.grocery.sainik_grocery.ui.adapter.CategoryListAdapter
import com.grocery.sainik_grocery.ui.adapter.ProductAdapter
import com.grocery.sainik_grocery.utils.ItemOffsetDecoration
import com.grocery.sainik_grocery.utils.Shared_Preferences
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel


class ProductListFragment : Fragment(), ProductAdapter.OnItemClickListener,
    CategoryListAdapter.OnItemClickListener {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentProductListBinding: FragmentProductListBinding
    private var productAdapter: ProductAdapter? = null
    private var categoryListAdapter: CategoryListAdapter? = null
    var category = ""
    var categoryId = 0
    var categoryName = ""
    val categoryModelArrayList: ArrayList<Category> = arrayListOf()
    private lateinit var viewModel: CommonViewModel

    var mIsLoading = false;
    var mIsLastPage = false;
    var mCurrentPage = 0;
    var pageSize = 10;

    companion object {
        var sortType: String = "popularity"
        var keyword: String = ""

        var attribute_id = ArrayList<Int>()
        var filterPrice:String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        fragmentProductListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        val root = fragmentProductListBinding.root
        mainActivity = activity as MainActivity

        val intent = arguments
        if (intent != null && intent.containsKey("viewalltype")) {
            category = intent.getString("viewalltype", "")
            if (category == "categoryProduct") {
                categoryId = intent.getInt("catId", 0)
                categoryName = intent.getString("c", "")
            }
        }

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        println("SORT TYPE   $sortType")
        viewModel = vm

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentProductListBinding.topnav.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }
        if (HomeFragment.cartCount > 0) {
            fragmentProductListBinding.topnav.tvCartCount.text = HomeFragment.cartCount.toString()
            fragmentProductListBinding.topnav.cvCartCount.visibility = View.VISIBLE
        } else {
            fragmentProductListBinding.topnav.cvCartCount.visibility = View.GONE
        }
        /* productCartList()

         viewModel.cartListItem.observe(viewLifecycleOwner, Observer {count->

         })*/
        if (category == "category") {
            fragmentProductListBinding.topnav.tvNavtitle.text = "Category"
            fragmentProductListBinding.tvsubtitle.text = "Category List"
            fragmentProductListBinding.llfilersort.visibility = View.GONE
            fragmentProductListBinding.btnSearch.visibility = View.GONE
            categoryListAdapter = CategoryListAdapter(mainActivity, this@ProductListFragment)
            fragmentProductListBinding.rvProductList.adapter = categoryListAdapter
            fragmentProductListBinding.rvProductList.layoutManager =
                GridLayoutManager(mainActivity, 2)
            val itemDecoration = ItemOffsetDecoration(mainActivity, R.dimen.photos_list_spacing1)
            fragmentProductListBinding.rvProductList.addItemDecoration(itemDecoration)
            getAllcategory(Shared_Preferences.getUrcid().toString())

        } else {
            fragmentProductListBinding.topnav.tvNavtitle.text = category
            fragmentProductListBinding.tvsubtitle.text = "Total 1000 products"
            fragmentProductListBinding.llfilersort.visibility = View.VISIBLE
            fragmentProductListBinding.btnSearch.visibility = View.VISIBLE

            productAdapter = ProductAdapter(mainActivity, this@ProductListFragment)
            fragmentProductListBinding.rvProductList.adapter = productAdapter
            fragmentProductListBinding.rvProductList.layoutManager =
                GridLayoutManager(mainActivity, 2)
            fragmentProductListBinding.rvProductList.addOnScrollListener(recyclerOnScroll)
            //productAdapter?.updateData(categoryModelArrayList)
            val itemDecoration = ItemOffsetDecoration(mainActivity, R.dimen.photos_list_spacing1)
            fragmentProductListBinding.rvProductList.addItemDecoration(itemDecoration)
            getAllProdutList(true, Shared_Preferences.getUrcid().toString())


            fragmentProductListBinding.btnSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {


                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    if (s?.length!! > 1) {
                        keyword = s.toString()
                        getAllProdutList(true, Shared_Preferences.getUrcid().toString())
                    } else {
                        keyword = ""
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })


        }


        fragmentProductListBinding.btnSort.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_sort_by)
        }

        fragmentProductListBinding.topnav.clCart.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_cart)


        }

        fragmentProductListBinding.topnav.ivNotification.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_notification)


        }


        fragmentProductListBinding.topnav.btnWishlist.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_wishlist)

        }

        fragmentProductListBinding.btnFilter.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_filter_by)
        }
    }

    private fun getAllcategory(urcid: String) {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.categoryall(
                CategoryRequestModel(urc_id = urcid)
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            resource.data?.let { itProfileInfo ->


                                categoryListAdapter?.updateData(itProfileInfo.data.category as ArrayList<Category>,itProfileInfo.categoryImageUrl)

                            }

                        }
                        Status.ERROR -> {
                            mainActivity.hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.status)


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

    val recyclerOnScroll = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            // number of visible items
            val visibleItemCount = recyclerView.layoutManager?.childCount;
            // number of items in layout
            val totalItemCount = recyclerView.layoutManager?.itemCount;
            // the position of first visible item
            val firstVisibleItemPosition =
                (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

            val isNotLoadingAndNotLastPage = !mIsLoading && !mIsLastPage;
            // flag if number of visible items is at the last
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount!! >= totalItemCount!!;
            // validate non negative values
            val isValidFirstItem = firstVisibleItemPosition >= 0;
            // validate total items are more than possible visible items
            val totalIsMoreThanVisible = totalItemCount >= pageSize;
            // flag to know whether to load more
            val shouldLoadMore =
                isValidFirstItem && isAtLastItem && totalIsMoreThanVisible && isNotLoadingAndNotLastPage

            if (shouldLoadMore) getAllProdutList(false, Shared_Preferences.getUrcid().toString());
        }
    }

    private fun getAllProdutList(isFirstPage: Boolean, urcid: String) {

        if (Utilities.isNetworkAvailable(mainActivity)) {

            mIsLoading = true
            if (isFirstPage)
                mCurrentPage = 1
            else
                mCurrentPage += 1


            val productListRequestModel = ProductListRequestModel(urc_id = urcid)
            productListRequestModel.keywords = keyword
            productListRequestModel.price = filterPrice
            productListRequestModel.attribute_id = attribute_id
            when (category) {
                "features" -> {
                    productListRequestModel.is_featured = 1

                    fragmentProductListBinding.topnav.tvNavtitle.text = "Featured products"
                }
                "essentials" -> {
                    productListRequestModel.is_essential = 1
                    fragmentProductListBinding.topnav.tvNavtitle.text = "Daily essential products"

                }
                "topselling" -> {
                    productListRequestModel.top_selling = 1
                    fragmentProductListBinding.topnav.tvNavtitle.text = "Top selling products"
                }
                "categoryProduct" -> {
                    productListRequestModel.category_id = categoryId
                    fragmentProductListBinding.topnav.tvNavtitle.text = categoryName
                }
            }

            when (sortType) {
                "alphabate" -> {
                    productListRequestModel.alphabetical = 1
                }
                "lowtohigh" -> {
                    productListRequestModel.is_low_price = 1
                }
                "hightolow" -> {
                    productListRequestModel.is_high_price = 1
                }
                "popularity" -> {
                    productListRequestModel.top_selling = 1
                }
            }

            viewModel.productList(
                productListRequestModel, mCurrentPage.toString()
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            resource.data?.let { itProfileInfo ->
                                if (isFirstPage) productAdapter?.updateData(itProfileInfo.data.productList.data,itProfileInfo.productImageUrl) else productAdapter?.addData(
                                    itProfileInfo.data.productList.data
                                )
                                mIsLoading = false
                                mIsLastPage =
                                    mCurrentPage == itProfileInfo.data.productList.lastPage
                                pageSize = itProfileInfo.data.productList.perPage
                                fragmentProductListBinding.tvsubtitle.text =
                                    "Total ${itProfileInfo.data.totalProduct} products"
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

    override fun onDestroyView() {
        super.onDestroyView()
        mIsLoading = false
        mIsLastPage = false
        mCurrentPage = 0
        sortType = "popularity"
        keyword = ""
        filterPrice = ""
        filterPrice = ""
        attribute_id = ArrayList<Int>()
    }

    override fun onClick(position: Int, view: View, id: Int) {
        val bundle = Bundle()
        bundle.putInt("productid", id)
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.nav_productdetails, bundle)

    }

    override fun onClickCategory(position: Int, view: View, catId: Int, catName: String) {

        val bundle = Bundle()
        bundle.putString("viewalltype", "categoryProduct")
        bundle.putString("catName", catName)
        bundle.putInt("catId", catId)
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.nav_productlist, bundle)
    }
}