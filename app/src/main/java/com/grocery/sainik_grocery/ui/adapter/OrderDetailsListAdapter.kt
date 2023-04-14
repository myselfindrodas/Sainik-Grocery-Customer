package com.grocery.sainik_grocery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.application.GlideApp
import com.grocery.sainik_grocery.data.model.my_order_list.Order
import com.grocery.sainik_grocery.data.model.order_details.OrderHistory
import com.grocery.sainik_grocery.data.model.productitem.ProductModel
import com.squareup.picasso.Picasso


class OrderDetailsListAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<OrderDetailsListAdapter.MyViewHolder>() {
    private var imageURL: String=""
    private val inflater: LayoutInflater
    private var orderHistoryModelArrayList: ArrayList<OrderHistory> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClick(position: Int, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.order_details_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {

            tvItemname.text = orderHistoryModelArrayList[position].product.name
            // holder.ivImg.setImageResource(productModelArrayList[position].productid)
            tvItemprice.text = "Price: ₹ " + orderHistoryModelArrayList[position].price.toString()
            tvItemAmtTxt.text = orderHistoryModelArrayList[position].productPackSize.size
            tvItemQtyTxt.text = "Qty: "+orderHistoryModelArrayList[position].quantity.toString()

            Picasso.get()
                .load(imageURL+"/"+orderHistoryModelArrayList[position].product.image)
                .error(R.drawable.item)
                .placeholder(R.drawable.item)
                .into(holder.ivImg)
            itemView.rootView.setOnClickListener {
                onItemClickListener.onClick(position, it)
            }

        }
    }

    fun updateData(mOrderHistoryModelArrayList: List<OrderHistory>, mImageURL:String){
        orderHistoryModelArrayList = mOrderHistoryModelArrayList as ArrayList<OrderHistory>
        imageURL=mImageURL
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return orderHistoryModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemname: TextView

         var ivImg: ImageView
        var tvItemprice: TextView
        var tvItemAmtTxt: TextView
        var tvItemQtyTxt: TextView

        init {
            tvItemname = itemView.findViewById(R.id.tvItemname)
            ivImg = itemView.findViewById(R.id.ivItemImg)
            tvItemprice = itemView.findViewById(R.id.tvItemprice)
            tvItemAmtTxt = itemView.findViewById(R.id.tvItemAmtTxt)
            tvItemQtyTxt = itemView.findViewById(R.id.tvItemQtyTxt)
        }
    }
}