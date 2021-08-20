package com.odukabdulbasit.bitcointicker.listsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.bitcointicker.databinding.FragmentListSearchBinding


class ListSearchFragment : Fragment() {

    private val viewModel: ListSearchViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, ListSearchViewModel.Factory(activity.application)).get(ListSearchViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentListSearchBinding.inflate(inflater)

        binding.listsearchviewmodel = viewModel
        binding.lifecycleOwner = this
        binding.srcButtton.visibility = View.VISIBLE


        val adapter = ListSearchAdapter(ListSearchAdapter.OnClickListener{ coin ->

            findNavController().navigate(ListSearchFragmentDirections.actionListSearchFragmentToDetailFragment(coin))
        })

        binding.coinsRecyclerView.adapter = adapter
        binding.srcButtton.setOnClickListener {

            if (!binding.searchEditText.text.isEmpty()){
                viewModel.searchingText(binding.searchEditText.text.toString())
                binding.searchEditText.text.clear()
            }

        }

        viewModel.searchedCoinsId.observe(this.viewLifecycleOwner, Observer {
            //it will update the recyclerview
            adapter.submitList(listOf(it))
            binding.srcButtton.visibility = View.INVISIBLE
        })
        return binding.root
    }

}