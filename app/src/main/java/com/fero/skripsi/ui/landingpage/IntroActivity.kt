package com.fero.skripsi.ui.landingpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityIntroBinding
import com.fero.skripsi.ui.main.HomePenjahitActivity
import com.fero.skripsi.ui.main.PilihUserActivity

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    private val introSlideAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Everything you need at your fingertips",
                "Tailor recommendations, Tailor search, your profile and saved Tailor only a touch away",
                R.drawable.img_intro1
            ),
            IntroSlide(
                "Choose and Design your Own clothes",
                "Choose your Tailor, Design your clothes, and wait the sewing process",
                R.drawable.img_intro2
            ),
            IntroSlide(
                "Proudly wear your clothes and rate the Tailor",
                "Help Tailor with wear your best design clothes and rate the Tailor",
                R.drawable.img_intro3
            ),
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpIntro.adapter = introSlideAdapter
        setupIndicators()
        setCurrentIndicator(0)

        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        binding.btnIntroNext.setOnClickListener {
            if (binding.vpIntro.currentItem + 1 < introSlideAdapter.itemCount) {
                binding.vpIntro.currentItem += 1
            } else {
                Intent(applicationContext, PilihUserActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        binding.tvIntroSkip.setOnClickListener {
            Intent(applicationContext, PilihUserActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}