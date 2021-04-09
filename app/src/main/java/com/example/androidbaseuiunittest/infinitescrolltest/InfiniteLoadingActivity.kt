package com.example.androidbaseuiunittest.infinitescrolltest

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbaseuiunittest.R
import kotlinx.android.synthetic.main.activity_infinite_loading.*
import kotlin.random.Random

class InfiniteLoadingActivity : AppCompatActivity() {
    private lateinit var infiniteLoadingAdapter: InfiniteLoadingAdapter
    private val items = ArrayList<InfiniteLoadingData?>()
    private var isLoading = false
    private var defaultColorBitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infinite_loading)

        initData(defaultColorBitmap)
        populateData()
        initAdapter()
        initScrollListener()
    }

    private fun addData(bitmap: Bitmap) {
        val currentRandomIndex = Random.nextInt(0, Int.MAX_VALUE)
        items.add(
            InfiniteLoadingData(
                title = "current Title : $currentRandomIndex",
                explain = "current Explain : $currentRandomIndex",
                image = bitmap
            )
        )
    }

    private fun initData(bitmap: Bitmap) {
        for (i in 0 until 10) {
            addData(bitmap)
        }
    }

    private fun populateData() {
        for (i in 0..9) {
            addData(defaultColorBitmap)
        }
    }

    private fun initAdapter() {
        infiniteLoadingAdapter = InfiniteLoadingAdapter(items)
        infiniteViewRecyclerView.adapter = infiniteLoadingAdapter
        infiniteViewRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initScrollListener() {
        infiniteViewRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading && layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == items.size - 2) {
                    //리스트 마지막
                    loadMore()
                    isLoading = true
                }
            }
        })
    }

    private fun loadMore() {
        items.add(null)
        infiniteLoadingAdapter.notifyItemInserted(items.size - 1)
        val handler = Handler()
        handler.postDelayed({
            infiniteLoadingAdapter.deleteData()
            val scrollPosition: Int = items.size
            infiniteLoadingAdapter.notifyItemRemoved(scrollPosition)
            var currentSize = scrollPosition
            val nextLimit = currentSize + 10
            while (currentSize - 1 < nextLimit) {
                addData(defaultColorBitmap)
                currentSize++
            }
            infiniteLoadingAdapter.notifyDataSetChanged()
            isLoading = false
        }, 500)
    }
}