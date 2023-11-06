package com.example.newrecipe.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.fragment.app.Fragment
import com.example.newrecipe.Fragment.CategriesFragment
import com.example.newrecipe.Fragment.FavoriteFragment
import com.example.newrecipe.Fragment.ProfileFragment
import com.example.newrecipe.Fragmentimport.HomeFragment
import com.example.newrecipe.R
import com.google.android.material.bottomnavigation.BottomNavigationView
class Home_Activity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId)
            {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
//                R.id.explore -> {
//                    loadFragment(CategriesFragment())
//                    true
//                }
                R.id.favorite -> {
                    loadFragment(FavoriteFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }

        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }

}