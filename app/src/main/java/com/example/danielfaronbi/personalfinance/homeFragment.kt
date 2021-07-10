package com.example.danielfaronbi.personalfinance


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_expense.*
import java.lang.Math.round
import java.math.BigDecimal
import java.math.RoundingMode


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class homeFragment : androidx.fragment.app.Fragment() {

    lateinit var myQueue:RequestQueue

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Save view
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val checking_bal:TextView = view.findViewById(R.id.home_checkingBal)
        val spending_bal:TextView = view.findViewById(R.id.home_spendingBal)
        val savings_bal:TextView = view.findViewById(R.id.home_savingsBal)
        val saveup_bal:TextView = view.findViewById(R.id.home_saveupBal)


        var global_var:GlobalClass = this.activity?.application as GlobalClass

        if(global_var.received_data.length() != 0) {

            checking_bal.text = "$" + BigDecimal(
                global_var.received_data.get("currentBalance").toString().toDouble()
            ).setScale(
                2,
                RoundingMode.HALF_EVEN
            )
            spending_bal.text = "$" + BigDecimal(
                global_var.received_data.get("spendingBalance").toString().toDouble()
            ).setScale(
                2,
                RoundingMode.HALF_EVEN
            )
            savings_bal.text = "$" + BigDecimal(
                global_var.received_data.get("savingsBalance").toString().toDouble()
            ).setScale(
                2,
                RoundingMode.HALF_EVEN
            )
            saveup_bal.text = "$" + BigDecimal(
                global_var.received_data.get("saveUpBalance").toString().toDouble()
            ).setScale(
                2,
                RoundingMode.HALF_EVEN
            )
        }
        else{

            checking_bal.text = "NOT FOUND"
            spending_bal.text = "NOT FOUND"
            savings_bal.text = "NOT FOUND"
            saveup_bal.text = "NOT FOUND"

        }

        // Inflate the layout for this fragment
        return view
    }


}
