package com.fero.skripsi.ui.pelanggan.rating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.databinding.ActivityRatingPenjahitBinding
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.model.Rating
import com.fero.skripsi.ui.main.HomePelangganActivity
import com.fero.skripsi.ui.pelanggan.rating.viewmodel.RatingPenjahitViewModel
import com.fero.skripsi.utils.ViewModelFactory

class RatingPenjahitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatingPenjahitBinding

    companion object {
        const val EXTRA_DATA_RATING_PENJAHIT = "EXTRA_DATA_RATING_PENJAHIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingPenjahitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Rating Penjahit"

        val dataPesanan: Pesanan? = intent.extras?.getParcelable(EXTRA_DATA_RATING_PENJAHIT)
        val idPenjahit = dataPesanan?.id_penjahit


        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[RatingPenjahitViewModel::class.java]

        viewModel.apply {
            dataRating.observe(this@RatingPenjahitActivity, {
//                val intent = Intent(this@RatingPenjahitActivity, HomePelangganActivity::class.java)
//                startActivity(intent)
                finish()
            })

            messageSuccess.observe(this@RatingPenjahitActivity, {
                Toast.makeText(this@RatingPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })

            showProgress.observe(this@RatingPenjahitActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@RatingPenjahitActivity, {
                Toast.makeText(this@RatingPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })

        }

        binding.btnRating.setOnClickListener {

            val kriteria1 : Int = binding.rbKriteria1.rating.toInt()
            val kriteria2 : Int = binding.rbKriteria2.rating.toInt()
            val kriteria3 : Int = binding.rbKriteria3.rating.toInt()
            val kriteria4 : Int = binding.rbKriteria4.rating.toInt()

            binding.rbKriteria1.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener{
                override fun onRatingChanged(
                    ratingBar: RatingBar?,
                    rating: Float,
                    fromUser: Boolean
                ) {
                    Toast.makeText(applicationContext, "Rating : $rating", Toast.LENGTH_SHORT).show()
                }

            })

            binding.rbKriteria2.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener{
                override fun onRatingChanged(
                    ratingBar: RatingBar?,
                    rating: Float,
                    fromUser: Boolean
                ) {
                    Toast.makeText(applicationContext, "Rating : $rating", Toast.LENGTH_SHORT).show()
                }

            })

            binding.rbKriteria3.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener{
                override fun onRatingChanged(
                    ratingBar: RatingBar?,
                    rating: Float,
                    fromUser: Boolean
                ) {
                    Toast.makeText(applicationContext, "Rating : $rating", Toast.LENGTH_SHORT).show()
                }

            })

            binding.rbKriteria4.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener{
                override fun onRatingChanged(
                    ratingBar: RatingBar?,
                    rating: Float,
                    fromUser: Boolean
                ) {
                    Toast.makeText(applicationContext, "Rating : $rating", Toast.LENGTH_SHORT).show()
                }

            })


            val dataRating = Rating(
                0,
                idPenjahit,
                kriteria1,
                kriteria2,
                kriteria3,
                kriteria4,
            )


            viewModel.insertDataRating(dataRating)
        }

    }
}