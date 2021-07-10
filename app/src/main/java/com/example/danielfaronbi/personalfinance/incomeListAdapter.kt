package com.example.danielfaronbi.personalfinance

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.danielfaronbi.personalfinance.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class incomeListAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<incomeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_income_list, parent, false)
        return ViewHolder(view)

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
        holder.content2View.text = item.details
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.e_item_number)
        val contentView: TextView = view.findViewById(R.id.e_content)
        val content2View: TextView = view.findViewById(R.id.e_content2)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "' " + content2View.text
        }
    }

}