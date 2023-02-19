package com.example.vk_projects.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import com.example.vk_projects.R
import com.example.vk_projects.presentation.detailFragment.DetailFragment
import com.example.vk_projects.presentation.mainFragment.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.OpenSecondFragmentListener{

    private var container: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)
        if (savedInstanceState == null) {
            setMainFragmentContainer()
        }
    }

    private fun setMainFragmentContainer() {
        val fragment = MainFragment()
        container?.let {
            supportFragmentManager.beginTransaction()
                .add(it.id, fragment)
                .commit()
        }
    }

    override fun openSecondFragment(number: Int) {
        val fragment = DetailFragment.newInstanceFromRemote(number)
        container?.let {
            supportFragmentManager.beginTransaction()
                .replace(it.id, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit()
        }
    }
}