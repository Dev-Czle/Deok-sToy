package com.example.androidbaseuiunittest.infinitescrolltest

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbaseuiunittest.R
import java.lang.IllegalArgumentException

class InfiniteLoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title = view.findViewById<TextView>(R.id.infiniteTitle)
    private val explain = view.findViewById<TextView>(R.id.infiniteExplain)
    private val pic = view.findViewById<ImageView>(R.id.infinitePic)

    fun bind(item: InfiniteLoadingData) {
        with(item) {
            this@InfiniteLoadingViewHolder.title.text = title
            this@InfiniteLoadingViewHolder.explain.text = explain
            this@InfiniteLoadingViewHolder.pic.setImageBitmap(
                image ?: throw IllegalArgumentException("Wrong Image")
            )
        }
    }
}

class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val loadingView = view.findViewById<ProgressBar>(R.id.infiniteLoadingView)
}