package com.mfarhan08a.githubuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mfarhan08a.githubuser.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val mainViewModel by viewModels<MainViewModel>()

    companion object {
        const val USERNAME = "username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = if (Build.VERSION.SDK_INT > 33) {
            intent.getStringExtra(USERNAME)
        } else {
            @Suppress("DEPRECIATION")
            intent.getStringExtra(USERNAME)
        }

        if (username != null) {
            mainViewModel.findUserDetailByUsername(username)
            mainViewModel.userData.observe(this) { userData ->
                setUserData(userData)
            }
            mainViewModel.isLoading.observe(this) {
                showLoading(it)
            }
        }

        val sectionsPagerAdapter = SectionPagerAdapter(this)
        sectionsPagerAdapter.username = username.toString()
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    private fun setUserData(userData: DetailUserResponse) {
        binding.tvFullname.text = userData.name
        binding.tvUsername.text = userData.login
        binding.tvFollowers.text = resources.getString(R.string.followers, userData.followers)
        binding.tvFollowing.text = resources.getString(R.string.following, userData.following)
        Glide.with(this).load(userData.avatarUrl).into(binding.imgAvatar)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}