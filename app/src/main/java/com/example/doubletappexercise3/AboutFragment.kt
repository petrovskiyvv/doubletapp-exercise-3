package com.example.doubletappexercise3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.doubletappexercise3.databinding.AboutFragmentBinding

class AboutFragment : Fragment(R.layout.about_fragment) {
    private var binding: AboutFragmentBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AboutFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}