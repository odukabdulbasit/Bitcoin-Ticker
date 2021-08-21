package com.odukabdulbasit.bitcointicker.listsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            if (viewModel.coinsDetail.value?.ath != null){
                findNavController().navigate(ListSearchFragmentDirections.actionListSearchFragmentToDetailFragment(viewModel.coinsDetail.value!!))

            }else{
                Toast.makeText(this.requireContext(), "Invalided ceses!", Toast.LENGTH_LONG).show()
            }
        })

        binding.coinsRecyclerView.adapter = adapter
        binding.srcButtton.setOnClickListener {

            if (!binding.searchEditText.text.isEmpty()){
                binding.srcButtton.visibility = View.INVISIBLE
                viewModel.searchingText(binding.searchEditText.text.toString())
                binding.searchEditText.text.clear()
            }

        }

        viewModel.searchedCoinsId.observe(this.viewLifecycleOwner, Observer {
            //it will update the recyclerview
            adapter.submitList(listOf(it))
        })
        return binding.root
    }

}