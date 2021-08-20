package com.odukabdulbasit.bitcointicker.listsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odukabdulbasit.bitcointicker.databinding.ListsearchListItemBinding

class ListSearchAdapter(val onClickListener: OnClickListener) : ListAdapter<ListSearchModel, ListSearchAdapter.ListSearchViewHolder>(DiffCallBack){

    object DiffCallBack : DiffUtil.ItemCallback<ListSearchModel>() {
        override fun areItemsTheSame(oldItem: ListSearchModel, newItem: ListSearchModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ListSearchModel, newItem: ListSearchModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class ListSearchViewHolder(private val binding: ListsearchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listSearchModel : ListSearchModel){
            binding.listsearchmodel = listSearchModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ListSearchAdapter.ListSearchViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListsearchListItemBinding.inflate(inflater, parent, false)
        return ListSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSearchAdapter.ListSearchViewHolder, position: Int) {
        val coin = getItem(position)
        holder.bind(coin)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(coin)
        }
    }

    class OnClickListener(val clickListener: (coin: ListSearchModel) -> Unit) {
        fun onClick(coinIdenty: ListSearchModel) = clickListener(coinIdenty)
    }

}