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
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import io.github.kallydev.emoticon.base.BaseViewModel
import io.github.kallydev.emoticon.bean.EmoticonPackageBean
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class MainViewModel(private val view: MainView, private val model: MainModel) : BaseViewModel(view, model) {

    fun loadEmoticonPackage() {
        model.loadEmoticonPackage()
            .subscribe(object : SingleObserver<Array<EmoticonPackageBean>> {

                override fun onSuccess(t: Array<EmoticonPackageBean>) {
                    view.showFragment(MainActivity.Fragment.EMOTICON_PACKAGE)
                    view.onEmoticonPackageLoadedSuccessful(t)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.showFragment(MainActivity.Fragment.PERMISSION)
                }
            })
    }

    fun checkPermissionsAndLoadEmoticonPackage(rxPermissions: RxPermissions) {
        rxPermissions.requestEachCombined(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe(object : Observer<Permission> {

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Permission) {
                if (t.granted) {
                    view.onEmoticonPackageLoading()
                    loadEmoticonPackage()
                } else {
                    view.onEmoticonPackageLoadedError(0)
                }
            }

            override fun onError(e: Throwable) {

            }

        })
    }

}
