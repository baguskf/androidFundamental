package com.example.androidfundamental.fragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfundamental.*
import com.example.androidfundamental.adapter.RvUserAdapter

import com.example.androidfundamental.databinding.FragmentFollowersBinding


class FollowersFragment : Fragment() {

    private val FollowersViewModel by viewModels<FollowersViewModel>()
    companion object {
        const val ARG_POSITION = "section_number"
        const val ARG_NAME = "name"
    }

    private lateinit var fragBinding: FragmentFollowersBinding


    private var position: Int? = null
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragBinding = FragmentFollowersBinding.inflate(inflater, container, false)
        return fragBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragBinding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_NAME)
        }


        if (position == 1) {
            FollowersViewModel.findFollower(username.toString())
        }else{
            FollowersViewModel.findFollowing(username.toString())
        }
        FollowersViewModel.followUser.observe(viewLifecycleOwner) {
            FollowData(it)
        }

        FollowersViewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }

    }

    private fun FollowData(listUser: List<ItemsItem>) {
        val adapter = RvUserAdapter(listUser)
        fragBinding.rvFollow.adapter = adapter

    }
    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            fragBinding.progressBar3.visibility = View.VISIBLE
        } else {
            fragBinding.progressBar3.visibility = View.GONE
        }
    }
}
