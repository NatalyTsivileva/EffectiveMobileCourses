package ru.compose.tsivileva.effectivemobilecourses.home.presentation.adapter

import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course

interface OnItemClick {
    fun onFavoriteClick(course: Course)
}