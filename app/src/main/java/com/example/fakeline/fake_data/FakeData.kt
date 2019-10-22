package com.example.fakeline.fake_data

import com.example.fakeline.R
import kotlin.random.Random

class MessageList{
    companion object{
        var messageList=FakeMessages.defaultList
    }
}
data class FakeMessages(
    var id:Int,
    var icon: Int,
    var userName: String,
    var userMessage: String,
    var date: String,
    var unread: Int,
    var isSelect:Boolean
) {
    companion object {
        val iconArray = intArrayOf(
            R.drawable.icon01,
            R.drawable.icon02,
            R.drawable.icon03,
            R.drawable.icon04,
            R.drawable.icon05,
            R.drawable.icon06,
            R.drawable.icon07,
            R.drawable.icon08,
            R.drawable.icon09,
            R.drawable.icon10
        )
        val defaultList: MutableList<FakeMessages>
            get() {
                val dataList = mutableListOf<FakeMessages>()
                for (i in 0 .. 49) {
                    dataList.add(
                        FakeMessages(
                            i,
                            FakeMessages.iconArray[Random.nextInt(0,9)],
                            "Title${i+1}",
                            "Message$i : this is test message for Line Test use.",
                            "${String.format("%02d", Random.nextInt(1,12))}/${String.format("%02d", Random.nextInt(1,31))}",
                            Random.nextInt(0,99),
                            false
                        )
                    )
                }
                return dataList
            }
    }
}