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

package io.github.kallydev.emoticon.provider.theme

import android.app.ActivityManager
import android.graphics.BitmapFactory
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.base.BaseActivity
import io.github.kallydev.emoticon.module.main.MainActivity

object ThemeProvider {

    enum class Theme {
        THEME_BLUE, THEME_RED
    }

    fun apply(activity: BaseActivity) {
        if (hasTheme(activity)) {
            return
        }
    }

    fun hasTheme(activity: BaseActivity) = when (activity) {
        is MainActivity -> false
        else -> true
    }
}
