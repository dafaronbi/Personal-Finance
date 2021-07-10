package com.example.danielfaronbi.personalfinance


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
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
class expenseFragment : androidx.fragment.app.Fragment(), AdapterView.OnItemSelectedListener{

    lateinit var myQueue: RequestQueue

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //save fragment view as variable
        val view = inflater.inflate(R.layout.fragment_expense, container, false)

        //get views and put them in variables
        val expense:EditText = view.findViewById(R.id.expense_expense)
        val amount:EditText = view.findViewById(R.id.expense_amount)
        val description:EditText = view.findViewById(R.id.expense_description)
        val card_spinner: Spinner = view.findViewById(R.id.expense_card)
        val type_spinner: Spinner = view.findViewById(R.id.expense_type)
        val date: TextView = view.findViewById(R.id.expense_date)
        val message: TextView = view.findViewById(R.id.expense_message)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val card_adapter = ArrayAdapter.createFromResource(this.context, R.array.credit_cards, android.R.layout.simple_spinner_item)
        val type_adapter = ArrayAdapter.createFromResource(this.context, R.array.expense_type, android.R.layout.simple_spinner_item)

        // Specify the layout to use when the list of choices appears
        card_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        // Apply the adapter to the spinner
        card_spinner.setAdapter(card_adapter)
        type_spinner.setAdapter(type_adapter)


        myQueue = Volley.newRequestQueue(this.context)

        val url= "https://script.google.com/macros/library/d/1lGXqGyYoCGhj_iRyWSLI6Q79o4YU1kN69-mUx50KZaHONNKj93CzSg2w/118"

        //On click listener for date picker
        date.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(this.context, DatePickerDialog.OnDateSetListener { view, year, month, day ->

                // Display Selected date in textbox
                date.text = (month+1).toString() + "/" + day.toString() + "/" +year.toString()
                date.setTextColor(Color.parseColor("#000000"))
            }, year, month, day)

            dialog.show()

        }

        //post when submit
        view.findViewById<Button>(R.id.expense_submit).setOnClickListener {


            //test json values
            val test =  JSONObject()

            test.put("type","expense")
            test.put("expense",expense.text)
            test.put("description",description.text)
            test.put("amount",amount.text)
            test.put("card",card_spinner.selectedItem.toString())
            test.put("etype",type_spinner.selectedItem.toString())
            test.put("date",date.text)

            context?.let {
                GlobalClass().volley_post(test, it)
            }
        }


        // Inflate the layout for this fragment
        return view
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
            //show item
            var item:String= parent.getItemAtPosition(pos).toString()
            Toast.makeText(parent.getContext(), item, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        //do nothing
    }



}
