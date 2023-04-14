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
import com.grocery.sainik_grocery.data.model.wishlist_model.DataWishListItem
import com.squareup.picasso.Picasso

class WishListAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<WishListAdapter.MyViewHolder>() {
    private var imageURL: String=""
    private val inflater: LayoutInflater
    private var wishListModelArrayList: ArrayList<DataWishListItem> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClick(position: Int, view: View, id: Int, s: String, mWishListModelArrayList:ArrayList<DataWishListItem>, isDelete:Boolean=false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.wish_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

           // holder.tvItemprice.visibility = View.VISIBLE
            holder.btnGo.visibility = View.VISIBLE
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(27, 0, 0, 0)
            holder.tvItemname.setLayoutParams(params)



        holder.tvItemname.text = wishListModelArrayList[position].urcProduct.product!!.name?:""

        Picasso.get()
            .load(imageURL+"/"+wishListModelArrayList[position].urcProduct.product.image)
            .error(R.drawable.item)
            .placeholder(R.drawable.item)
            .into(holder.ivImg)
        holder.tvPrice.text =
            " ₹ ${wishListModelArrayList[position].urcProduct.sellingPrice}/${wishListModelArrayList[position].urcProduct.product.unitsOfMeasurementTypes}"
        holder.tvPriceOld.text =
            "₹ ${wishListModelArrayList[position].urcProduct.product.price}/${wishListModelArrayList[position].urcProduct.product.unitsOfMeasurementTypes}"

        holder.tvPriceOld.paintFlags = holder.tvPriceOld.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

//        holder.ivImg.setImageResource(productModelArrayList[position].image)
//        holder.tvItemprice.text = productModelArrayList[position].productprice
        holder.itemView.rootView.setOnClickListener {
            onItemClickListener.onClick(position, it,wishListModelArrayList[position].urcProduct.productId,
                wishListModelArrayList[position].urcProduct.product!!.name, wishListModelArrayList, false)

        }

        if (wishListModelArrayList[position].urcProduct.discount>0) {
            holder.tvDiscount.text = "${wishListModelArrayList[position].urcProduct.discount}% Off"
            holder.lldiscount.visibility=View.VISIBLE
        }
        else
            holder.lldiscount.visibility=View.GONE
        holder.btnDeleteitem.setOnClickListener {

            onItemClickListener.onClick(position, it,wishListModelArrayList[position].urcProduct.productId,
                wishListModelArrayList[position].urcProduct.product!!.name, wishListModelArrayList, true)
        }

    }




    fun updateData(mProductModelArrayList: List<DataWishListItem>, mImageURL:String){
        wishListModelArrayList= mProductModelArrayList as ArrayList<DataWishListItem>
        imageURL=mImageURL
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return wishListModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemname: TextView
        var ivImg: ImageView
        var tvPrice: TextView
        var tvPriceOld: TextView
        var btnGo: AppCompatButton
        var tvDiscount: TextView
        var lldiscount: LinearLayout
        var btnDeleteitem: ImageView

        init {
            tvItemname = itemView.findViewById(R.id.tvItemname)
            ivImg = itemView.findViewById(R.id.ivImg)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            tvPriceOld = itemView.findViewById(R.id.tvPriceOld)
            btnGo = itemView.findViewById(R.id.btnGo)
            lldiscount = itemView.findViewById(R.id.lldiscount)
            tvDiscount = itemView.findViewById(R.id.tvDiscount)
            btnDeleteitem = itemView.findViewById(R.id.btnDeleteitem)
        }
    }
}