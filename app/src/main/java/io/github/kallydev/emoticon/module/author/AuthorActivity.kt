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

package io.github.kallydev.emoticon.module.author

import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.adapter.AuthorAdapter
import io.github.kallydev.emoticon.base.BaseActivity
import kotlinx.android.synthetic.main.activity_author.*

class AuthorActivity : BaseActivity() {

    override fun getLayoutID(): Int {
        return R.layout.activity_author
    }

    override fun onInit() {
        setSupportActionBar(activity_author_bottomAppBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity_author_recyclerView_author.layoutManager = LinearLayoutManager(
            this,
            LinearLayout.HORIZONTAL,
            false
        )
        activity_author_recyclerView_author.adapter = AuthorAdapter(this)

        activity_author_recyclerView_thanks.layoutManager = LinearLayoutManager(
            this,
            LinearLayout.HORIZONTAL,
            false
        )
        activity_author_recyclerView_thanks.adapter = AuthorAdapter(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAfterTransition()
        return true
//        Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show()
//        if (item.itemId == com.google.android.material.R.id.home) {
//            finish()
//            return true
//        }
        // return super.onOptionsItemSelected(item)
    }

}
