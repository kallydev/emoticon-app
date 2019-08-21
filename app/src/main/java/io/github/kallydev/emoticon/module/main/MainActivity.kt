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

package io.github.kallydev.emoticon.module.main

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.GravityCompat
import androidx.core.widget.NestedScrollView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.base.BaseActivity
import io.github.kallydev.emoticon.module.author.AuthorActivity
import io.github.kallydev.emoticon.module.main.fragment.EmoticonFragment
import io.github.kallydev.emoticon.module.settings.fragment.AddFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : BaseActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    NestedScrollView.OnScrollChangeListener {

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun onInit() {
        setSupportActionBar(activity_main_bottomAppBar)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            activity_main_drawerLayout,
            activity_main_bottomAppBar,
            0,
            0
        )

        activity_main_drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        activity_main_navigationView.setNavigationItemSelectedListener(this)
        activity_main_extendedFloatingActionButton.setOnClickListener {
            AddFragment().show(supportFragmentManager, "dialog")
//            val dialog = BottomSheetDialog(this)
//            val view = layoutInflater.inflate(R.layout.view_main_bottom_sheet_add_new_emoticon, null)
//            dialog.setContentView(view)
//            dialog.show()
//            val builder = AlertDialog.Builder(this, R.style.AppDialogTheme)
////            builder.setTitle("添加新表情")
//            builder.setView(R.layout.view_main_dialog_add_new_emoticon)
//            builder.setNegativeButton("取消", null)
//            builder.setPositiveButton("导入", null)
//            //builder.setNeutralButton("CCC", null)
//            builder.show()
        }
        activity_main_nestedScrollView.setOnScrollChangeListener(this)
        val emoticonFragment = EmoticonFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.activity_main_frameLayout, emoticonFragment)
        transaction.show(emoticonFragment).commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        GlobalScope.launch {
            delay(240)
            withContext(Dispatchers.Main) {
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MainActivity,
                    activity_main_extendedFloatingActionButton,
                    "extendedFloatingActionButton"
                ).toBundle()
                startActivity(Intent(this@MainActivity, AuthorActivity::class.java), bundle)
            }
        }
        activity_main_drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_bottom_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Snackbar.make(activity_main_coordinatorLayout, "咕咕咕", Snackbar.LENGTH_LONG)
            .setAnchorView(activity_main_extendedFloatingActionButton)
            .show()
        return true
    }

    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (v != null) {
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                activity_main_extendedFloatingActionButton.hide(true)
                return
            }
            activity_main_extendedFloatingActionButton.show(true)
            if (scrollY > oldScrollY) {
                activity_main_extendedFloatingActionButton.icon = resources.getDrawable(
                    R.drawable.ic_arrow_upward_white_24dp
                )
                activity_main_extendedFloatingActionButton.shrink(true)
                return
            }
            if (!activity_main_extendedFloatingActionButton.isExtended) {
                activity_main_extendedFloatingActionButton.icon = resources.getDrawable(
                    R.drawable.ic_add_white_24dp
                )
                activity_main_extendedFloatingActionButton.extend(true)
            }
        }
    }

    override fun onBackPressed() {
        if (activity_main_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activity_main_drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        moveTaskToBack(false)
    }

}
