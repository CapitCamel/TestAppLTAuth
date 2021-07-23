package com.example.testapplt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapplt.App
import com.example.testapplt.R
import com.example.testapplt.databinding.FragmentHomeBinding
import com.example.testapplt.databinding.FragmentLogoutBinding
import com.example.testapplt.utils.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutFragment : Fragment() {
    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.logout.setOnClickListener {
            //не совсем разобрался в api logout поэтому сделал просто через токен и SharedPreferences
            App.hashSettings.edit().putString(App.TOKEN, null).apply()
            this.findNavController().popBackStack(R.id.homeFragment, false)
        }
    }
}