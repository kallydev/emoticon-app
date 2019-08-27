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
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.GravityCompat
import androidx.core.widget.NestedScrollView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.adapter.SourceAdapter
import io.github.kallydev.emoticon.base.BaseActivity
import io.github.kallydev.emoticon.bean.SourceBean
import io.github.kallydev.emoticon.module.author.AuthorActivity
import io.github.kallydev.emoticon.module.main.fragment.EmoticonPackageFragment
import io.github.kallydev.emoticon.provider.fragment.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_emoticon.view.*
import kotlinx.coroutines.*

class MainActivity : BaseActivity(), MainView,
    NavigationView.OnNavigationItemSelectedListener,
    NestedScrollView.OnScrollChangeListener,
    SwipeRefreshLayout.OnRefreshListener,
    View.OnClickListener {

    private val model = MainModel()
    private val viewModel = MainViewModel(this, model)
    private val fragmentManager = FragmentManager(supportFragmentManager)

    object Fragment {
        const val EMOTICON_PACKAGE = "EmoticonPackageFragment"
        const val PERMISSION = "PermissionFragment"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun onInit() {
        initView()
        // initFragment()
        onRefresh()
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

    override fun onBackPressed() {
        if (activity_main_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activity_main_drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        moveTaskToBack(false)
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

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            else -> {
                openAuthorActivity()
            }
        }
        activity_main_drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(p0: View?) {
        when (p0!!) {
            activity_main_extendedFloatingActionButton -> {
                if (!activity_main_extendedFloatingActionButton.isExtended) {
                    smoothScrollToTop()
                    return
                }
                val view = LayoutInflater.from(this).inflate(R.layout.fragment_add_emoticon, null, false)
                val bottomSheetDialog = BottomSheetDialog(this)
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
                // AddEmoticonFragment().show(supportFragmentManager, "dialog")
            }
        }
    }

    override fun onRefresh() {
        viewModel.checkPermissionsAndLoadEmoticonPackage(rxPermissions!!)
    }

    override fun showFragment(fragmentName: String) {
        // fragmentManager.showFragment(fragmentName)
    }

    override fun onSourceLoading() {
        if (!activity_main_swipeRefreshLayout.isRefreshing) {
            activity_main_swipeRefreshLayout.isRefreshing = true
        }
    }

    override fun onSourceLoadedSuccessful(sourceBeanArrayList: ArrayList<SourceBean>) {
        activity_main_swipeRefreshLayout.isRefreshing = false
        val sourceAdapter = activity_main_viewPager.adapter as SourceAdapter
        sourceAdapter.setSourceArrayList(sourceBeanArrayList)
    }

    override fun onSourceLoadedError(state: Int) {

    }

//    override fun onEmoticonPackageLoading() {
//        if (!activity_main_swipeRefreshLayout.isRefreshing) {
//            activity_main_swipeRefreshLayout.isRefreshing = true
//        }
//    }
//
//    override fun onLocalEmoticonPackageLoadedSuccessful(emoticonPackageBeanArray: Array<EmoticonPackageBean>) {
////        activity_main_swipeRefreshLayout.isRefreshing = false
////        val emoticonFragment = fragmentManager.getFragment("EmoticonPackageFragment") as EmoticonPackageFragment
////        val emoticonFragmentRecyclerView = emoticonFragment.view!!.fragment_emoticon_fastScrollRecyclerView
////        val emoticonPackageAdapter = emoticonFragmentRecyclerView.adapter as EmoticonPackageAdapter
////        emoticonPackageAdapter.loadEmoticonPackage(emoticonPackageBeanArray, false)
//    }
//
//    override fun onLocalEmoticonPackageLoadedError(state: Int) {
////        activity_main_swipeRefreshLayout.isRefreshing = false
////        showFragment(PERMISSION)
//    }
//
//    override fun onNetworkEmoticonPackageLoadedSuccessful(emoticonPackageBeanArray: Array<EmoticonPackageBean>) {
//        Snackbar.make(activity_main_coordinatorLayout, "咕咕咕", Snackbar.LENGTH_LONG)
//            .setAnchorView(activity_main_extendedFloatingActionButton)
//            .show()
//        // activity_main_swipeRefreshLayout.isRefreshing = false
//        val emoticonFragment = fragmentManager.getFragment("EmoticonPackageFragment") as EmoticonPackageFragment
//        val emoticonFragmentRecyclerView = emoticonFragment.view!!.fragment_emoticon_fastScrollRecyclerView
//        val emoticonPackageAdapter = emoticonFragmentRecyclerView.adapter as EmoticonPackageAdapter
//        emoticonPackageAdapter.loadEmoticonPackage(emoticonPackageBeanArray, true)
//    }
//
//    override fun onNetworkEmoticonPackageLoadedError(state: Int) {
//
//    }

//    private fun initFragment() {
//        fragmentManager.addFragmentArray(
//            R.id.activity_main_frameLayout,
//            hashMapOf(
//                EMOTICON_PACKAGE to EmoticonPackageFragment(),
//                PERMISSION to PermissionFragment()
//            )
//        )
//        fragmentManager.hideAllFragment()
//    }

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
        activity_main_extendedFloatingActionButton.setOnClickListener(this)
        activity_main_nestedScrollView.setOnScrollChangeListener(this)
        activity_main_swipeRefreshLayout.setColorSchemeColors(
            resources.getColor(R.color.colorBlue500),
            resources.getColor(R.color.colorPinkA200)
        )
        activity_main_swipeRefreshLayout.setOnRefreshListener(this)
        activity_main_viewPager.adapter = SourceAdapter(supportFragmentManager)
        activity_main_tabLayout.setupWithViewPager(activity_main_viewPager)
    }

    private fun smoothScrollToTop() {
        val emoticonFragment = fragmentManager.getFragment("EmoticonPackageFragment") as EmoticonPackageFragment
        emoticonFragment.view!!.fragment_emoticon_fastScrollRecyclerView.stopScroll()
        activity_main_nestedScrollView.smoothScrollTo(0, 0)
        activity_main_bottomAppBar.performShow()
    }

    private fun openAuthorActivity() {
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
    }

    fun openApplicationSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", packageName, null)
        startActivity(intent)
    }

}
