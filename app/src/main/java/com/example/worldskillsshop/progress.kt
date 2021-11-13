package com.example.worldskillsshop

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskillsshop.adapter_RV.adapter_coupon
import com.example.worldskillsshop.adapter_RV.adapter_progress
import com.example.worldskillsshop.data_history_RV.progressRV
import com.example.worldskillsshop.data_history_RV.сoupon
import com.example.worldskillsshop.databinding.ActivityProgressBinding

class progress : AppCompatActivity() {

    var bar: ActionBar? = null

    private val adapter = adapter_progress()

    lateinit var binding: ActivityProgressBinding

    var actBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvFun("Сделайте 1 заказ, чтобы получить бронзовый уровень. С эти уровнем вам станут доступны следующие скидки:" +
                "\n     1. Купон на день рождения","1")
        rvFun("Сделайте 10 заказов, чтобы получить серебряный уровень. С эти уровнем вам станут доступны следующие скидки:" +
                "\n     1. Получаете дополнительную скидку 5%" +
                "\n     2.Спецкупон (Получайте спей купон каждый раз, когда повышается ваш уровень) ","2")
        rvFun("Сделайте 25 заказов, чтобы получить золотой уровень. С эти уровнем вам станут доступны следующие скидки:" +
                "\n     1. Получите дополнительную скидку 8%","3")
        rvFun("Сделайте 35 заказов, чтобы получить платиновый уровень. С эти уровнем вам станут доступны следующие скидки:" +
                "\n     1. Получите дополнительную скидку 10%" +
                "\n     2. Скидка на доставку 20%","4")
        rvFun("Сделайте 50 заказов, чтобы получить бриллиантовый уровень. С эти уровнем вам станут доступны следующие скидки:" +
                "\n     1. Получите дополнительную скидку 15%" +
                "\n     2. Эксклюзивные купоны (Получайте эксклюзивный купон раз в неделю. Количество купонов ограничено)","5")
        bar = supportActionBar
        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#141414")))
        bar?.title = "Уровени"
    }

    fun rvFun(text_progress:String,medal:String){

        binding.RVProgress.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        binding.RVProgress.adapter = adapter

        val card = progressRV(text_progress,medal)
        adapter.addCard(card)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        val id = item.itemId
        when (id)
        {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}