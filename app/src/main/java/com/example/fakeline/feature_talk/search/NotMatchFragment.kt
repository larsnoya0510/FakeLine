package com.example.fakeline.feature_talk.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fakeline.R
import com.example.fakeline.fake_data.FakeMessages

class NotMatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_not_match, container, false)
    }
    companion object {
        @JvmStatic
        fun newInstance() = NotMatchFragment()
    }
}
