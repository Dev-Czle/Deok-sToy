package com.example.androidbaseuiunittest.infinitescrolltest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbaseuiunittest.R
import java.lang.NullPointerException

class InfiniteLoadingAdapter(private var data: ArrayList<InfiniteLoadingData?>? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            InfiniteLoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.infinite_data, parent, false)
            )
        } else {
            LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.loading_view, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return data?.get(position)?.let {
            VIEW_TYPE_ITEM
        } ?: VIEW_TYPE_LOADING
    }

    fun deleteData() {
        data?.removeAt(data?.lastIndex ?: throw NullPointerException("Null 이야 임마"))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is InfiniteLoadingViewHolder) {
            holder.bind(data?.get(position) ?: throw NullPointerException("null 이야 임마"))
        } else if (holder is LoadingViewHolder) {
            showLoading(holder, position)
        }
    }

    private fun showLoading(loadingViewHolder: LoadingViewHolder, position: Int) {

    }

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

}