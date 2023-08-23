package com.zs.mangareader.core.domain.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.zs.mangareader.core.utils.Constants

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = Constants.THEME_DATASTORE)