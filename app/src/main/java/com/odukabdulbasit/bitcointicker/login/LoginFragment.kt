package com.odukabdulbasit.bitcointicker.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.odukabdulbasit.bitcointicker.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, LoginViewModel.Factory(activity.application)).get(LoginViewModel::class.java)
    }

   // val loginModel = LoginModel("","")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater)

        binding.loginModel = LoginModel()//loginModel
        binding.loginViewModel = viewModel

        return binding.root
    }


}