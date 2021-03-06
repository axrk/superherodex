package com.example.superherodex
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SuperHeroAdapter(private val model: SuperHeroViewModel) :
    RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {

    // Static attribute
    companion object {
        private var clickListener: ClickListener? = null
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView = view.findViewById(R.id.tv_sh_row)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.onItemClick(adapterPosition, v)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.sh_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        viewHolder.textView.text = model.getList().value?.get(position).toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = model.getList().value?.size ?: 0

    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        Companion.clickListener = clickListener
    }
}


