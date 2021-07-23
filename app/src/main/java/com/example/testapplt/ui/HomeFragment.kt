package com.example.testapplt.ui

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testapplt.App
import com.example.testapplt.R
import com.example.testapplt.databinding.FragmentHomeBinding
import com.example.testapplt.utils.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        changingTheLogo()

        if( App.hashSettings.getString(App.TOKEN, null) != null){
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLogoutFragment(
                App.hashSettings.getString(App.TOKEN, "").toString()
            ))
        }

        binding.signIn.setOnClickListener {
            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            viewModel.login(email, password)
            viewModel.res.observe(viewLifecycleOwner, Observer {
                when(it){
                    is ResultWrapper.Success -> {
                        App.hashSettings.edit().putString(App.TOKEN, it.value.body.access_token).apply()

                        viewModel.displayPropertyDetails(it.value.body.access_token)
                        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
                            if(null != it){
                                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLogoutFragment(it))
                                viewModel.displayPropertyDetailsComplete()
                            }
                        })
                }
                    is ResultWrapper.Error -> {

                    }
                }
            })
        }
    }

    //изменяет логотип взависимости от состояния клавиатуры
    private fun changingTheLogo(){
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            if (_binding != null) {
                val r = Rect()
                binding.root.getWindowVisibleDisplayFrame(r)
                val screenHeight: Int = binding.root.rootView.height

                val keypadHeight = screenHeight - r.bottom
                Log.d("TAG", "keypadHeight = $keypadHeight")
                if (keypadHeight > screenHeight * 0.15) { //0,15 достаточно для определения высоты клавиатуры.
                    // keyboard is opened
                    Log.e("MyActivity", "keyboard opened")
                    binding.logo.setImageResource(R.drawable.logo_name)

                } else {
                    // keyboard is closed
                    Log.e("MyActivity", "keyboard closed")
                    binding.logo.setImageResource(R.drawable.icon_img)

                }
            }
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}