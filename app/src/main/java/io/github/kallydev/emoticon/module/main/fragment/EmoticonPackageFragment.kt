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

package io.github.kallydev.emoticon.module.main.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.adapter.EmoticonPackageAdapter
import io.github.kallydev.emoticon.base.BaseFragment
import io.github.kallydev.emoticon.module.main.MainActivity
import kotlinx.android.synthetic.main.fragment_emoticon.view.*

class EmoticonPackageFragment : BaseFragment(), EmoticonPackageAdapter.OnItemClickListener {

    override fun getLayoutID(): Int {
        return R.layout.fragment_emoticon_package
    }

    override fun onInit(view: View) {
        view.fragment_emoticon_fastScrollRecyclerView.layoutManager =
            GridLayoutManager(context!!, 4)
        val emoticonPackageAdapter = EmoticonPackageAdapter(context!!)
        view.fragment_emoticon_fastScrollRecyclerView.adapter = emoticonPackageAdapter
        emoticonPackageAdapter.setOnItemClickListener(this)
    }

    override fun onItemClickListener(itemView: View, position: Int) {
        val baseActivity = context as MainActivity
        EmoticonFragment().show(baseActivity.supportFragmentManager, "dialog")
    }

    override fun onItemLongClickListener(itemView: View, position: Int) {

    }

}
