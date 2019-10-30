package com.example.fakeline.feature_talk.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fakeline.R
import com.example.fakeline.utilities.SearchFragmentPool
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {
    lateinit var mSearchFragmentPool: SearchFragmentPool
    lateinit var rootView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mSearchFragmentPool =(activity as SearchActivity).mSearchFragmentPool
        rootView= inflater.inflate(R.layout.fragment_search, container, false)
        initTablayoutView(rootView)
        rootView.categoryTabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!=null && tab.text=="訊息"){
                    replaceFragment(mSearchFragmentPool.mSearchInMessageFragment,"SearchInMessageFragment")
                }
                else if(tab!=null){
                    replaceFragment(mSearchFragmentPool.mNotMatchFragment,"NotMatchFragment")
                }
            }
        })
        return rootView
    }

    private fun initTablayoutView(rootView:View) {
        var titleList= arrayListOf<String>("全部","聊天","訊息","好友","新聞","官方帳號","購物","貼圖","主題","服務","附近")
        titleList.forEach {
            rootView.categoryTabLayout.addTab(rootView.categoryTabLayout.newTab().setText(it))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
    private fun replaceFragment(mFragment: Fragment,mTag:String) {
        var action = this.childFragmentManager!!.beginTransaction()
        action.replace(
            R.id.childFragmentContainerFrameLayout,
            mFragment,
            mTag
        )
        action.commit()
    }
}
