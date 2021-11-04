package com.fero.skripsi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseActivity
import com.fero.skripsi.databinding.ActivityHomePenjahitBinding
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.pelanggan.ProfilePelangganFragment
import com.fero.skripsi.ui.penjahit.TransaksiPenjahitFragment
import com.fero.skripsi.ui.penjahit.DashboardPenjahitFragment
import com.fero.skripsi.ui.penjahit.ProfilePenjahitFragment
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.PrefHelper
import com.google.gson.Gson

class HomePenjahitActivity : BaseActivity<ActivityHomePenjahitBinding>() {

    lateinit var prefHelper: PrefHelper

    companion object {
        const val EXTRA_LOGIN_PENJAHIT = "EXTRA_LOGIN_PENJAHIT"
    }

    override fun setupViewBinding(): ActivityHomePenjahitBinding {
        return ActivityHomePenjahitBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        // TODO("Not yet implemented")
    }

    override fun setupUI(savedInstanceState: Bundle?) {

        val profilePenjahitFragment = ProfilePenjahitFragment.newInstance()

        prefHelper = PrefHelper(this)
        val extraData: Penjahit? = intent.extras?.getParcelable(EXTRA_LOGIN_PENJAHIT)

        binding.tvUsername.text = extraData!!.nama_penjahit
        Glide.with(this)
            .load("${Constant.IMAGE_PENJAHIT}${extraData.foto_penjahit}")
            .into(binding.imgPenjahit)

        val bundle = Bundle()
        val bundleData = Gson().toJson(extraData)
        bundle.putString("EXTRA_PENJAHIT", bundleData)
        profilePenjahitFragment.arguments = bundle

        addFragment(DashboardPenjahitFragment.newInstance())
        binding.apply {
            bottomNavigation.show(0)
            bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
            bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_add))
            bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_profile))

            bottomNavigation.setOnClickMenuListener {
                when (it.id) {
                    0 -> {
                        replaceFragment(DashboardPenjahitFragment.newInstance())
                    }
                    1 -> {
                        replaceFragment(TransaksiPenjahitFragment.newInstance())
                    }
                    2 -> {
                        replaceFragment(ProfilePenjahitFragment.newInstance())
                    }
                    else -> {
                        replaceFragment(DashboardPenjahitFragment.newInstance())
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