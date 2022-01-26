package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.example.contacts.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFirstStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val detailsContainer = binding.secondContainer
        if (detailsContainer != null) {
            if (isFirstStart) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add(binding.firstContainer.id, ListFragment::class.java, null)
                    add(detailsContainer.id, DetailFragment::class.java, null)
                }
                isFirstStart = false
            }
        } else {
            if (isFirstStart) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add(binding.firstContainer.id, ListFragment::class.java, null)
                }
                isFirstStart = false
            }
        }

        supportFragmentManager
            .setFragmentResultListener(ListFragment.SHOW_DETAILS_REQUEST_KEY, this) { _, bundle ->
                val itemId = bundle.getInt(ListFragment.BUNDLE_KEY)
                Log.e("MainActivity", "Contact id: $itemId")
                if (detailsContainer != null) {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(detailsContainer.id, DetailFragment::class.java, bundleOf(DetailFragment.ARG_CONTACT_ID to itemId))
                    }
                } else {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(binding.firstContainer.id, DetailFragment::class.java, bundleOf(DetailFragment.ARG_CONTACT_ID to itemId))
                        addToBackStack(null)
                    }
                }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putBoolean(IS_NOT_FIRST_START_KEY, isFirstStart)
        }
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val IS_NOT_FIRST_START_KEY = "IS_NOT_FIRST_START_KEY"
    }
}
