package mx.mariovaldez.elashstudioapp.homeEmployee.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.mariovaldez.elashstudioapp.databinding.ViewSectionEmployeeBinding
import mx.mariovaldez.elashstudioapp.home.presentation.models.SectionUI

internal class SectionsEmployeeAdapter(
    private val listener: (String) -> Unit,

    ) : RecyclerView.Adapter<SectionsEmployeeAdapter.ViewHolder>() {

    private val sections: MutableList<SectionUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewSectionEmployeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
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
        private val binding: ViewSectionEmployeeBinding,
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

