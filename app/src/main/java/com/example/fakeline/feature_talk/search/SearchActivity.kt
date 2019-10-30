package com.example.fakeline.feature_talk.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fakeline.R
import com.example.fakeline.fake_data.FakeMessages
import com.example.fakeline.fake_data.MessageList
import com.example.fakeline.feature_talk.search.view_model.MessageViewModel
import com.example.fakeline.feature_talk.search.view_model.RecentRecordViewModel
import com.example.fakeline.utilities.SearchFragmentPool
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    lateinit var messagesViewModel:MessageViewModel
    lateinit var recentRecordListViewModel:RecentRecordViewModel
    lateinit var mSearchFragmentPool: SearchFragmentPool
    lateinit var mSearchInMessageList : MutableList<FakeMessages>
    var searchFlag=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        messagesViewModel = ViewModelProviders.of(this!!).get(MessageViewModel::class.java)
        recentRecordListViewModel = ViewModelProviders.of(this!!).get(RecentRecordViewModel::class.java)
        mSearchFragmentPool = SearchFragmentPool() //初始化可用fragment資源
        replaceFragment(mSearchFragmentPool.mSearchSettingFragment)
        backImageView.setOnClickListener {
            this.finish()
        }
        searchEditText.addTextChangedListener {
            if (it!=null && it.length > 0) {
                if (searchFlag == false) {
                    replaceFragment(mSearchFragmentPool.mSearchFragment)
                    searchFlag=true
                }
                else{

                }
                var keyword=it.toString()
                mSearchInMessageList= MessageList.messageList.filter{
                    it.userMessage.contains(keyword)
                }.map{
                    it.userMessage="共找到1則訊息"
                    it.keyword=keyword
                    it
                }.toMutableList()
                messagesViewModel.setSearchMessageListLiveData(mSearchInMessageList)
            }
            else if(it!=null && it.length == 0 ){
                if (searchFlag == true) {
                    replaceFragment(mSearchFragmentPool.mSearchSettingFragment)
                    searchFlag=false
                }
            }
        }
    }
    private fun replaceFragment(mFragment: Fragment) {
        var action = this.supportFragmentManager!!.beginTransaction()
        action.replace(
            R.id.fragmentContainerFrameLayout,
            mFragment
        )
        action.commit()
    }

}
