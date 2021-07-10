package com.example.danielfaronbi.personalfinance

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import org.json.JSONObject

class budget_recyle_adapter(private val jList:JSONArray, private val ct:Context): RecyclerView.Adapter<budget_recyle_adapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): budget_recyle_adapter.ViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.budget_recycler_adapter, p0, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: budget_recyle_adapter.ViewHolder, position: Int) {
        val item = jList.getJSONObject(position)
        holder.expense.text = item.get("expense").toString()
        holder.budgeted.setText(item.get("budgeted").toString())
        holder.spent.setText(item.get("spent").toString())
        holder.edit_button.setOnClickListener {
            val request =  JSONObject()
            request.put("type","budget")
            request.put("action","update")
            request.put("expense", holder.expense.text)
            request.put("budgeted", holder.budgeted.text)
            request.put("spent",holder.spent.text)

            GlobalClass().volley_post(request, ct)
        }
        holder.delete_button.setOnClickListener {
            val request =  JSONObject()
            request.put("type","budget")
            request.put("action","delete")
            request.put("expense", holder.expense.text)
            request.put("budgeted", holder.budgeted.text)
            request.put("spent",holder.spent.text)

            GlobalClass().volley_post(request, ct)
        }
    }

    override fun getItemCount(): Int {
        return jList.length()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //create class variables for each view item
        val expense:TextView = view.findViewById(R.id.b_title)
        val budgeted:TextInputEditText = view.findViewById(R.id.b_budgeted)
        val spent:TextInputEditText = view.findViewById(R.id.b_spent)
        val edit_button:Button = view.findViewById(R.id.b_edit_button)
        val delete_button:Button = view.findViewById(R.id.b_delete_button)
    }
}