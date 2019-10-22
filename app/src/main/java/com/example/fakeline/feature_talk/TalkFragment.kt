package com.example.fakeline.feature_talk


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakeline.R
import com.example.fakeline.feature_talk.adapter.MessageRecycleViewAdapter
import com.example.fakeline.fake_data.FakeMessages
import com.example.fakeline.fake_data.MessageList
import kotlinx.android.synthetic.main.fragment_talk.view.*

class TalkFragment : Fragment() {
    private lateinit var rootView:View
    lateinit var messageRecycleViewAdapter: MessageRecycleViewAdapter
    var messageList= mutableListOf<FakeMessages>()
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var isSelectItemList: MutableList<Int>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageList= MessageList.messageList
        isSelectItemList=mutableListOf<Int>()
        rootView= inflater.inflate(R.layout.fragment_talk, container, false)
        rootView.menuImageView.setOnClickListener {
            popuMenu(rootView.menuImageView)
        }
        messageRecycleViewAdapter=MessageRecycleViewAdapter(this.context!!,messageList)
        messageRecycleViewAdapter.setOnItemCheckListener(object : MessageRecycleViewAdapter.OnItemCheckListener{
            override fun onCheck(position: Int, id: Int) {
                if(messageList[position].isSelect==true) isSelectItemList.add(messageList[position].id)
                else isSelectItemList.remove(messageList[position].id)
            }
        })
        mLinearLayoutManager = LinearLayoutManager(this.context)
        rootView.messageRecycleView.layoutManager = mLinearLayoutManager
        rootView.messageRecycleView.adapter=messageRecycleViewAdapter
        rootView.messageRecycleView.setHasFixedSize(true)
        return rootView
    }

    fun popuMenu(view: View){
        val popupMenu = PopupMenu(this.context!!, view)
        popupMenu.gravity= Gravity.BOTTOM
        popupMenu.getMenuInflater().inflate(R.menu.talk_menu, popupMenu.getMenu())
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.modifyMessageMenuItem ->{
                    var intentModifyMessage= Intent(this.context,ModifyMessageActivity::class.java)
                    startActivityForResult(intentModifyMessage,0)
                }
                R.id.sortTalkRoomMenuItem ->{
                    var intentSortMessage= Intent(this.context,
                        SortActivity::class.java)
                    startActivityForResult(intentSortMessage,1)
                }
                R.id.allMarkReadMenuItem ->{
                    var dialog = AlertDialog.Builder(this.context)
                        .setMessage("要將所有訊息標為已讀嗎?")
                        .setPositiveButton("標為已讀",object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                MessageList.messageList.forEach {
                                    it.unread = 0
                                }
                                messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                            }
                        })
                        .setNegativeButton("取消",object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        })
                        .show()
                }
            }
            false
        }
        popupMenu.setOnDismissListener {

        }
        popupMenu.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            0->{
                if(resultCode== Activity.RESULT_OK) {
                    messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                }
            }
            1->{
                if(resultCode== Activity.RESULT_OK) {
                    MessageList.messageList.sortWith(compareBy({ it.date }, { it.unread}))
                    messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                }
                else if(resultCode== Activity.RESULT_CANCELED) {
                    MessageList.messageList.sortWith(compareBy({ it.unread }, { it.date}))
                    messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                }
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = TalkFragment()
    }
}
