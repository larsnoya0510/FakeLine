package com.example.fakeline

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.example.fakeline.custom.BottomNavigationViewHelper
import com.example.fakeline.fake_data.FakeMessages
import com.example.fakeline.view_model.MessageListViewodel
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.Array.setBoolean
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var messageListViewModel : MessageListViewodel
    lateinit var mFragmentPool: FragmentPool
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFragmentPool = FragmentPool()
        init()
        initView()
        initFragment()
        bottomNavigationView.selectedItemId=R.id.talkMenu
    }
    fun init(){
        messageListViewModel= ViewModelProviders.of(this!!).get(MessageListViewodel::class.java)
    }
    private fun initFragment() {
        var action = this.supportFragmentManager!!.beginTransaction()
        action.replace(
            R.id.fragmentContainer,
            mFragmentPool.mHomeFragment
        )
        action.commit()
    }

    private fun replaceFragment(mFragment: Fragment) {
        var action = this.supportFragmentManager!!.beginTransaction()
        action.replace(
            R.id.fragmentContainer,
            mFragment
        )
        action.commit()
    }

    private fun initView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeMenu -> {
                    replaceFragment(mFragmentPool.mHomeFragment)
                    true
                }
                R.id.talkMenu -> {
                    replaceFragment(mFragmentPool.mTalkFragment)
                    true
                }
                R.id.articleMenu -> {
                    replaceFragment(mFragmentPool.mAriticleFragmet)
                    true
                }
                R.id.todayMenu -> {
                    replaceFragment(mFragmentPool.mTodayFragment)
                    true
                }
                R.id.walletMenu -> {
                    replaceFragment(mFragmentPool.mWalletFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

}

