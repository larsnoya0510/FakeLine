package com.example.fakeline.feature_talk

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakeline.R
import com.example.fakeline.fake_data.FakeMessages
import com.example.fakeline.fake_data.MessageList
import com.example.fakeline.feature_talk.adapter.MessageModifyRecycleViewAdapter
import com.example.fakeline.view_model.MessageListViewodel
import kotlinx.android.synthetic.main.activity_modify_message.*

class ModifyMessageActivity : AppCompatActivity() {

    lateinit var recycleViewAdapter:MessageModifyRecycleViewAdapter
    lateinit var linearLayoutManager :LinearLayoutManager
    lateinit var selectItemList:MutableList<Int>
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_message)

        selectItemList=mutableListOf<Int>()
        linearLayoutManager= LinearLayoutManager(this)
        messageModifyRecycleView.layoutManager=linearLayoutManager
        recycleViewAdapter=MessageModifyRecycleViewAdapter(this, MessageList.messageList)
        recycleViewAdapter.setOnItemCheckListener(object : MessageModifyRecycleViewAdapter.OnItemCheckListener{
            override fun onCheck(position: Int, id: Int) {
                if(recycleViewAdapter.mMessageList[position].isSelect==false)  {
                    recycleViewAdapter.mMessageList[position].isSelect=true
                    if(!selectItemList.contains(id)) selectItemList.add(id)
                    println("***ADD ${selectItemList.toString()}")
                }
                else {
                    recycleViewAdapter.mMessageList[position].isSelect=false
                    if(selectItemList.contains(id)) selectItemList.remove(id)
                    println("***REMOVE  ${selectItemList.toString()}")
                }
                recycleViewAdapter.notifyDataSetChanged()
            }
        })
        messageModifyRecycleView.adapter=recycleViewAdapter
        deleteButton.setOnClickListener {
            if(selectItemList.size>0){
                MessageList.messageList.removeIf {
                    it.id in selectItemList
                }
                selectItemList.clear()
                this.setResult(  Activity.RESULT_OK)
                this.finish()
            }
        }
        backImageView.setOnClickListener {
            this.finish()
        }
    }
}
