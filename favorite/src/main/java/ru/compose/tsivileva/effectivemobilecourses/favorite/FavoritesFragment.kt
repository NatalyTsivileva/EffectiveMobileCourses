package ru.compose.tsivileva.effectivemobilecourses.favorite

import androidx.recyclerview.widget.RecyclerView
import ru.compose.tsivileva.effectivemobilecourses.favorite.databinding.FrFavoritesBinding
import ru.compose.tsivileva.effectivemobilecourses.home.presentation.BaseCoursesFragment

class FavoritesFragment : BaseCoursesFragment<FrFavoritesBinding>(FrFavoritesBinding::inflate) {

    override fun loadFromNetwork() {
        loadFromCache()
    }

    override fun loadFromCache() {
        viewModel.loadFavoritesCourses()
    }

    override fun getListFromUi(): RecyclerView {
       return binding.list
    }


}