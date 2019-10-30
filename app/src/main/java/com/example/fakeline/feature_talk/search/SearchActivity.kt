package com.example.fakeline.feature_talk.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        //viewModel
        messagesViewModel = ViewModelProviders.of(this!!).get(MessageViewModel::class.java)
        recentRecordListViewModel = ViewModelProviders.of(this!!).get(RecentRecordViewModel::class.java)

        mSearchInMessageList=mutableListOf<FakeMessages>()
        mSearchFragmentPool = SearchFragmentPool() //初始化可用fragment資源
        replaceFragment(mSearchFragmentPool.mSearchSettingFragment)

        backImageView.setOnClickListener {
            this.finish()
        }
        //逐字判斷
        searchEditText.addTextChangedListener {
            if (it!=null && it.length > 0) {
                if (searchFlag == false) {
                    replaceFragment(mSearchFragmentPool.mSearchFragment)
                    searchFlag=true
                }
                //用輸入文字從資料篩選
                var keyword=it.toString()
                mSearchInMessageList= MessageList.messageList.filter{
                    it->it.userMessage.contains(keyword)
                }.map{
                    var mFakeMessages=it.copy()
                    mFakeMessages.userMessage="共找到1則訊息"
                    mFakeMessages.keyword=keyword
                    mFakeMessages
                }.toMutableList()
                //使用viewmodel更新搜尋資料顯示
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
