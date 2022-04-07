package com.example.triforktestapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.triforktestapp.databinding.OrgTotalActivityBinding
import com.example.triforktestapp.viewmodel.OrgTotalViewModel

class OrgTotalActivity : AppCompatActivity() {
    private lateinit var binding: OrgTotalActivityBinding

    private val orgTotalViewModel: OrgTotalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OrgTotalActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orgTotalViewModel.data.observe(this) {
            binding.orgAmountCounter.text = it.data?.organizations?.userCount.toString()
        }

        binding.refreshButton.setOnClickListener {
            orgTotalViewModel.updateTotal()
        }

        binding.toEx2.setOnClickListener {
            this.onBackPressed()
        }
    }
}