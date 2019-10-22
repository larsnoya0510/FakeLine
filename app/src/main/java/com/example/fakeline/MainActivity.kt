package com.example.fakeline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fakeline.utilities.FragmentPool
import com.example.fakeline.view_model.MessageListViewodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    lateinit var messageListViewModel : MessageListViewodel
    lateinit var mFragmentPool: FragmentPool
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFragmentPool = FragmentPool()
//        init()
        initView()
        initFragment()
        bottomNavigationView.selectedItemId=R.id.talkMenu
    }
//    fun init(){
//        messageListViewModel= ViewModelProviders.of(this!!).get(MessageListViewodel::class.java)
//    }
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

