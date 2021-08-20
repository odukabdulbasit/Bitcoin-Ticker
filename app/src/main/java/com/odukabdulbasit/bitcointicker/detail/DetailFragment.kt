package com.odukabdulbasit.bitcointicker.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.odukabdulbasit.bitcointicker.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val coinDetail = DetailFragmentArgs.fromBundle(requireArguments()!!).selectedCoinDetail

        binding.detailmodel = coinDetail

        return binding.root
    }
}