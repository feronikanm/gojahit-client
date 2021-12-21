package com.fero.skripsi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseActivity
import com.fero.skripsi.databinding.ActivityHomePenjahitBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.pelanggan.auth.viewmodel.AuthPelangganViewModel
import com.fero.skripsi.ui.penjahit.transaksi.TransaksiPenjahitFragment
import com.fero.skripsi.ui.penjahit.dashboard.DashboardPenjahitFragment
import com.fero.skripsi.ui.penjahit.kategori.KategoriPenjahitFragment
import com.fero.skripsi.ui.penjahit.auth.ProfilePenjahitFragment
import com.fero.skripsi.ui.penjahit.auth.viewmodel.AuthPenjahitViewModel
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.ViewModelFactory
import com.google.gson.Gson

class HomePenjahitActivity : BaseActivity<ActivityHomePenjahitBinding>() {

    lateinit var prefHelper: PrefHelper

    companion object {
        const val EXTRA_LOGIN_PENJAHIT = "EXTRA_LOGIN_PENJAHIT"
    }

    private val factory by lazy {
        ViewModelFactory.getInstance(this)
    }

    private val authPenjahitViewModel by lazy {
        ViewModelProvider(this, factory)[AuthPenjahitViewModel::class.java]
    }

    private val extraData: Penjahit? by lazy {
        intent.getParcelableExtra(EXTRA_LOGIN_PENJAHIT)
    }

    override fun setupViewBinding(): ActivityHomePenjahitBinding {
        return ActivityHomePenjahitBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        authPenjahitViewModel.apply {
            dataPenjahitVM.observe(this@HomePenjahitActivity, {
                binding.apply {

                    tvUsername.text = it.nama_penjahit

                    if(it.foto_penjahit != null){
                        Glide.with(this@HomePenjahitActivity)
                            .load("${Constant.IMAGE_PENJAHIT}${it!!.foto_penjahit}")
                            .into(imgPenjahit)
                    }
                }
            })
        }

        authPenjahitViewModel.getDataPenjahitById(extraData!!)
    }

    override fun setupUI(savedInstanceState: Bundle?) {

        val dashboardPenjahitFragment = DashboardPenjahitFragment.newInstance()
        val transaksiPenjahitFragment = TransaksiPenjahitFragment.newInstance()
        val kategoriPenjahitFragment = KategoriPenjahitFragment.newInstance()
        val profilePenjahitFragment = ProfilePenjahitFragment.newInstance()

        prefHelper = PrefHelper(this)
//        val extraData: Penjahit? = intent.extras?.getParcelable(EXTRA_LOGIN_PENJAHIT)

//        binding.tvUsername.text = extraData!!.nama_penjahit
//        Glide.with(this)
//            .load("${Constant.IMAGE_PENJAHIT}${extraData.foto_penjahit}")
//            .into(binding.imgPenjahit)

        val bundle = Bundle()
        val bundleData = Gson().toJson(extraData)
        bundle.putString("EXTRA_PENJAHIT", bundleData)
        profilePenjahitFragment.arguments = bundle
        transaksiPenjahitFragment.arguments = bundle

        dashboardPenjahitFragment.baseNewInstance("EXTRA_PENJAHIT_DASHBOARD", extraData)
        kategoriPenjahitFragment.baseNewInstance("EXTRA_PENJAHIT_KATEGORI", extraData)

        addFragment(dashboardPenjahitFragment)
        binding.apply {
            bottomNavigation.show(0)
            bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
            bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_shopping_cart))
            bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_category))
            bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_profile))

            bottomNavigation.setOnClickMenuListener {
                when (it.id) {
                    0 -> {
                        replaceFragment(dashboardPenjahitFragment)
                    }
                    1 -> {
                        replaceFragment(transaksiPenjahitFragment)
                    }
                    2 -> {
                        replaceFragment(kategoriPenjahitFragment)
                    }
                    3 -> {
                        replaceFragment(profilePenjahitFragment)
                    }
                    else -> {
                        replaceFragment(dashboardPenjahitFragment)
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