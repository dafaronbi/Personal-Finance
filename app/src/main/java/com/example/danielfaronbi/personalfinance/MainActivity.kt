package com.example.danielfaronbi.personalfinance

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: homeFragment
    private lateinit var incomeFragment: incomeFragment
    private lateinit var expenseFragment: expenseFragment
    private lateinit var historyFragment: historyFragment
    private lateinit var budgetFragment: budgetFragment
    private var current_menu = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        displayScreen(-1)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //update all months to have same value
        val request = JSONObject()
        request.put("type","update")
        GlobalClass().volley_post(request, this)

        //get data from spreadsheet
        get_request()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    fun get_request(){
        val myQueue:RequestQueue = Volley.newRequestQueue(this)
        val url= "https://script.google.com/macros/s/AKfycbx6UvcAycDplhSkZMmN3CZPbfu_QXYBHk9tQArj46nP2aTVImyoZ6GjEbEssD_Rt6KM/exec"

        //create bar showing progress
        val progressBar:ProgressBar = findViewById(R.id.loading_bar)
        progressBar.visibility = android.view.View.VISIBLE

        val request = JsonObjectRequest(
            Request.Method.GET,url,null, { response ->
                var global_var:GlobalClass = this.application as GlobalClass

                global_var.received_data = response
                displayScreen(current_menu)
                progressBar.visibility = android.view.View.INVISIBLE



            },
            { error ->
                //json would be empty
                displayScreen(current_menu)
                //destroy progress bar
                progressBar.visibility = android.view.View.INVISIBLE
            })

        myQueue.add(request)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_refresh -> {
                get_request()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun displayScreen(id: Int){

                when (id) {
                 R.id.nav_home -> {
                    homeFragment = homeFragment()
                     supportFragmentManager
                         .beginTransaction()
                         .replace(R.id.frame_layout, homeFragment)
                         .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                         .commit()
                }
                R.id.nav_income -> {
                    incomeFragment = incomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, incomeFragment)
                        .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.nav_expense -> {
                    expenseFragment = expenseFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, expenseFragment)
                        .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.nav_history -> {
                    historyFragment = historyFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, historyFragment)
                        .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                    R.id.nav_budget -> {
                        budgetFragment = budgetFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, budgetFragment)
                            .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                else -> {
                    homeFragment = homeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeFragment)
                        .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        current_menu = item.itemId
        // Handle navigation view item clicks here.
        displayScreen(item.itemId)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
