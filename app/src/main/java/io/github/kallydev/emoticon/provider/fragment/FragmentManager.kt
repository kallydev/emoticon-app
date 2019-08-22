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

package io.github.kallydev.emoticon.provider.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentManager(private val supportFragmentManager: FragmentManager) {

    private var fragmentHashMap = HashMap<String, Fragment>()

    fun addFragmentArray(
        fragmentID: Int,
        fragmentHashMap: HashMap<String, Fragment>
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        this.fragmentHashMap = fragmentHashMap
        this.fragmentHashMap.forEach {
            fragmentTransaction.add(fragmentID, it.value)
        }
        fragmentTransaction.commit()
    }

    fun showFragment(fragmentName: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        this.fragmentHashMap.forEach {
            if (it.key != fragmentName && !it.value.isHidden) {
                fragmentTransaction.hide(it.value)
            }
        }
        fragmentTransaction.show(fragmentHashMap[fragmentName]!!)
    }

    fun getFragment(fragmentName: String): Fragment? {
        return fragmentHashMap[fragmentName]
    }

}
