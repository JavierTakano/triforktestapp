package com.example.triforktestapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.triforktestapp.R
import com.example.triforktestapp.databinding.BiggestRepoActivityBinding
import com.example.triforktestapp.viewmodel.BiggestRepoViewModel

class BiggestRepoActivity : AppCompatActivity() {
    private lateinit var binding: BiggestRepoActivityBinding

    private val biggestRepoViewModel: BiggestRepoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BiggestRepoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        biggestRepoViewModel.data.observe(this, Observer {
            binding.orgRepoSizeText.text = getString(R.string.biggest_repo_text, it)
        })

        binding.searchButton.setOnClickListener {
            val text = binding.inputOrgName.text.toString()
            if (text.isNotBlank()) {
                biggestRepoViewModel.getBiggestRepo(text)
                closeKeyBoard()
            }
        }

        binding.toEx1.setOnClickListener {
            this.onBackPressed()
        }

        binding.toEx3.setOnClickListener {
            val intent = Intent(this, OrgTotalActivity::class.java)
            startActivity(intent)
        }

    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}