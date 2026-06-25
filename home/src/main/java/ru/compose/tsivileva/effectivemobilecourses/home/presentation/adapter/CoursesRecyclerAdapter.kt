package ru.compose.tsivileva.effectivemobilecourses.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.compose.tsivileva.effectivemobilecourses.core.R
import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.uikit.databinding.ItemCourseBinding

class CoursesRecyclerAdapter: ListAdapter<Course, CoursesRecyclerAdapter.CoursesViewHolder>(
coursesDiffUtils
) {
    class CoursesViewHolder(val binding: ItemCourseBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Course){
            binding.title.text = item.title
            binding.text.text = item.text
            binding.image.setImageResource(item.image)
            binding.rating.text = item.rate
            binding.date.text = item.startDate
            binding.price.text = item.price

            val res = if (item.hasLike){
                ru.compose.tsivileva.effectivemobilecourses.uikit.R.drawable.ic_bookmark_fill
            }else{
                ru.compose.tsivileva.effectivemobilecourses.uikit.R.drawable.ic_bookmark
            }
            binding.favorite.setImageResource(res)
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): CoursesViewHolder {
        val inflater = LayoutInflater.from(group.context)
        val binding = ItemCourseBinding.inflate(inflater, group, false)
        return CoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object{
        val coursesDiffUtils = object: DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(p0: Course, p1: Course): Boolean {
                return p0.id==p1.id
            }

            override fun areContentsTheSame(p0: Course, p1: Course): Boolean {
                return p0==p1
            }

        }
    }
}