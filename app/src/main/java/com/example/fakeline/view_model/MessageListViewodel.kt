package com.example.fakeline.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakeline.fake_data.FakeMessages

class MessageListViewodel : ViewModel() {
    var messageListLiveData= MutableLiveData<MutableList<FakeMessages>>()
    fun setMessageList(mList :MutableList<FakeMessages>){
        messageListLiveData.value=mList
    }
    fun getMessageList() : LiveData<MutableList<FakeMessages>> {
        return messageListLiveData
    }
}