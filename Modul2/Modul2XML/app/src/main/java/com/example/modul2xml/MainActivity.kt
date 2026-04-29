package com.example.modul2xml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var etBillAmount: TextInputEditText
    private lateinit var dropdownTip: AutoCompleteTextView
    private lateinit var switchRoundUp: SwitchMaterial
    private lateinit var tvTipAmount: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etBillAmount = findViewById(R.id.etBillAmount)
        dropdownTip = findViewById(R.id.dropdownTip)
        switchRoundUp = findViewById(R.id.switchRoundUp)
        tvTipAmount = findViewById(R.id.tvTipAmount)

        val tipOptions = listOf("15", "18", "20")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tipOptions)
        dropdownTip.setAdapter(adapter)
        dropdownTip.setText(tipOptions[0], false)


        etBillAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calculateAndDisplayTip()
            }
        })

        dropdownTip.setOnItemClickListener { _, _, _, _ ->
            calculateAndDisplayTip()
        }

        switchRoundUp.setOnCheckedChangeListener { _, _ ->
            calculateAndDisplayTip()
        }
    }

    private fun calculateAndDisplayTip() {
        val amountStr = etBillAmount.text.toString()
        val tipStr = dropdownTip.text.toString()

        val amount = amountStr.toDoubleOrNull() ?: 0.0
        val tipPercent = tipStr.toDoubleOrNull() ?: 0.0
        val isRoundUp = switchRoundUp.isChecked

        var tip = (tipPercent / 100) * amount
        if (isRoundUp) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tvTipAmount.text = "Tip Amount: $formattedTip"
    }
}