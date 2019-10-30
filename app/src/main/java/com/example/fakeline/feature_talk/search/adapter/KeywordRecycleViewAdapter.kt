package com.example.fakeline.feature_talk.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakeline.R

class KeywordRecycleViewAdapter(
    private val context : Context,
    private val keywordList: MutableList<String>
) :
    RecyclerView.Adapter<KeywordRecycleViewAdapter.keywordViewHolder>() {
    var mkeywordList: MutableList<String>
    val inflater: LayoutInflater = LayoutInflater.from(context)
    var mOnItemCheckListener: OnItemCheckListener? = null

    init{
        mkeywordList=keywordList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): keywordViewHolder {
        val view: View = inflater.inflate(R.layout.viewholder_keyword_item, parent, false)
        return keywordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  mkeywordList.size
    }

    override fun onBindViewHolder(holder: keywordViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class keywordViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var mKeyWordTextView= view.findViewById<TextView>(R.id.keyWordTextView)

        fun bind(position: Int){
            mKeyWordTextView.text= keywordList[position]
        }
    }
    fun UpdateData(mList:MutableList<String>){
        mkeywordList=mList
        notifyDataSetChanged()
    }
    interface OnItemCheckListener {
        fun onCheck(position:Int,id:Int)
    }
    fun setOnItemCheckListener(mOnItemCheckListener: OnItemCheckListener) {
        this.mOnItemCheckListener = mOnItemCheckListener
    }
}