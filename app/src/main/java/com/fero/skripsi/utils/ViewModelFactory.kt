package com.fero.skripsi.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.data.Repository
import com.fero.skripsi.di.Injection
import com.fero.skripsi.ui.pelanggan.AuthPelangganViewModel
import com.fero.skripsi.ui.pelanggan.DashboardPelangganViewModel
import com.fero.skripsi.ui.penjahit.AuthPenjahitViewModel
import com.fero.skripsi.ui.penjahit.DashboardPenjahitViewModel

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T{
        when{
            modelClass.isAssignableFrom(DashboardPenjahitViewModel::class.java) -> {
                return DashboardPenjahitViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DashboardPelangganViewModel::class.java) -> {
                return DashboardPelangganViewModel(repository) as T
            }

            modelClass.isAssignableFrom(AuthPelangganViewModel::class.java) -> {
                return AuthPelangganViewModel(repository) as T
            }

            modelClass.isAssignableFrom(AuthPenjahitViewModel::class.java) -> {
                return AuthPenjahitViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}