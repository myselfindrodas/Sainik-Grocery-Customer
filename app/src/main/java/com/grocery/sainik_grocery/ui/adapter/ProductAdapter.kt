package com.grocery.sainik_grocery.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.application.GlideApp
import com.grocery.sainik_grocery.data.model.product_list.responsemodel.DataProductList
import com.squareup.picasso.Picasso

class ProductAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private var imageURL: String=""
    private val inflater: LayoutInflater
    private var productModelArrayList: ArrayList<DataProductList> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClick(position: Int, view: View, id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.lldiscount.visibility = View.VISIBLE
        holder.tvPrice.visibility = View.VISIBLE
        holder.btnGo.visibility = View.VISIBLE
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(25, 0, 0, 0)
        holder.tvItemname.setLayoutParams(params)
        //holder.tvPrice.setLayoutParams(params)



        Picasso.get()
            .load(imageURL+"/"+productModelArrayList[position].product.image)
            .error(R.drawable.item)
            .placeholder(R.drawable.item)
            .into(holder.ivImg)
        holder.tvItemname.text = productModelArrayList[position].product.name
        holder.tvPrice.text =
            " ₹ ${productModelArrayList[position].sellingPrice.toString()}/${productModelArrayList[position].product.units_of_measurement_types}"
        holder.tvPriceOld.text =
            "₹ ${productModelArrayList[position].product.price.toString()}/${productModelArrayList[position].product.units_of_measurement_types}"

        holder.tvPriceOld.paintFlags = holder.tvPriceOld.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        if (productModelArrayList[position].discount > 0) {
            holder.tvDiscount.text = "${productModelArrayList[position].discount}% Off"
            holder.lldiscount.visibility = View.VISIBLE
        } else
            holder.lldiscount.visibility = View.GONE


//        holder.ivImg.setImageResource(productModelArrayList[position].image)
//        holder.tvItemprice.text = productModelArrayList[position].productprice
        holder.itemView.rootView.setOnClickListener {
            onItemClickListener.onClick(position, it, productModelArrayList[position].id)
        }
    }


    fun updateData(mProductModelArrayList: List<DataProductList>, mImageURL:String) {
        productModelArrayList = mProductModelArrayList as ArrayList<DataProductList>
        imageURL=mImageURL
        notifyDataSetChanged()
    }

    fun addData(mProductModelArrayList: List<DataProductList>) {

        val lastIndex: Int = mProductModelArrayList.size
        productModelArrayList.addAll(mProductModelArrayList)
        notifyItemRangeInserted(lastIndex, mProductModelArrayList.size)

    }


    override fun getItemCount(): Int {
        return productModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemname: TextView
        var ivImg: ImageView
        var tvPrice: TextView
        var tvPriceOld: TextView
        var tvDiscount: TextView
        var lldiscount: LinearLayout
        var btnGo: AppCompatButton

        init {
            tvItemname = itemView.findViewById(R.id.tvItemname)
            ivImg = itemView.findViewById(R.id.ivImg)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            tvPriceOld = itemView.findViewById(R.id.tvPriceOld)
            tvDiscount = itemView.findViewById(R.id.tvDiscount)
            lldiscount = itemView.findViewById(R.id.lldiscount)
            btnGo = itemView.findViewById(R.id.btnGo)
        }
    }
}