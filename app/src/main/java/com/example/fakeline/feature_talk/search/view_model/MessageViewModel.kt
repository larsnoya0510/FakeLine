package com.example.fakeline.feature_talk.search.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakeline.fake_data.FakeMessages

class MessageViewModel:ViewModel() {
    var searchMessageListLiveData=MutableLiveData<MutableList<FakeMessages>>()

    fun setSearchMessageListLiveData(mList:MutableList<FakeMessages>){
        searchMessageListLiveData.value=mList
    }

    fun getSearchMessageListLiveData() : LiveData<MutableList<FakeMessages>>{
        return searchMessageListLiveData
    }
}

