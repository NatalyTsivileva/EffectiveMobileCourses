package ru.compose.tsivileva.effectivemobilecourses.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.home.R
import ru.compose.tsivileva.effectivemobilecourses.home.databinding.FrHomeBinding
import ru.compose.tsivileva.effectivemobilecourses.home.presentation.adapter.CoursesRecyclerAdapter
import ru.compose.tsivileva.effectivemobilecourses.home.presentation.adapter.OnItemClick

class HomeFragment : Fragment(R.layout.fr_home) {

    private val viewModel: HomeViewModel by viewModel()

    private var adapter: CoursesRecyclerAdapter? = null

    private var _binding: FrHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        subscribeOnData()
        subscribeOnError()
    }

    private fun setupRecycler(){
        adapter = CoursesRecyclerAdapter(object : OnItemClick{
            override fun onFavoriteClick(course: Course) {
                if (course.hasLike) {
                    viewModel.removeCourseFromFavorite(course)
                }else{
                    viewModel.addCourseToFavorite(course)
                }
            }
        })

        binding.list.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.list.adapter = adapter
    }

    private fun subscribeOnData(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect {
                    adapter?.submitList(it)
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

