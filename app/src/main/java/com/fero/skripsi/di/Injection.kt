package com.fero.skripsi.di

import android.content.Context
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): Repository {
        val remoteDataSource = RemoteDataSource.getInstance(context)
        return Repository.getInstance(remoteDataSource)
    }

}