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

import io.github.kallydev.emoticon.base.BaseModel
import io.github.kallydev.emoticon.bean.EmoticonPackageBean
import io.github.kallydev.emoticon.provider.emoticon.EmoticonManager
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainModel : BaseModel() {

    fun loadEmoticonPackage(): Single<Array<EmoticonPackageBean>> {
        return Single.create(SingleOnSubscribe<Array<EmoticonPackageBean>> {
            it.onSuccess(EmoticonManager.loadEmoticonPackage())
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

}
