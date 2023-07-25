package com.example.androidfundamental.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.androidfundamental.R
import com.example.androidfundamental.ViewModelFactory
import com.example.androidfundamental.adapter.SectionsPagerAdapter
import com.example.androidfundamental.database.NoteEntity
import com.example.androidfundamental.databinding.ActivityDetailBinding
import com.example.androidfundamental.response.ResponseDetailUser
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class Detail : AppCompatActivity(), OnClickListener {

    private lateinit var userBinding: ActivityDetailBinding
    private val DetailViewModel by viewModels<DetailViewModel>(){
        ViewModelFactory.getInstance(this)
    }
    private lateinit var favUser : NoteEntity
    var isFavorite : Boolean = false




    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

        const val TAG = "DetaiActivity"
        const val KEY = "Data"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(userBinding.root)


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)

        sectionsPagerAdapter.username = intent.getStringExtra(KEY).toString()

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val username = intent.getStringExtra(KEY)
        if(username !=null){
            DetailViewModel.findData(username)
        }

        DetailViewModel.detailUser.observe(this, { detail ->
            detailData(detail)
            favUser = NoteEntity(username.toString(),detail.avatarUrl)
        })

        DetailViewModel.isLoading.observe(this, {
            isLoading(it)
        })

        DetailViewModel.FindUserById(username.toString()).observe(this){user : NoteEntity?->
            if(user !=null){
                isFavorite = true
                userBinding.addtofav.setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.baseline_favorite_24
                ))
            }else{
                isFavorite = false
                userBinding.addtofav.setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.baseline_favorite_border_24
                ))
            }
        }
        userBinding.addtofav.setOnClickListener(this)

    }

    private fun detailData(detail : ResponseDetailUser){
        userBinding.apply{
            tvUsername.text = detail.login
            tvName.text = detail.name
            tvFollower.text = detail.followers.toString() + " Follower"
            tvFollowing.text = detail.following.toString() + " Following"
            Glide.with(this@Detail)
                .load(detail.avatarUrl)
                .into(photoDetail)

        }

    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            userBinding.progressBar2.visibility = View.VISIBLE
        } else {
            userBinding.progressBar2.visibility = View.GONE
        }
    }

        override fun onClick (view: View){
        if (view.id == R.id.addtofav){
            if(isFavorite){
                DetailViewModel.delete(favUser)
            }else{
                DetailViewModel.Insert(favUser)
            }
        }
    }


}
