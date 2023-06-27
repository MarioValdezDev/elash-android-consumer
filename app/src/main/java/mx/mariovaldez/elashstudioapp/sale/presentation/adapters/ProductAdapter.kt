package mx.mariovaldez.elashstudioapp.sale.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.mariovaldez.elashstudioapp.R
import mx.mariovaldez.elashstudioapp.databinding.ViewProductBinding
import mx.mariovaldez.elashstudioapp.ktx.AddMoneyFormat
import mx.mariovaldez.elashstudioapp.ktx.context
import mx.mariovaldez.elashstudioapp.ktx.findColorInt
import mx.mariovaldez.elashstudioapp.ktx.findDrawable
import mx.mariovaldez.elashstudioapp.sale.presentation.models.ProductUI

internal class ProductAdapter(
    private val listener: (ProductUI) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val products: MutableList<ProductUI> = mutableListOf()

    private var lastProductPosition: Int = -1
    private var selectedProductPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position], position)
    }

    override fun getItemCount(): Int = products.size

    fun addProducts(products: List<ProductUI>) {
        this.products.clear()
        this.products.addAll(products)
    }

    inner class ViewHolder(
        private val binding: ViewProductBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductUI, position: Int) = with(binding) {
            Glide.with(itemView)
                .load(context.findDrawable(R.drawable.icv_logo))
                .circleCrop()
                .into(iconImageView)
                .onLoadFailed(context.findDrawable(R.drawable.icv_logo))
            priceTextView.text = product.price.toString().AddMoneyFormat()
            descriptionTextView.text = product.name
            containerCardView.setOnClickListener {
                listener(product)
                selectedProductPosition = position
                lastProductPosition = if (lastProductPosition == -1)
                    selectedProductPosition
                else {
                    notifyItemChanged(lastProductPosition)
                    selectedProductPosition
                }
                notifyItemChanged(selectedProductPosition)
            }
            containerCardView.setCardBackgroundColor(
                context.findColorInt(
                    if (position == selectedProductPosition) R.color.Primarylightcolor else R.color.white
                )
            )
            descriptionTextView.setTextColor(
                context.findColorInt(
                    if (position == selectedProductPosition) R.color.white else R.color.black
                )
            )
            priceTextView.setTextColor(
                context.findColorInt(
                    if (position == selectedProductPosition) R.color.white else R.color.black
                )
            )
        }
    }
}
