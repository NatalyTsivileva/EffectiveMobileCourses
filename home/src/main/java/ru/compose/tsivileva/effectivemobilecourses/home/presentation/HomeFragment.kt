package ru.compose.tsivileva.effectivemobilecourses.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.compose.tsivileva.effectivemobilecourses.home.R

class HomeFragment : Fragment(R.layout.fr_home) {

    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnData()
        subscribeOnError()
    }

    private fun subscribeOnData(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect {
                    Log.d("TAG","${it.count()}")
                }
            }
        }
    }

    private fun subscribeOnError() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect {
                    if (it!=null) {
                        val toast = Toast.makeText(
                            requireContext(),
                            it,
                            Toast.LENGTH_LONG
                        )
                        toast.show()
                    }
                }
            }
        }
    }
}

