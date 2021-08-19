package com.odukabdulbasit.bitcointicker.login.loginregisterui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.odukabdulbasit.bitcointicker.databinding.FragmentRegisterBinding
import com.odukabdulbasit.bitcointicker.login.loginregistermodels.LoginModel
import com.odukabdulbasit.bitcointicker.login.loginregistermodels.LoginViewModel


class RegisterFragment : Fragment() {
    
    private val viewModel: LoginViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, LoginViewModel.Factory(activity.application)).get(LoginViewModel::class.java)
    }

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
        binding.registerViewModel = viewModel
        binding.registerModel = LoginModel()


        return binding.root
    }

}