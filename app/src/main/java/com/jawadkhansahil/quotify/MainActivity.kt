package com.jawadkhansahil.quotify

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jawadkhansahil.quotify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        getQuote()

        binding.nextButton.setOnClickListener {
            nextQuote()
        }

        binding.previousButton.setOnClickListener {
            prevoiusQuote()
        }

        binding.shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(EXTRA_TEXT, mainViewModel.getQuote().quote)
            startActivity(intent)
        }

    }

    fun setQuote(quote: Quote){
        binding.quoteText.text = quote.quote
        binding.authorName.text = quote.author
    }
    fun getQuote(){
        setQuote(mainViewModel.getQuote())
    }

    fun nextQuote(){
        setQuote(mainViewModel.nextQuote()!!)
    }

    fun prevoiusQuote(){
        setQuote(mainViewModel.previousQuote()!!)
    }
}