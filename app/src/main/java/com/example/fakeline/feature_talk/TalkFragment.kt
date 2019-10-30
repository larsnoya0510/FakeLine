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
import com.example.fakeline.MainActivity
import com.example.fakeline.R
import com.example.fakeline.feature_talk.adapter.MessageRecycleViewAdapter
import com.example.fakeline.fake_data.FakeMessages
import com.example.fakeline.fake_data.MessageList
import com.example.fakeline.feature_talk.search.SearchActivity
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
        rootView= inflater.inflate(R.layout.fragment_talk, container, false)
        initView(rootView)
        return rootView
    }

    private fun initView(rootView:View) {
        messageList = MessageList.messageList
        isSelectItemList = mutableListOf<Int>()
        rootView.menuImageView.setOnClickListener {
            popuMenu(rootView.menuImageView)
        }
        rootView.searcImageView.setOnClickListener { openSearchActivity() }
        messageRecycleViewAdapter = MessageRecycleViewAdapter(this.context!!, messageList)
        mLinearLayoutManager = LinearLayoutManager(this.context)
        rootView.messageRecycleView.layoutManager = mLinearLayoutManager
        rootView.messageRecycleView.adapter = messageRecycleViewAdapter
        rootView.messageRecycleView.setHasFixedSize(true)
    }

    private fun openSearchActivity() {
        var openSearchIntent=Intent(this.context,
            SearchActivity::class.java)
        startActivity(openSearchIntent)
    }

    //自定物件選單，PopupMenu可以指定物件綁定menu並設定出現位置
    fun popuMenu(view: View){
        val popupMenu = PopupMenu(this.context!!, view)
        popupMenu.gravity= Gravity.BOTTOM
        popupMenu.getMenuInflater().inflate(R.menu.talk_menu, popupMenu.getMenu())
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.modifyMessageMenuItem ->{
                    openModifyMessageActivity()
                }
                R.id.sortTalkRoomMenuItem ->{
//                    openSortActivity()  //兩種方式，Activity也可以當Dialog用
                    openSortDialog()
                }
                R.id.allMarkReadMenuItem ->{
                    openUnreadDialog()
                }
            }
            false
        }
        popupMenu.setOnDismissListener {
        }
        popupMenu.show()
    }

    private fun openUnreadDialog() {
        var dialog = AlertDialog.Builder(this.context)
            .setMessage("要將所有訊息標為已讀嗎?")
            .setPositiveButton("標為已讀", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    MessageList.messageList.forEach {
                        it.unread = 0
                    }
                    messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                    setBadge()
                }
            })
            .setNegativeButton("取消", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.dismiss()
                }
            })
        dialog.show()
    }

    private fun setBadge() {
        var act = activity as MainActivity
        act.setBadge()
    }

    private fun openSortActivity() {
        var intentSortMessage = Intent(this.context, SortActivity::class.java)
        startActivityForResult(intentSortMessage, 1)
    }

    private fun openSortDialog() {
        var dialog = AlertDialog.Builder(this.context)
            .setItems(arrayOf<String>("收到的時間", "未讀訊息"), object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {
                        0 -> {
                            MessageList.messageList.sortWith(compareBy({ it.date }, { it.unread }))
                            messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                        }
                        1 -> {
                            MessageList.messageList.sortWith(compareBy({ it.unread }, { it.date }))
                            messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                        }
                    }
                }
            })
        dialog.show()
    }

    private fun openModifyMessageActivity() {
        var intentModifyMessage = Intent(this.context, ModifyMessageActivity::class.java)
        startActivityForResult(intentModifyMessage, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            //處理MessageActivity事件
            0->{
                if(resultCode== Activity.RESULT_OK) {
                    messageRecycleViewAdapter.UpdateData(MessageList.messageList)
                    setBadge()
                }
            }
            //處理SortActivity事件
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
