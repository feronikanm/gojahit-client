package com.fero.skripsi.ui.penjahit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentProfilePenjahitBinding
import com.fero.skripsi.ui.main.HomePelangganActivity
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.utils.PrefHelper

class ProfilePenjahitFragment : Fragment() {

    private lateinit var binding: FragmentProfilePenjahitBinding
    lateinit var prefHelper: PrefHelper

    val EXTRA_PENJAHIT = "EXTRA_PENJAHIT"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfilePenjahitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        supportActionBar?.title = "Data Penjahit"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnEditProfil.setOnClickListener {
            val moveIntent = Intent(context, EditDataPenjahitActivity::class.java)
            startActivity(moveIntent)
        }

        binding.btnLogout.setOnClickListener {
            Toast.makeText(context, "Logout Berhasil" , Toast.LENGTH_SHORT).show()
            val moveIntent = Intent(context, PilihUserActivity::class.java)
            startActivity(moveIntent)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfilePenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}