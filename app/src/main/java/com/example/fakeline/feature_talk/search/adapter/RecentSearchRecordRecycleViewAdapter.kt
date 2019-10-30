package com.example.fakeline.feature_talk.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakeline.R

class RecentSearchRecordRecycleViewAdapter(
    private val context : Context,
    private val RecentSearchRecordList: MutableList<String>
) :
    RecyclerView.Adapter<RecentSearchRecordRecycleViewAdapter.recentSearchRecordViewHolder>() {
    var mRecentSearchRecordList: MutableList<String>
    val inflater: LayoutInflater = LayoutInflater.from(context)
    var mOnItemCheckListener: OnItemCheckListener? = null
    var mOnDeleteItemCheckListener: OnDeleteItemCheckListener? = null
    init{
        mRecentSearchRecordList=RecentSearchRecordList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recentSearchRecordViewHolder {
        val view: View = inflater.inflate(R.layout.viewholder_recent_search_record_item, parent, false)
        return recentSearchRecordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  mRecentSearchRecordList.size
    }

    override fun onBindViewHolder(holder: recentSearchRecordViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class recentSearchRecordViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var mRecentSearchRecordTextView= view.findViewById<TextView>(R.id.recentSearchRecordTextView)
        var mecentSearchRecordDeleteImageView= view.findViewById<ImageView>(R.id.recentSearchRecordDeleteImageView)

        fun bind(position: Int){
            mRecentSearchRecordTextView.text= RecentSearchRecordList[position].toString()
            mecentSearchRecordDeleteImageView.setOnClickListener {
                mOnDeleteItemCheckListener?.onDelete(position,RecentSearchRecordList[position])
            }
        }
    }
    fun UpdateData(mList:MutableList<String>){
        mRecentSearchRecordList=mList
        notifyDataSetChanged()
    }
    interface OnItemCheckListener {
        fun onCheck(position:Int,id:Int)
    }
    interface OnDeleteItemCheckListener {
        fun onDelete(position:Int,recentSearch:String)
    }
    fun setOnItemCheckListener(mOnItemCheckListener: OnItemCheckListener) {
        this.mOnItemCheckListener = mOnItemCheckListener
    }
    fun setOnDeleteItemCheckListener(onDeleteItemCheckListener: OnDeleteItemCheckListener) {
        this.mOnDeleteItemCheckListener = onDeleteItemCheckListener
    }
}