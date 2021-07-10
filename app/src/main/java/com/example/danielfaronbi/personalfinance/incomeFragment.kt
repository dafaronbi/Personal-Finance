package com.example.danielfaronbi.personalfinance


import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class incomeFragment : androidx.fragment.app.Fragment() {

    lateinit var myQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //save fragment view as variable
        val view = inflater.inflate(R.layout.fragment_income, container, false)

        //get text boxes and put in variables
        val date: TextView = view.findViewById(R.id.income_date)
        val source: EditText = view.findViewById(R.id.income_source)
        val amount: EditText = view.findViewById(R.id.income_amount)
        val description: EditText = view.findViewById(R.id.expense_amount)
        val extra: CheckBox = view.findViewById(R.id.income_extra)
        val message: TextView = view.findViewById(R.id.income_message)

        myQueue = Volley.newRequestQueue(this.context)

//        val url= "https://script.google.com/macros/library/d/1lGXqGyYoCGhj_iRyWSLI6Q79o4YU1kN69-mUx50KZaHONNKj93CzSg2w/118"



        //On click listener for date picker
        date.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(this.context, DatePickerDialog.OnDateSetListener { view, year, month, day ->

                // Display Selected date in textbox
                date.text = (month+1).toString() + "/" + day.toString() + "/" + year.toString()
                date.setTextColor(Color.parseColor("#000000"))
            }, year, month, day)

            dialog.show()



        }

        //post when submit
        view.findViewById<Button>(R.id.income_submit).setOnClickListener {


            //test json values
            val test =  JSONObject()

            test.put("type","income")
            test.put("source",source.text)
            test.put("amount",amount.text)
            test.put("description",description.text)
            test.put("date",date.text)
            if(extra.isChecked){
                test.put("extra","True")
            }
            else{
                test.put("extra","False")
            }
            context?.let {
                GlobalClass().volley_post(test, it)
            }

        }



        // Inflate the layout for this fragment
        return view
    }


}
