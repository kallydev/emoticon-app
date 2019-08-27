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

package io.github.kallydev.emoticon.module.main.fragment.source

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.adapter.TempEmoticonPackageAdapter
import io.github.kallydev.emoticon.base.BaseFragment
import io.github.kallydev.emoticon.bean.EmoticonPackageBean
import kotlinx.android.synthetic.main.fragment_source.view.*

class SourceFragment : BaseFragment() {

    override fun getLayoutID(): Int {
        return R.layout.fragment_source
    }

    override fun onInit(view: View) {
        val adapter = TempEmoticonPackageAdapter(context!!)
        view.fragment_source_fastScrollRecyclerView.layoutManager =
            GridLayoutManager(context!!, 4)
        view.fragment_source_fastScrollRecyclerView.adapter = adapter
        adapter.loadEmoticonPackage(
            arrayOf(
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf()),
                EmoticonPackageBean("", false, arrayListOf())
            ), false
        )
    }

    fun setSource(url: String) {

    }

}
