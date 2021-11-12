package com.fero.skripsi.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseActivity
import com.fero.skripsi.databinding.ActivityHomePelangganBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.ui.pelanggan.dashboard.DashboardPelangganFragment
import com.fero.skripsi.ui.pelanggan.auth.ProfilePelangganFragment
import com.fero.skripsi.ui.pelanggan.transaksi.TransaksiPelangganFragment
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ID_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_NAMA_PELANGGAN
import com.google.gson.Gson

class HomePelangganActivity : BaseActivity<ActivityHomePelangganBinding>() {

    lateinit var prefHelper: PrefHelper

    companion object {
        const val EXTRA_LOGIN_PELANGGAN = "EXTRA_LOGIN_PELANGGAN"
    }

    override fun setupViewBinding(): ActivityHomePelangganBinding {
        return ActivityHomePelangganBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        //TODO("Not yet implemented")
    }

    override fun setupUI(savedInstanceState: Bundle?) {

        val dashboardPelangganFragment = DashboardPelangganFragment()
        val transaksiPelangganFragment = TransaksiPelangganFragment.newInstance()
        val profilePelangganFragment = ProfilePelangganFragment.newInstance()

        prefHelper = PrefHelper(this)
//        binding.tvUsername2.text = prefHelper.getString(PREF_NAMA_PELANGGAN)
//        binding.tvHi2.text = prefHelper.getString(PREF_ID_PELANGGAN)

        val extraData: Pelanggan? = intent.extras?.getParcelable(EXTRA_LOGIN_PELANGGAN)

        binding.tvUsername.text = extraData!!.nama_pelanggan
        Glide.with(this)
            .load("${Constant.IMAGE_PELANGGAN}${extraData.foto_pelanggan}")
            .into(binding.imgPelanggan)

        val bundle = Bundle()
        val bundleData = Gson().toJson(extraData)
        bundle.putString("EXTRA_PELANGGAN", bundleData)
        profilePelangganFragment.arguments = bundle
        transaksiPelangganFragment.arguments = bundle

        dashboardPelangganFragment.baseNewInstance("EXTRA_PELANGGAN_DASHBOARD",extraData)
        extraData.nama_pelanggan?.let { Log.d("EXTRA PELANGGAN DASH", it) }

        addFragment(dashboardPelangganFragment)
        binding.apply {
            bottomNavigation.show(0)
            bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
            bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_check))
            bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_profile))

            bottomNavigation.setOnClickMenuListener {
                when (it.id) {
                    0 -> {
                        replaceFragment(dashboardPelangganFragment)
                    }
                    1 -> {
                        replaceFragment(transaksiPelangganFragment)
                    }
                    2 -> {
                        replaceFragment(profilePelangganFragment)
                    }
                    else -> {
                        replaceFragment(dashboardPelangganFragment)
                    }
                }
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container, fragment)
            .addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragment_container, fragment)
            .addToBackStack(Fragment::class.java.simpleName).commit()
    }
}