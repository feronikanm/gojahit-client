package com.fero.skripsi.ui.pelanggan.pesanan

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.fero.skripsi.databinding.ActivityPesananBinding
import java.util.*

class PesananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPilihTanggal.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                binding.tvTanggalSelesai.text = ("" + year + "-" + month + "-" + dayOfMonth)
            }, year, month, day)
            dpd.show()
        }




    }
}