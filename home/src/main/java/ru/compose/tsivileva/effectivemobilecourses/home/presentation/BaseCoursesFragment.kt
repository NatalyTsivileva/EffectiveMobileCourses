package ru.compose.tsivileva.effectivemobilecourses.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.core.presentation.BaseFragment
import ru.compose.tsivileva.effectivemobilecourses.home.presentation.adapter.CoursesRecyclerAdapter
import ru.compose.tsivileva.effectivemobilecourses.home.presentation.adapter.OnItemClick
import kotlin.getValue

abstract class BaseCoursesFragment<T: ViewBinding>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
): BaseFragment<T>(inflate) {

    abstract fun loadFromNetwork()

    abstract fun loadFromCache()

    abstract fun getListFromUi(): RecyclerView

    protected val viewModel: HomeViewModel by viewModel()

    private var adapter: CoursesRecyclerAdapter? = null

    override fun onStart() {
        super.onStart()
        loadFromNetwork()
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

                loadFromCache()
            }
        })

        val listView = getListFromUi()
        listView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        listView.adapter = adapter
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