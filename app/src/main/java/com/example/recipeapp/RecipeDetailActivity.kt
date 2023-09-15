package com.example.recipeapp

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecipeDetailBinding

    var imgCrop = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("img")).into(binding.foodItemImg)
        binding.tittle.text = intent.getStringExtra("tittle")
        binding.stepsData.text = intent.getStringExtra("des")

        binding.backBtn.setOnClickListener {
            finish()
        }

        var ing = intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        binding.time.text = ing?.get(0)

        for(i in 1 until ing!!.size){
            binding.ingData.text =
                """${binding.ingData.text} 🟢 ${ing[i]}
                    
                """.trimIndent()

        }

        binding.steps.background = null
        binding.steps.setTextColor(getColor(R.color.black))

        binding.steps.setOnClickListener {
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.setTextColor(getColor(R.color.white))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.background = null
            binding.stepsSv.visibility = View.VISIBLE
            binding.ingSv.visibility = View.GONE
        }

        binding.ing.setOnClickListener {
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.ing.setTextColor(getColor(R.color.white))
            binding.steps.setTextColor(getColor(R.color.black))
            binding.steps.background = null
            binding.ingSv.visibility = View.VISIBLE
            binding.stepsSv.visibility = View.GONE
        }



        binding.fullScreen.setOnClickListener {
            if(imgCrop){
                binding.foodItemImg.scaleType = ImageView.ScaleType.FIT_CENTER
                binding.fullScreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                binding.shadow.visibility = View.GONE
                imgCrop = !imgCrop
            }
            else{
                binding.foodItemImg.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.fullScreen.setColorFilter(null)
                binding.shadow.visibility = View.VISIBLE
                imgCrop = !imgCrop
            }
        }
    }
}