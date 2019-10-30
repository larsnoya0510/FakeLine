package com.example.fakeline.feature_talk.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL

import com.example.fakeline.R
import com.example.fakeline.fake_data.MessageList
import com.example.fakeline.feature_talk.search.adapter.KeywordRecycleViewAdapter
import com.example.fakeline.feature_talk.search.adapter.RecentSearchRecordRecycleViewAdapter
import com.example.fakeline.feature_talk.search.view_model.RecentRecordViewModel
import kotlinx.android.synthetic.main.fragment_search_setting.view.*

class SearchSettingFragment : Fragment() {
    lateinit var recentRecordListViewModel: RecentRecordViewModel
    lateinit var rootView : View
    lateinit var mStaggeredGridLayoutManager :StaggeredGridLayoutManager
    lateinit var mLinearLayoutManager :LinearLayoutManager
    lateinit var mKeywordRecycleViewAdapter: KeywordRecycleViewAdapter
    lateinit var mRecentSearchRecordRecycleViewAdapter: RecentSearchRecordRecycleViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mStaggeredGridLayoutManager=StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL)
        mLinearLayoutManager=LinearLayoutManager(this.context)
        rootView= inflater.inflate(R.layout.fragment_search_setting, container, false)

        rootView.startAutoRecordTextView.setOnClickListener {
            settingAutoRecordFeature(true,rootView)
        }
        rootView.stoptAutoRecordTextView.setOnClickListener {
            recentRecordListViewModel.clearSearchMessageListLiveData()
            settingAutoRecordFeature(false,rootView)
        }
        rootView.removeAllTextView.setOnClickListener {
            recentRecordListViewModel.clearSearchMessageListLiveData()
            settingAutoRecordFeature(true,rootView)
        }
        recentRecordListViewModel= ViewModelProviders.of(activity!!).get(RecentRecordViewModel::class.java)
        recentRecordListViewModel.getRecentRecordListLiveData().observe(activity!!,androidx.lifecycle.Observer {
            mRecentSearchRecordRecycleViewAdapter.UpdateData(it)
        })
        mKeywordRecycleViewAdapter=KeywordRecycleViewAdapter(this.context!!,MessageList.keywordList)
        rootView.keywordRecycleView.layoutManager=mStaggeredGridLayoutManager
        rootView.keywordRecycleView.adapter=mKeywordRecycleViewAdapter

        mRecentSearchRecordRecycleViewAdapter = RecentSearchRecordRecycleViewAdapter(this.context!!,recentRecordListViewModel.getRecentRecordListLiveData().value!! )
        mRecentSearchRecordRecycleViewAdapter.setOnDeleteItemCheckListener(object  : RecentSearchRecordRecycleViewAdapter.OnDeleteItemCheckListener{
            override fun onDelete(position: Int, recentSearch: String) {
                recentRecordListViewModel.deleteSearchMessageListLiveData(recentSearch)
            }

        })
        rootView.recentSearchRecordRecycleView.adapter=mRecentSearchRecordRecycleViewAdapter
        rootView.recentSearchRecordRecycleView.layoutManager=mLinearLayoutManager
        //初始狀態
        settingAutoRecordFeature(MessageList.recentRecordEnable,rootView)
        return rootView
    }

    private fun settingAutoRecordFeature(enable: Boolean, rootView: View) {
        MessageList.recentRecordEnable=enable
        when(enable){
            true ->{
                if(MessageList.recentRecordList.size>0){
                    rootView.recentSearchRecordRecycleView.visibility=View.VISIBLE
                    rootView.noRecentRecordTextView.visibility=View.GONE
                }
                else{
                    rootView.recentSearchRecordRecycleView.visibility=View.GONE
                    rootView.noRecentRecordTextView.visibility=View.VISIBLE
                    rootView.noRecentRecordTextView.text = "尚無記錄"
                }
                rootView.startSearchSettingConstraintLyout.visibility=View.GONE
                rootView.stopSearchSettingConstraintLyout.visibility=View.VISIBLE
            }
            false->{

                rootView.recentSearchRecordRecycleView.visibility=View.GONE
                rootView.noRecentRecordTextView.visibility=View.VISIBLE
                rootView.noRecentRecordTextView.text = "自動記錄功能停用中"

                rootView.startSearchSettingConstraintLyout.visibility=View.VISIBLE
                rootView.stopSearchSettingConstraintLyout.visibility=View.GONE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchSettingFragment()
    }
}
