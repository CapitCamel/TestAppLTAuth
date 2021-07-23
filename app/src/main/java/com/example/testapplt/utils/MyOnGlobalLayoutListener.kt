package com.example.testapplt.utils

import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver
import com.example.testapplt.R
//
//class MyOnGlobalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener {
//    override fun onGlobalLayout() {
//        val r = Rect()
//        binding.root.getWindowVisibleDisplayFrame(r)
//        val screenHeight: Int = binding.root.rootView.height
//
//        val keypadHeight = screenHeight - r.bottom
//        Log.d("TAG", "keypadHeight = $keypadHeight")
//        if (keypadHeight > screenHeight * 0.15) { //0,15 достаточно для определения высоты клавиатуры.
//            // keyboard is opened
//            Log.e("MyActivity", "keyboard opened")
//            binding.logo.setImageResource(R.drawable.logo_name)
//
//        } else {
//            // keyboard is closed
//            Log.e("MyActivity", "keyboard closed")
//            binding.logo.setImageResource(R.drawable.icon_img)
//
//        }
//    }
//}