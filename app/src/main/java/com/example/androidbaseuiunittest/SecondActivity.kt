package com.example.androidbaseuiunittest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_second.*

class SecondActivity : AppCompatActivity() {
    private val REQUEST_CODE_LOCALE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, LocaleActivity::class.java)
            val country: String = editTextUserInput.text.toString()
            intent.putExtra(LocaleActivity.EXTRA_COUNTRY, country)
            startActivityForResult(intent, REQUEST_CODE_LOCALE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_LOCALE -> {
                val locale = data?.getStringExtra(LocaleActivity.EXTRA_LOCALE)
                tvResultLocale.text = locale
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}