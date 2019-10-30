package com.example.fakeline.feature_talk.adapter

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fakeline.R
import com.example.fakeline.fake_data.FakeMessages

class MessageModifyRecycleViewAdapter(
    private val context : Context,
    private val messageList: MutableList<FakeMessages>
) :
    RecyclerView.Adapter<MessageModifyRecycleViewAdapter.messageViewHolder>() {
     var mMessageList: MutableList<FakeMessages>
    val inflater: LayoutInflater = LayoutInflater.from(context)
    var mOnItemCheckListener: OnItemCheckListener? = null

    init{
        mMessageList=messageList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): messageViewHolder {
        val view: View = inflater.inflate(R.layout.viewholder_message_item, parent, false)
        return messageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  mMessageList.size
    }

    override fun onBindViewHolder(holder: messageViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class messageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var iconImageView= view.findViewById<ImageView>(R.id.iconImageView)
        var nameTextView= view.findViewById<TextView>(R.id.nameTextView)
        var messageTextView = view.findViewById<TextView>(R.id.messageTextView)
        var dateTextView = view.findViewById<TextView>(R.id.dateTextView)
        var unreadTextView = view.findViewById<TextView>(R.id.unreadTextView)
        var unreadImageView= view.findViewById<ImageView>(R.id.unreadImageView)
        var unreadFrameLayout= view.findViewById<FrameLayout>(R.id.unreadFrameLayout)
        var selectCheckBox = view.findViewById<CheckBox>(R.id.selectCheckBox)

        fun bind(position: Int){
            val selectColor = context.resources.getDrawable(R.color.colorUnread)
            val playWithBackground = LayerDrawable(arrayOf(selectColor))
            selectCheckBox.visibility=View.VISIBLE
            unreadFrameLayout.visibility=View.INVISIBLE

            Glide.with(context!!)
                .load(mMessageList[position].icon)
                .apply(RequestOptions.circleCropTransform())
                .into(iconImageView)
            Glide.with(context!!)
                .load(playWithBackground)
                .apply(RequestOptions.circleCropTransform())
                .into(unreadImageView)
            nameTextView.text=mMessageList[position].userName
            messageTextView.text=mMessageList[position].userMessage
            dateTextView.text=mMessageList[position].date
            unreadTextView.text=mMessageList[position].unread.toString()
            selectCheckBox.isChecked=mMessageList[position].isSelect
            selectCheckBox.setOnClickListener {
                mOnItemCheckListener!!.onCheck(position, mMessageList[position].id)
            }
        }
    }
    fun UpdateData(mList:MutableList<FakeMessages>){
        mMessageList=mList
        notifyDataSetChanged()
    }
    interface OnItemCheckListener {
        fun onCheck(position:Int,id:Int)
    }
    fun setOnItemCheckListener(mOnItemCheckListener: OnItemCheckListener) {
        this.mOnItemCheckListener = mOnItemCheckListener
    }
}