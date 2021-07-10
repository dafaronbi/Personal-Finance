package com.example.danielfaronbi.personalfinance

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_budget.*
import org.json.JSONArray
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [budgetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class budgetFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_budget, container, false)
        val global_var:GlobalClass = this.activity?.application as GlobalClass

        val b_totalBudgeted: TextView = view.findViewById(R.id.b_totalBudgetedQ)
        val b_totalSpent:TextView = view.findViewById(R.id.b_totalSpentQ)

        val b_recycler:RecyclerView = view.findViewById(R.id.b_recycler)

        var rAdapter:budget_recyle_adapter = budget_recyle_adapter(JSONArray(), this.requireContext())

        if(global_var.received_data.length() != 0 ) {

            b_totalBudgeted.text = "$" + BigDecimal(global_var.received_data.get("b_totalBudgeted").toString().toDouble()
            ).setScale(
            2,
            RoundingMode.HALF_EVEN
            )
            b_totalSpent.text = "$" + BigDecimal(global_var.received_data.get("b_totalSpent").toString().toDouble()
            ).setScale(
            2,
            RoundingMode.HALF_EVEN
            )
            context?.let {
                rAdapter = budget_recyle_adapter(
                    global_var.received_data.getJSONArray("budget"),
                    it
                )
            }

        }
        else{
            b_totalBudgeted.text = "NOT FOUND"
            b_totalSpent.text = "NOT FOUND"
        }

        b_recycler.adapter = rAdapter
        b_recycler.layoutManager = LinearLayoutManager(this.context)

        val new_button:FloatingActionButton = view.findViewById(R.id.b_new)

        new_button.setOnClickListener {
            context?.let {
                val new_dialog:Dialog= Dialog(it)
                new_dialog.setContentView(R.layout.budget_add_dialog)
                new_dialog.show()

                val request =  JSONObject()

                val expense: TextInputEditText = new_dialog.findViewById(R.id.b_add_expense_text)
                val budgeted: TextInputEditText = new_dialog.findViewById(R.id.b_add_budgeted_text)
                val spent: TextInputEditText = new_dialog.findViewById(R.id.b_add_spent_text)
                val confirm:Button = new_dialog.findViewById(R.id.b_add_confirmButton)

                confirm.setOnClickListener {
                    request.put("type","budget")
                    request.put("action","add")
                    request.put("expense", expense.text)
                    request.put("budgeted", budgeted.text)
                    request.put("spent",spent.text)

                    GlobalClass().volley_post(request, it.context)

                    new_dialog.dismiss()
                }

            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment budgetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            budgetFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}