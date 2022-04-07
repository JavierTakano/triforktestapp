package com.example.triforktestapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.triforktestapp.R
import com.example.triforktestapp.databinding.ReposByOrgActivityBinding
import com.example.triforktestapp.viewmodel.ReposByOrgViewModel

class ReposByOrgActivity : AppCompatActivity() {
    private lateinit var binding: ReposByOrgActivityBinding

    private val reposByOrgViewModel: ReposByOrgViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReposByOrgActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reposByOrgViewModel.data.observe(this) {
            if (it.data?.organization?.name.isNullOrBlank()) {
                binding.repoAmountText.text = getString(R.string.org_not_found_text)
            } else {
                binding.repoAmountText.text = getString(
                    R.string.repos_by_org_text, it.data?.organization?.name.toString(),
                    it.data?.organization?.repositories?.totalCount
                )
            }
        }

        binding.searchButton.setOnClickListener {
            val text = binding.inputOrgName.text.toString()
            if (text.isNotBlank()) {
                reposByOrgViewModel.updateReposOrg(text)
                closeKeyBoard()
            }
        }

        binding.toEx1.setOnClickListener {
            val intent = Intent(this, BiggestRepoActivity::class.java)
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