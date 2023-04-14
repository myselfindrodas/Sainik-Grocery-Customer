package com.grocery.sainik_grocery.ui.adapter

import android.content.Context
import android.view.Gravity
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
import com.grocery.sainik_grocery.data.model.categoryitem.categoryresponse.Category
import com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse.TopSellingProduct
import com.squareup.picasso.Picasso

class CategoryListAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CategoryListAdapter.MyViewHolder>() {
    private var imageURL: String=""
    private val inflater: LayoutInflater
    private var productModelArrayList: ArrayList<Category> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClickCategory(position: Int, view: View, catId:Int, catName:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        /*if (isCategory == true) {
            holder.lldiscount.visibility = View.GONE
            holder.tvItemprice.visibility = View.GONE
            holder.btnGo.visibility = View.GONE
            holder.tvItemname.gravity = Gravity.CENTER_HORIZONTAL
        } else {
            holder.lldiscount.visibility = View.VISIBLE
            holder.tvItemprice.visibility = View.VISIBLE
            holder.btnGo.visibility = View.VISIBLE
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(27, 0, 0, 0)
            holder.tvItemname.setLayoutParams(params)

        }*/
        holder.lldiscount.visibility = View.GONE
        holder.btnGo.visibility = View.GONE
        holder.tvItemname.gravity = Gravity.CENTER_HORIZONTAL
        holder.tvItemname.text = productModelArrayList[position].category.name

        Picasso.get()
            .load(imageURL+"/"+productModelArrayList[position].category.image)
            .error(R.drawable.item)
            .placeholder(R.drawable.item)
            .into(holder.ivImg)

//        holder.ivImg.setImageResource(productModelArrayList[position].image)
//        holder.tvItemprice.text = productModelArrayList[position].productprice
        holder.itemView.rootView.setOnClickListener {
            onItemClickListener.onClickCategory(position, it,productModelArrayList[position].categoryId,productModelArrayList[position].category.name)

        }
    }




    fun updateData(mProductModelArrayList: ArrayList<Category>,mImageURL:String){
        productModelArrayList= mProductModelArrayList
        imageURL=mImageURL
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return productModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemname: TextView
        var ivImg: ImageView
        var lldiscount: LinearLayout
        var btnGo: AppCompatButton

        init {
            tvItemname = itemView.findViewById(R.id.tvItemname)
            ivImg = itemView.findViewById(R.id.ivImg)
            lldiscount = itemView.findViewById(R.id.lldiscount)
            btnGo = itemView.findViewById(R.id.btnGo)
        }
    }
}