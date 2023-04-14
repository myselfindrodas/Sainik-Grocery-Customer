package com.grocery.sainik_grocery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.application.GlideApp
import com.grocery.sainik_grocery.data.model.my_order_list.Order
import com.grocery.sainik_grocery.data.model.notificationmodel.NotificationResponseModel.DataNotification
import com.grocery.sainik_grocery.data.model.productitem.ProductModel
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MyOrderListAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater
    private var orderModelArrayList: ArrayList<Order> = arrayListOf()
    private var imageUrl:String = ""
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClick(position: Int, view: View,mOrderData: Order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.myorder_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {

            tvItemname.text = orderModelArrayList[position].orderReferenceId

            val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val output = SimpleDateFormat("dd-MM-yyyy")
            var d: Date? = null
            try {
                d = input.parse(orderModelArrayList[position].createdAt.toString())
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val formatted: String = output.format(d)


            holder.tvOrderDate.text = formatted
            holder.tvItemStatus.text = when(orderModelArrayList[position].orderStatus){
                4->{
                    "Delivered"
                }
                5->{
                    "Canceled"
                }
                else->{
                    "On process"
                }
            }
            Picasso.get()
                .load(imageUrl)
                .error(R.drawable.item)
                .placeholder(R.drawable.item)
                .into(holder.ivImg)
            // holder.ivImg.setImageResource(productModelArrayList[position].productid)
            itemView.rootView.setOnClickListener {
                onItemClickListener.onClick(position, it, orderModelArrayList[position])

            }

        }
    }


    fun updateData(mOrderModelArrayList: List<Order>, mImageUrl:String){
        orderModelArrayList = mOrderModelArrayList as ArrayList<Order>
        imageUrl=mImageUrl
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return orderModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemname: TextView

         var ivImg: ImageView
        var tvItemStatus: TextView
        var tvOrderDate: TextView

        init {
            ivImg = itemView.findViewById(R.id.ivItemImg)
            tvItemname = itemView.findViewById(R.id.tvItemname)
            tvItemStatus = itemView.findViewById(R.id.tvItemStatus)
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate)
        }
    }
}