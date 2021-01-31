package com.example.androidbaseuiunittest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_locale.*

class LocaleActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COUNTRY = "country"
        const val EXTRA_LOCALE = "locale"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locale)

        val country = intent.getStringExtra(EXTRA_COUNTRY) ?: getString(R.string.no_country_extra)

        val localeStr: String = when (country.toLowerCase()) {
            "korea" -> getString(R.string.locale_korea)
            "japan" -> getString(R.string.locale_japan)
            "china" -> getString(R.string.locale_china)
            else -> getString(R.string.unknown_country, country)
        }
        tvLocale.text = localeStr

        btnOk.setOnClickListener { view ->
            Intent().apply {
                putExtra(EXTRA_LOCALE, tvLocale.text)
            }.also {
                setResult(Activity.RESULT_OK, it)
                finish()
            }
        }
    }
}