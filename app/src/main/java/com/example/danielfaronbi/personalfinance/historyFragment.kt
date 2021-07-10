package com.example.danielfaronbi.personalfinance


import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator


//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// *
// */
//class historyFragment : Fragment() {
//
//    private var myContext: FragmentActivity? = null
//
//    lateinit var historyIncomefragment: history_incomeFragment
//    lateinit var historyExpensefragment: history_expenseFragment
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view = inflater.inflate(R.layout.fragment_history, container, false)
//
//        //create view variables
//        val viewPager:ViewPager = view.findViewById(R.id.history_pager)
//        val tabLayout:TabLayout = view.findViewById(R.id.history_tablayout)
//        val incomeTab:TabItem = view.findViewById(R.id.history_incomeTab)
//        val expenseTab:TabItem = view.findViewById(R.id.history_expenseTab)
//
//
//        historyIncomefragment = history_incomeFragment()
//        historyExpensefragment = history_expenseFragment()
//
//        tabLayout.setupWithViewPager(viewPager)
//
//        val viewPagerAdapter = ViewPagerAdapter(myContext.supportFragmentManager,0)
//        viewPagerAdapter.addFragment(historyIncomefragment, "Income")
//        viewPagerAdapter.addFragment(historyExpensefragment, "Expense")
//        viewPager.setAdapter(viewPagerAdapter)

        // Inflate the layout for this fragment
//        return view
//
//    }
//
//    override fun onAttach(activity: Activity) {
//        myContext = activity as FragmentActivity
//        super.onAttach(activity)
//    }
//
//
//}
//
//
//private class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm) {
//
//    private val fragments: MutableList<Fragment> = ArrayList()
//    private val fragmentTitle: MutableList<String> = ArrayList()
//
//    fun addFragment(fragment: Fragment, title: String) {
//        fragments.add(fragment)
//        fragmentTitle.add(title)
//    }
//
//    override fun getItem(position: Int): Fragment {
//        return fragments[position]
//    }
//
//    override fun getCount(): Int {
//        return fragments.size
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return fragmentTitle[position]
//    }
//}

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class historyFragment : androidx.fragment.app.Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val tabLayout:TabLayout

        tabLayout = view.findViewById(R.id.tabLayout2)
        viewPager = view.findViewById(R.id.viewPager2)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout,viewPager){
            tab, position ->
            when (position) {
                0 -> { tab.text = "Income"}
                1 -> { tab.text = "Expense"}
            }
        }.attach()



        // Inflate the layout for this fragment
        return view
    }

    /**
    * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
    * sequence.
    */
    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment{
            val pickedFragment:Fragment = when(position){
                1-> {
                    expenseListFragment()
                }
                else-> {
                    incomeListFragment()
                }
            }
            return pickedFragment
        }
    }


}