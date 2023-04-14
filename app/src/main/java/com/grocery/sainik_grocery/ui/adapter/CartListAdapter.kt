package com.grocery.sainik_grocery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.application.GlideApp
import com.grocery.sainik_grocery.data.model.cartlist_model.Cart
import com.squareup.picasso.Picasso


class CartListAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CartListAdapter.MyViewHolder>() {
    private var imageURL: String=""
    private val inflater: LayoutInflater
    private var cartModelArrayList: ArrayList<Cart> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)

        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClick(position: Int, view: View, clickType: Int,cartData: Cart, count: Int, textView: TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.cart_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {

            tvItemname.text = cartModelArrayList[position].product.name
            // holder.ivImg.setImageResource(productModelArrayList[position].productid)
            tvItemprice.text = "₹ " + cartModelArrayList[position].price.toString()
            tvPackSize.text = cartModelArrayList[position].product_pack_size.size
            /* itemView.rootView.setOnClickListener {
                 onItemClickListener.onClick(position, it)

             }*/

            Picasso.get()
                .load(imageURL+"/"+cartModelArrayList[position].product.image)
                .error(R.drawable.item)
                .placeholder(R.drawable.item)
                .into(holder.ivImg)
            count=cartModelArrayList[position].quantity
            tvCounter.text=count.toString()
            btnAdd.setOnClickListener {
                count++
                onItemClickListener.onClick(position, it, 1, cartModelArrayList[position],count, tvCounter)

                /*tvCounter.text = count.toString()*/
            }
            btnSub.setOnClickListener {
                if (count > 0)
                    count--
                onItemClickListener.onClick(position, it, 0,cartModelArrayList[position], count, tvCounter)

                /*tvCounter.text = count.toString()*/
            }
            ivDelete.setOnClickListener {

                onItemClickListener.onClick(position, it, 2,cartModelArrayList[position], count, tvCounter)
            }

        }
    }


    fun updateData(mCartModelArrayList: List<Cart>, mImageURL:String) {
        cartModelArrayList = mCartModelArrayList as ArrayList<Cart>
        imageURL=mImageURL
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return cartModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemname: TextView

         var ivImg: ImageView
        var tvItemprice: TextView
        var tvCounter: TextView
        var tvPackSize: TextView
        var ivDelete: ImageView
        var btnAdd: AppCompatButton
        var btnSub: AppCompatButton
        var count = 0

        init {
            btnAdd = itemView.findViewById(R.id.btnAdd)
            btnSub = itemView.findViewById(R.id.btnSub)
            tvCounter = itemView.findViewById(R.id.tvCounter)
            ivDelete = itemView.findViewById(R.id.ivDelete)
            ivImg = itemView.findViewById(R.id.ivItemImg)
            tvPackSize = itemView.findViewById(R.id.tvPackSize)
            tvCounter.text = count.toString()
            tvItemname = itemView.findViewById(R.id.tvItemname)
            //ivImg = itemView.findViewById(R.id.ivImg)
            tvItemprice = itemView.findViewById(R.id.tvItemprice)
        }
    }
}