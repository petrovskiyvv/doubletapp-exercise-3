package com.example.doubletappexercise3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.doubletappexercise3.databinding.FabFragmentBinding

class FabFragment : Fragment(R.layout.fab_fragment) {
    private var binding: FabFragmentBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FabFragmentBinding.bind(view)

        binding?.floatingActionButtonAddHabit?.setOnClickListener { navigate() }
    }

    private fun navigate() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fab_fragment, EditHabitFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}