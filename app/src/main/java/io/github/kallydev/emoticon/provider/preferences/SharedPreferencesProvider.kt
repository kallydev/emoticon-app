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

package io.github.kallydev.emoticon.provider.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPreferencesProvider {

    private const val sharedPreferencesName = "emoticon"

    fun getString(context: Context, key: String): String {
        return context.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE).getString(key, "") as String
    }

    fun getBoolean(context: Context, key: String): Boolean {
        return context.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE).getBoolean(key, false)
    }

    fun getFloat(context: Context, key: String): Float {
        return context.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE).getFloat(key, 0F)
    }

    fun getInt(context: Context, key: String): Int {
        return context.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE).getInt(key, 0)
    }

    fun getLong(context: Context, key: String): Long {
        return context.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE).getLong(key, 0L)
    }

}
