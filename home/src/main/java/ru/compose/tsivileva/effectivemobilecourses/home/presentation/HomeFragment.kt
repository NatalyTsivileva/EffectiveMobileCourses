package ru.compose.tsivileva.effectivemobilecourses.home.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.compose.tsivileva.effectivemobilecourses.home.databinding.FrHomeBinding

class HomeFragment : BaseCoursesFragment<FrHomeBinding>(FrHomeBinding::inflate) {

    override fun loadFromNetwork() {
        viewModel.loadCourses(refresh = true)
    }

    override fun loadFromCache() {
        viewModel.loadCourses(refresh = false)
    }

    override fun getListFromUi(): RecyclerView {
        return binding.list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sortButton.setOnClickListener {
           if(viewModel.isSortByAsc()){
               viewModel.setSortByDesc()
               loadFromCache()
           }else{
               viewModel.setSortByAsc()
               loadFromCache()
           }
        }
    }

}

