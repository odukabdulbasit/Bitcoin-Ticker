package com.odukabdulbasit.bitcointicker.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.odukabdulbasit.bitcointicker.R
import com.odukabdulbasit.bitcointicker.databinding.FragmentRegisterBinding
import com.odukabdulbasit.bitcointicker.login.LoginModel
import com.odukabdulbasit.bitcointicker.login.LoginViewModel


class RegisterFragment : Fragment() {

    private val registerViewModel : LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRegisterBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.registerModel = LoginModel()


        return binding.root
    }

}