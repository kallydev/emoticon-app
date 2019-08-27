/*
 * An open source emoticon application
 * Copyright (C) 2019 KallyDev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.kallydev.emoticon.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.kallydev.emoticon.bean.SourceBean
import io.github.kallydev.emoticon.module.main.fragment.source.SourceFragment

class SourceAdapter(private val fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val sourceArrayList = ArrayList<SourceBean>()
    private val fragmentArrayList = ArrayList<SourceFragment>()

    override fun getItem(position: Int): Fragment {
        Log.d("DEBUG", "getItem")
        if (position == 0) {
            // TODO 处理首页
        }
        val fragment = SourceFragment()
        fragmentArrayList.add(fragment)
        return fragment
    }

    override fun getCount(): Int {
        return sourceArrayList.size + 1
    }

//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val fragment = super.instantiateItem(container, position) as Fragment
//        this.fragmentManager.beginTransaction().show(fragment).commit()
//        return fragment
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        val fragment = fragmentArrayList[position]
//        fragmentManager.beginTransaction().hide(fragment).commit()
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return (`object` as Fragment).view === view
//    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "首页"
        } else {
            "测试"
        }
    }

//    override fun getItemPosition(`object`: Any): Int {
//        return POSITION_NONE
//    }

    private fun refreshFragment() {
        this.fragmentArrayList.clear()
        for (source in sourceArrayList) {
            val sourceFragment = SourceFragment()
            fragmentArrayList.add(sourceFragment)
        }
    }

    fun setSourceArrayList(sourceArrayList: ArrayList<SourceBean>) {
        this.sourceArrayList.clear()
        this.sourceArrayList.addAll(sourceArrayList)
        refreshFragment()
        notifyDataSetChanged()
    }

}
