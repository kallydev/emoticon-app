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

package io.github.kallydev.emoticon.provider.emoticon

import android.os.Environment
import io.github.kallydev.emoticon.bean.EmoticonBean
import io.github.kallydev.emoticon.bean.EmoticonPackageBean
import java.io.File

object EmoticonManager {

    @Suppress("DEPRECATION")
    private val emoticonPackageRootFile =
        File(Environment.getExternalStorageDirectory().absolutePath + "/kallydev/emoticon")

    private val configFile = File(emoticonPackageRootFile.absolutePath + "/config.json")

    fun loadConfigFile() {

    }

    fun loadEmoticonPackage(): Array<EmoticonPackageBean> {
        if (!emoticonPackageRootFile.exists()) {
            emoticonPackageRootFile.mkdirs()
        }
        val emoticonPackageBeanArrayList = ArrayList<EmoticonPackageBean>()
        val emoticonPackageFile = (emoticonPackageRootFile.listFiles() as Array<File>).filter {
            it.isDirectory
        }
        emoticonPackageFile.forEach { it ->
            val emoticonBeanArrayList = ArrayList<EmoticonBean>()
            emoticonPackageBeanArrayList.add(EmoticonPackageBean(it.name, false, emoticonBeanArrayList))
            val emoticonFile = (it.listFiles() as Array<File>).filter {
                Regex(".*.(jpg|png|gif)").matches(it.name)
            }
            emoticonFile.forEach {
                emoticonBeanArrayList.add(EmoticonBean(it))
            }
        }
        return emoticonPackageBeanArrayList.toTypedArray()
    }

}
