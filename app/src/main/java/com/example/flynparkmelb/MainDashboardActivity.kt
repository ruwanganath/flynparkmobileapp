package com.example.flynparkmelb

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.flynparkmelb.databinding.ActivityMainDashboardBinding
//Main dashboard activity class
class MainDashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainDashboard.toolbar)

       // binding.appBarMainDashboard.fab.setOnClickListener { view ->
       //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
       //         .setAction("Action", null).show()
       // }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main_dashboard)

        val intent = getIntent()
        val name = intent.getStringExtra("userName")
        val hView: View = navView.getHeaderView(0)
        val nav_headeruser = hView.findViewById(R.id.textViewName) as TextView
        nav_headeruser.setText(name)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_dash, R.id.nav_searchparking, R.id.nav_parkinghistory, R.id.nav_changepassword, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main_dashboard)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}