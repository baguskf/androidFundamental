package com.example.androidfundamental.Favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfundamental.ItemsItem
import com.example.androidfundamental.ViewModelFactory
import com.example.androidfundamental.adapter.RvUserAdapter
import com.example.androidfundamental.database.NoteEntity
import com.example.androidfundamental.databinding.ActivityFavoriteBinding

class favoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val  favoriteViewModel by viewModels<favoriteViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = layoutManager


        favoriteViewModel.getUserFavorite().observe(this) {user : List<NoteEntity> ->
            val items = arrayListOf<ItemsItem>()
            user.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl)
                items.add(item)
            }
                binding.rvFavorite.adapter = RvUserAdapter(items)
        }

    }

}