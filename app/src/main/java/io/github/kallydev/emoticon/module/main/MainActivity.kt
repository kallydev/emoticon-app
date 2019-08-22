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

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.GravityCompat
import androidx.core.widget.NestedScrollView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.base.BaseActivity
import io.github.kallydev.emoticon.module.author.AuthorActivity
import io.github.kallydev.emoticon.module.main.fragment.EmoticonFragment
import io.github.kallydev.emoticon.module.main.fragment.PermissionsFragment
import io.github.kallydev.emoticon.provider.fragment.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_emoticon.view.*
import kotlinx.coroutines.*
import permissions.dispatcher.*

open class MainActivity : BaseActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    NestedScrollView.OnScrollChangeListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val fragmentManager = FragmentManager(supportFragmentManager)

    private enum class Page {
        HOME
    }

    private var page = Page.HOME

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun onInit() {
        initFragment()
        initView()
        requestPermission()
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

    override fun onRefresh() {

    }

    override fun onBackPressed() {
        if (activity_main_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activity_main_drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        moveTaskToBack(false)
    }

    private fun initFragment() {
        fragmentManager.addFragmentArray(
            R.id.activity_main_frameLayout,
            hashMapOf(
                "EmoticonFragment" to EmoticonFragment(),
                "PermissionsFragment" to PermissionsFragment()
            )
        )
        fragmentManager.showFragment("EmoticonFragment")
    }

    private fun initView() {
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
            if (!activity_main_extendedFloatingActionButton.isExtended) {
                val emoticonFragment = fragmentManager.getFragment("EmoticonFragment") as EmoticonFragment
                emoticonFragment.view!!.fragment_emoticon_fastScrollRecyclerView.stopScroll()
                activity_main_nestedScrollView.smoothScrollTo(0, 0)
                activity_main_bottomAppBar.performShow()
                return@setOnClickListener
            }
            Snackbar.make(activity_main_coordinatorLayout, "咕咕咕", Snackbar.LENGTH_LONG)
                .setAnchorView(activity_main_extendedFloatingActionButton)
                .show()
        }
        activity_main_nestedScrollView.setOnScrollChangeListener(this)
        activity_main_swipeRefreshLayout.setColorSchemeColors(
            resources.getColor(R.color.colorBlue500),
            resources.getColor(R.color.colorPinkA200)
        )
        activity_main_swipeRefreshLayout.setOnRefreshListener(this)
    }

    @SuppressLint("CheckResult")
    protected fun requestPermission() {
        rxPermissions!!.requestEach(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe {
            when {
                it.granted -> {

                }
                else -> {
                    fragmentManager.showFragment("PermissionsFragment")
//                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                    val uri = Uri.fromParts("package", packageName, null)
//                    intent.data = uri
//                    startActivity(intent)
                }
            }
        }
    }

}
