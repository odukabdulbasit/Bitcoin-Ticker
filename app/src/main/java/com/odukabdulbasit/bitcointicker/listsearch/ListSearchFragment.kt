package com.odukabdulbasit.bitcointicker.listsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.odukabdulbasit.bitcointicker.R
import com.odukabdulbasit.bitcointicker.databinding.FragmentListSearchBinding

class ListSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentListSearchBinding.inflate(inflater)

        binding.listsearchviewmodel = ListSearchViewModel()
        binding.lifecycleOwner = this

        return binding.root
    }

}