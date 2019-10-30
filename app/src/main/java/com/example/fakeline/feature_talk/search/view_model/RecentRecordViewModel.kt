package com.example.fakeline.feature_talk.search.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakeline.fake_data.FakeMessages
import com.example.fakeline.fake_data.MessageList

class RecentRecordViewModel: ViewModel() {
    var recentRecordListLiveData= MutableLiveData<MutableList<String>>()
    init {
        recentRecordListLiveData.value=MessageList.recentRecordList
    }
    fun setRecentRecordListLiveData(mKeyWrod:String){
        if(!MessageList.recentRecordList.contains(mKeyWrod))  MessageList.recentRecordList.add(mKeyWrod)
        recentRecordListLiveData.value= MessageList.recentRecordList
    }
    fun clearSearchMessageListLiveData(){
        MessageList.recentRecordList.clear()
        recentRecordListLiveData.value= MessageList.recentRecordList
    }
    fun deleteSearchMessageListLiveData(mString:String){
        MessageList.recentRecordList.remove(mString)
        recentRecordListLiveData.value= MessageList.recentRecordList
    }
    fun getRecentRecordListLiveData() : LiveData<MutableList<String>> {
        return recentRecordListLiveData
    }
}