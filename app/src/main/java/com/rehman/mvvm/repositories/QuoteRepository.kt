package com.rehman.mvvm.repositories

import androidx.lifecycle.LiveData
import com.rehman.mvvm.db.Quote
import com.rehman.mvvm.db.QuoteDao

class QuoteRepository(private val quotesDao : QuoteDao) {


    fun getQuotes() : LiveData<List<Quote>>{
        return quotesDao.getQuotes()
    }
    suspend fun insertQuote(quote: Quote){
        quotesDao.insertQuote(quote)
    }
}