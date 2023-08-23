package com.zs.mangareader.core.domain.helpers

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.zs.mangareader.core.domain.extensions.datastore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeDataStore @Inject constructor(
    private val context: Context
) {

    private val themePreference = booleanPreferencesKey("theme")

    val isDarkTheme = context.datastore.data.map {
        it[themePreference] ?: true
    }

    suspend fun savePreference(themeState: Boolean) {
        context.datastore.edit {
            it[themePreference] = themeState
        }
    }
}