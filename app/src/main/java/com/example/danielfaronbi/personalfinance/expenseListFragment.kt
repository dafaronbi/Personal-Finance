package com.example.danielfaronbi.personalfinance

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.danielfaronbi.personalfinance.placeholder.PlaceholderContent
import java.util.ArrayList

/**
 * A fragment representing a list of Items.
 */
class expenseListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense_list_list, container, false)

        val global_var:GlobalClass = this.activity?.application as GlobalClass

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                var items: MutableList<PlaceholderContent.PlaceholderItem> = ArrayList()
                if(global_var.received_data.length() != 0 ) {
                    val jsonObject_array = global_var.received_data.getJSONArray("expense")

                    for (i in 0 until jsonObject_array.length()) {
                        val jObject = jsonObject_array.getJSONObject(i)
                        items.add(PlaceholderContent.PlaceholderItem((i+1).toString(), jObject.get("expense").toString(), "-$"+ jObject.get("cost").toString()))
                    }
                }
                else{
                    items = PlaceholderContent.ITEMS
                }
                adapter = expenseListAdapter(items)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            expenseListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}