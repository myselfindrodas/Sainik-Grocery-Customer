package com.grocery.sainik_grocery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.data.model.notificationmodel.NotificationResponseModel.DataNotification
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotificationAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater
    private var notificationListModelArrayList: ArrayList<DataNotification> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClick(position: Int, view: View, id: Int, s: String, mNotificationListModelArrayList: ArrayList<DataNotification>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.rv_notification, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        holder.tvTitle.text = notificationListModelArrayList[position].title
        holder.tvDescriptions.text = notificationListModelArrayList[position].description

        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val output = SimpleDateFormat("dd-MM-yyyy")
        var d: Date? = null
        try {
            d = input.parse(notificationListModelArrayList[position].createdAt.toString())
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val formatted: String = output.format(d)


        holder.tvTitle.text = formatted
        holder.tvDescriptions.text = notificationListModelArrayList[position].description


        holder.itemView.rootView.setOnClickListener {
            onItemClickListener.onClick(position, it,notificationListModelArrayList[position].id,
                notificationListModelArrayList[position].title, notificationListModelArrayList)

        }


    }




    fun updateData(mNotificationModelArrayList: List<DataNotification>){
        notificationListModelArrayList= mNotificationModelArrayList as ArrayList<DataNotification>
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return notificationListModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvDescriptions: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDescriptions = itemView.findViewById(R.id.tvDescriptions)
        }
    }
}