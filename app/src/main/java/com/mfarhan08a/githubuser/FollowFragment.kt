package com.mfarhan08a.githubuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfarhan08a.githubuser.databinding.FragmentFollowBinding


class FollowFragment : Fragment() {
    private val mainViewModel by viewModels<MainViewModel>()

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARG_POSITION)
        val username = arguments?.getString(ARG_USERNAME)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvUserFollow.layoutManager = layoutManager

        mainViewModel.findUserFollowers(username!!)
        mainViewModel.findUserFollowing(username)

        mainViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        if (position == 1) {
            mainViewModel.userFollowers.observe(viewLifecycleOwner) { userFollowers ->
                setUserFollowers(userFollowers)
            }
        } else {
            mainViewModel.userFollowing.observe(viewLifecycleOwner) { userFollowing ->
                setUserFollowing(userFollowing)
            }
        }
    }

    private fun setUserFollowers(userFollowers: List<ItemsItem>?) {
        val adapter = UserAdapter(userFollowers!!)
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
            }
        })
        binding.rvUserFollow.adapter = adapter
    }

    private fun setUserFollowing(userFollowing: List<ItemsItem>?) {
        val adapter = UserAdapter(userFollowing!!)
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
            }
        })
        binding.rvUserFollow.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}