package id.co.ppa_github.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.co.ppa_github.framework.local.UserDetailsDao

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ContentProviderEntryPoint {

    fun userDetailsDao(): UserDetailsDao
}