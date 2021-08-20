package com.odukabdulbasit.bitcointicker

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.odukabdulbasit.bitcointicker.listsearch.ListSearchAdapter
import com.odukabdulbasit.bitcointicker.listsearch.ListSearchModel

@BindingAdapter("imageUrl")
fun loadImage(view : ImageView, url : String?){
    Glide
        .with(view.context)
        .load("$url")
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
        .into(view);
}

@BindingAdapter("listData")
fun bindRecyclerViewList(recyclerView: RecyclerView, coinIdety : List<ListSearchModel>?){

    val adapter = recyclerView.adapter as ListSearchAdapter?
    adapter?.submitList(coinIdety)
}




