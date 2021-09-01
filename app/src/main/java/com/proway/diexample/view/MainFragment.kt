package com.proway.diexample.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proway.diexample.R
import com.proway.diexample.adapter.GithubUserAdapter
import com.proway.diexample.databinding.MainFragmentBinding
import com.proway.diexample.model.User
import com.proway.diexample.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private val adapter: GithubUserAdapter = GithubUserAdapter()

    private val observePage = Observer<Int> {
        viewModel.fetchUsers()
    }

    private val observeUsers = Observer<List<User>> { list ->

        binding.linearLayout.visibility = View.GONE
        adapter.refresh(list)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.bind(view)

        viewModel.repositories.observe(viewLifecycleOwner, observeUsers)
        viewModel.page.observe(viewLifecycleOwner, observePage)

        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.repositoriesRecyclerView.adapter = adapter

        setEventsForButtons()

    }

    private fun setEventsForButtons() {

        binding.repositoriesRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    callForMoreUsers()
                }
            }

        })

    }

    fun callForMoreUsers() {
        binding.linearLayout.visibility = View.VISIBLE
        viewModel.nextPage()
    }

}