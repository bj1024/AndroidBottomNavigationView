package com.testdomain.androidbottomnavigationviewexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        Log.d(TAG,"OnNavigationItemSelected ${item.itemId}")
        when(item.itemId){

            R.id.navigation_dashboard -> {

            }
            R.id.navigation_home -> {
                Log.d(TAG,"OnNavigationItemSelected navigation_home")
            }
            R.id.navigation_myitem ->{
                Log.d(TAG,"OnNavigationItemSelected navigation_myitem")
            }
            R.id.navigation_notifications ->{
                Log.d(TAG,"OnNavigationItemSelected navigation_dashboard")
            }

        }
        true


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        navView.disableShiftMode()
        BottomNavigationHelper.removeShiftMode(bottomNavigationView)

        // force showing title
        val menuView: BottomNavigationMenuView = bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
        for (i in 0..menuView.childCount - 1) {
            val itemView = menuView.getChildAt(i) as BottomNavigationItemView
            itemView.setShifting(false)
//            itemView.setChecked(false)
            itemView.setChecked(itemView.itemData.isChecked)
        }


        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)

//        setupWithNavController とsetOnNavigationItemSelectedListenerは同時に利用できない。
//        navController に addOnDestinationChangedListenerを入れる必要がある。
//        navView.setupWithNavController(navController)


    }



}

//
//@SuppressLint("RestrictedApi")
//fun BottomNavigationView.disableShiftMode() {
//    val menuView = getChildAt(0) as BottomNavigationMenuView
//    try {
//        val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
//        shiftingMode.isAccessible = true
//        shiftingMode.setBoolean(menuView, false)
//        shiftingMode.isAccessible = false
//        for (i in 0 until menuView.childCount) {
//            val item = menuView.getChildAt(i) as BottomNavigationItemView
//            item.setShifting(false)
//            // set once again checked value, so view will be updated
//            item.setChecked(item.itemData.isChecked)
//        }
//    } catch (e: NoSuchFieldException) {
//        Log.e("BottomNavigationView", "Unable to get shift mode field", e)
//    } catch (e: IllegalStateException) {
//        Log.e("BottomNavigationView", "Unable to change value of shift mode", e)
//    }
//}

private object BottomNavigationHelper {
    @SuppressLint("RestrictedApi")
    fun removeShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShifting(false)
            item.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED)

            // set once again checked value, so view will be updated
            item.setChecked(item.itemData.isChecked)
        }
    }
}