package ru.compose.tsivileva.effectivemobilecourses.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.compose.tsivileva.effectivemobilecourses.core.data.ICoursesRepository
import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course

class HomeViewModel(
    private val repository: ICoursesRepository
): ViewModel() {

    private val _data = MutableStateFlow<List<Course>>(emptyList())
    val data: StateFlow<List<Course>>
        get() = _data

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?>
        get() = _error

    val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        val msg = throwable.message
        if(!msg.isNullOrEmpty())
            _error.update { msg }
    }

    fun loadCourses(refresh: Boolean){
        viewModelScope.launch(exceptionHandler) {
            _data.update {  repository.getCourses(refresh) }
        }
    }

    fun loadFavoritesCourses(){
        viewModelScope.launch(exceptionHandler) {
            _data.update {  repository.getFavoriteCourses() }
        }
    }

    fun addCourseToFavorite(course: Course){
       viewModelScope.launch(exceptionHandler) {
           repository.addCourseToFavorite(course)
       }
    }

    fun removeCourseFromFavorite(course: Course){
        viewModelScope.launch(exceptionHandler) {
            repository.removeCourseFromFavorite(course)
        }
    }
}