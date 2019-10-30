package com.example.fakeline.feature_talk.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.fakeline.R
import com.example.fakeline.fake_data.FakeMessages
import com.example.fakeline.fake_data.MessageList
import com.example.fakeline.feature_talk.search.adapter.SearchInMesageRecycleViewAdapter
import com.example.fakeline.feature_talk.search.view_model.MessageViewModel
import com.example.fakeline.feature_talk.search.view_model.RecentRecordViewModel
import kotlinx.android.synthetic.main.fragment_search_in_message.view.*

class SearchInMessageFragment : Fragment() {
    lateinit var recentRecordListViewModel: RecentRecordViewModel
    lateinit var messagesViewModel: MessageViewModel
    lateinit var rootView:View
    lateinit var mSearchInMesageRecycleViewAdapter: SearchInMesageRecycleViewAdapter
    lateinit var mLinearLayoutManager : LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var initList=mutableListOf<FakeMessages>()
        //RecycleView
        mLinearLayoutManager=LinearLayoutManager(this.context)
        mSearchInMesageRecycleViewAdapter=SearchInMesageRecycleViewAdapter(this.context!!,initList)
        mSearchInMesageRecycleViewAdapter.setOnItemCheckListener(object : SearchInMesageRecycleViewAdapter.OnItemCheckListener{
            override fun onCheck(position: Int, keyword: String) {
                if(MessageList.recentRecordEnable) recentRecordListViewModel.setRecentRecordListLiveData(keyword)
            }
        })
        rootView= inflater.inflate(R.layout.fragment_search_in_message, container, false)
        rootView.searchInMessageRecycleView.adapter=mSearchInMesageRecycleViewAdapter
        rootView.searchInMessageRecycleView.layoutManager=mLinearLayoutManager
        //ViewModel
        recentRecordListViewModel= ViewModelProviders.of(activity!!).get(RecentRecordViewModel::class.java)
        messagesViewModel= ViewModelProviders.of(activity!!).get(MessageViewModel::class.java)
        messagesViewModel.getSearchMessageListLiveData().observe(activity!!,androidx.lifecycle.Observer {
            mSearchInMesageRecycleViewAdapter.UpdateData(it)
        })
        return rootView
    }
    companion object {
        @JvmStatic
        fun newInstance() = SearchInMessageFragment()
    }
}
