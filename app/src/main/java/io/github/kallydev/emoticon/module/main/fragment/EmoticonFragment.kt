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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.adapter.EmoticonAdapter
import io.github.kallydev.emoticon.adapter.EmoticonPackageAdapter
import kotlinx.android.synthetic.main.fragment_emoticon.view.*

class EmoticonFragment : BottomSheetDialogFragment(), EmoticonPackageAdapter.OnItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_emoticon, container, false)
        view.fragment_emoticon_fastScrollRecyclerView.layoutManager = GridLayoutManager(context!!, 4)
        view.fragment_emoticon_fastScrollRecyclerView.adapter = EmoticonAdapter(context!!)
        return view
    }

    override fun onItemClickListener(itemView: View, position: Int) {

    }

    override fun onItemLongClickListener(itemView: View, position: Int) {

    }

}
