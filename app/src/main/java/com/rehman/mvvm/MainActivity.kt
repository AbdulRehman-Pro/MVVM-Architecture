package com.rehman.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rehman.mvvm.databinding.ActivityMainBinding
import com.rehman.mvvm.db.Quote
import com.rehman.mvvm.db.QuoteDatabase
import com.rehman.mvvm.repositories.QuoteRepository
import com.rehman.mvvm.viewModels.MainViewModel
import com.rehman.mvvm.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getQuotes().observe(this) {
            binding.quotes = it.toString()
        }

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0, "This is testing", "Testing")
            mainViewModel.insertQuote(quote)
        }
    }
}