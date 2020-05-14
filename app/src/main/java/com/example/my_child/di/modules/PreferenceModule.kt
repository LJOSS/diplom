package com.example.my_child.di.modules

import android.content.Context
import com.example.my_child.domain.preferences.PreferencesManager
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {
    @Provides
    fun providesPreferencesManager(context: Context): PreferencesManager =
        PreferencesManager(context)
}