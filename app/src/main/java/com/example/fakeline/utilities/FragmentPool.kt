package com.example.fakeline.utilities

import com.example.fakeline.feature_ariticle.AriticleFragment
import com.example.fakeline.feature_home.HomeFragment
import com.example.fakeline.feature_talk.TalkFragment
import com.example.fakeline.feature_talk.search.NotMatchFragment
import com.example.fakeline.feature_talk.search.SearchFragment
import com.example.fakeline.feature_talk.search.SearchInMessageFragment
import com.example.fakeline.feature_talk.search.SearchSettingFragment
import com.example.fakeline.feature_today.TodayFragment
import com.example.fakeline.feature_wallet.WalletFragment

class FragmentPool {
    val mHomeFragment: HomeFragment by lazy { HomeFragment.newInstance() }
    val mTalkFragment: TalkFragment by lazy { TalkFragment.newInstance() }
    val mAriticleFragmet: AriticleFragment by lazy { AriticleFragment.newInstance() }
    val mTodayFragment: TodayFragment by lazy { TodayFragment.newInstance() }
    val mWalletFragment: WalletFragment by lazy { WalletFragment.newInstance() }
}

class SearchFragmentPool{
    val mSearchFragment: SearchFragment by lazy { SearchFragment.newInstance() }
    val mSearchSettingFragment: SearchSettingFragment by lazy { SearchSettingFragment.newInstance() }
    val mNotMatchFragment: NotMatchFragment by lazy { NotMatchFragment.newInstance() }
    val mSearchInMessageFragment: SearchInMessageFragment by lazy { SearchInMessageFragment.newInstance() }
}