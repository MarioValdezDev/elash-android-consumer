package mx.mariovaldez.elashstudioapp.sale.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.mariovaldez.elashstudioapp.databinding.ViewProductBinding
import mx.mariovaldez.elashstudioapp.sale.presentation.models.ProductUI

internal class ProductAdapter (
    private val listener: (ProductUI) -> Unit,

) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val products: MutableList<ProductUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun addSections(products: List<ProductUI>) {
        this.products.clear()
        this.products.addAll(products)
    }

    inner class ViewHolder(
        private val binding: ViewProductBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductUI) = with(binding) {
            Glide.with(itemView).load(product.image).into(iconImageView)
            priceTextView.text = product.price
            descriptionTextView.text = product.name
            containerCardView.setOnClickListener {
                listener(product)
            }
        }
    }
}
