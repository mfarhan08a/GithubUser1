package com.mfarhan08a.githubuser.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfarhan08a.githubuser.ItemsItem
import com.mfarhan08a.githubuser.databinding.ActivityFavoriteUserBinding
import com.mfarhan08a.githubuser.ui.adapter.UserAdapter
import com.mfarhan08a.githubuser.ui.detail.DetailUserActivity
import com.mfarhan08a.githubuser.utils.ViewModelFactory

class FavoriteUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding

    private val favoriteUserViewModel by viewModels<FavoriteUserViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUserFavorite.layoutManager = layoutManager

        supportActionBar?.title = "Favorite Users"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteUserViewModel.getFavoritedUserList().observe(this) {user ->
            val items = ArrayList<ItemsItem>()
            user.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl!!)
                items.add(item)
            }
            setUserList(items)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUserList(listUsers: List<ItemsItem>) {
        val adapter = UserAdapter(listUsers)
        binding.rvUserFavorite.adapter = adapter

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
                val detailIntent = Intent(this@FavoriteUserActivity, DetailUserActivity::class.java)
                detailIntent.putExtra(DetailUserActivity.USERNAME, data.login)
                startActivity(detailIntent)
            }
        })
    }
}