package ru.mirea.sipirecipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.FragmentScoped
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.databinding.ItemRecipeListBinding
import javax.inject.Inject

@FragmentScoped
class RecipeListAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<RecipeSummary, RecipeListAdapter.ViewHolder>(RecipeListDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    inner class ViewHolder(private val binding: ItemRecipeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecipeSummary, clickListener: ClickListener) {
            binding.data = data
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }

    class ClickListener @Inject constructor() {

        var onItemClick: ((RecipeSummary) -> Unit)? = null

        fun onClick(data: RecipeSummary) {
            onItemClick?.invoke(data)
        }
    }

    class RecipeListDiffCallback : DiffUtil.ItemCallback<RecipeSummary>() {

        override fun areItemsTheSame(oldItem: RecipeSummary, newItem: RecipeSummary): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: RecipeSummary, newItem: RecipeSummary): Boolean {
            return oldItem == newItem
        }

    }
}