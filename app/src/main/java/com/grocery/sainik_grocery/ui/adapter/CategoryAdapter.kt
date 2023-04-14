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
import com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    private var imageURL: String=""
    private val inflater: LayoutInflater
    private var categoryModelArrayList: ArrayList<Category> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClickCategory(position: Int, view: View, catId:Int, catName:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.cat_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.tvCatname.text = categoryModelArrayList[position].category.name

        Picasso.get()
            .load(imageURL+"/"+categoryModelArrayList[position].category.image)
            .error(R.drawable.item)
            .placeholder(R.drawable.item)
            .into(holder.imgIcon)

//        holder.ivImg.setImageResource(productModelArrayList[position].image)
        holder.itemView.rootView.setOnClickListener {
            onItemClickListener.onClickCategory(position, it, categoryModelArrayList[position].categoryId, categoryModelArrayList[position].category.name)

        }
    }




    fun updateData(mCategoryModelArrayList: ArrayList<Category>,mImageURL:String){
        categoryModelArrayList= mCategoryModelArrayList
        imageURL=mImageURL
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return categoryModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCatname: TextView
        var imgIcon: ImageView

        init {
            tvCatname = itemView.findViewById(R.id.tvItemname)
            imgIcon = itemView.findViewById(R.id.ivImg)
        }
    }
}