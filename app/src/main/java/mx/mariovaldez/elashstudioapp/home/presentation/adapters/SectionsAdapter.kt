package mx.mariovaldez.elashstudioapp.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.mariovaldez.elashstudioapp.databinding.ViewSectionBinding
import mx.mariovaldez.elashstudioapp.home.presentation.models.SectionUI

internal class SectionsAdapter(
    private val listener: (String) -> Unit,

) : RecyclerView.Adapter<SectionsAdapter.ViewHolder>() {

    private val sections: MutableList<SectionUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewSectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ).apply {
            val itemSize = parent.measuredWidth / 2
            root.layoutParams.width = itemSize
            root.layoutParams.height = itemSize
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sections[position])
    }

    override fun getItemCount(): Int = sections.size

    fun addSections(sections: List<SectionUI>) {
        this.sections.clear()
        this.sections.addAll(sections)
    }

    inner class ViewHolder(
        private val binding: ViewSectionBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(section: SectionUI) = with(binding) {
            iconImageView.setImageDrawable(section.icon)
            labelTextView.text = section.label
            containerCardView.setOnClickListener {
                listener(section.id)
            }
        }
    }
}

