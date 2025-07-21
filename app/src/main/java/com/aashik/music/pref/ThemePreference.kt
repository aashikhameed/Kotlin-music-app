package com.aashik.music.pref

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "theme_preferences")

class ThemePreference(context: Context) {
    private val appContext = context.applicationContext
    private val dataStore = appContext.dataStore

    companion object {
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }

    val isDarkMode: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[DARK_MODE_KEY] ?: false
    }

    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = enabled
        }
    }
}

