package com.jawadkhansahil.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    var quoteArrayList: Array<Quote> = emptyArray()
    var index: Int = 0

    init {
        quoteArrayList = getQuoteList()
    }

    private fun getQuoteList(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteArrayList[index]

    fun nextQuote() : Quote? {
        if (index < quoteArrayList.size - 1) {
            return quoteArrayList[++index]
        }
        return quoteArrayList[quoteArrayList.size-1]
    }

    fun previousQuote() : Quote? {
        if (index > 0) {
            return quoteArrayList[--index]
        }
        return quoteArrayList[0]
    }
}