package com.example.modul2compopse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.modul2compopse.ui.theme.Modul2CompopseTheme
import java.text.NumberFormat.getCurrencyInstance


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul2CompopseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    TipCalculatorApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculatorApp() {
    // untuk textfield
    var amountInput by remember { mutableStateOf("") }

    // untuk dropdown
    var tipInput by remember {mutableStateOf("15")}
    var expanded by remember {mutableStateOf(false)}
    val tipOptions = listOf("15","18","20")

    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    val tip = calculateTip(amount, tipPercent, roundUp)

    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Menampilkan teks judul
        Text(
            text = "Calculate Tip",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )

        TextField(
            value = amountInput,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.toDoubleOrNull() != null) {
                    amountInput = newValue
                }
            },
            label = { Text("Bill Amount") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Bill Icon"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        //dropdown tip
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            TextField(
                value = "$tipInput%",
                onValueChange = { },
                readOnly = true,
                label = { Text("Tip Percentage") },
                leadingIcon = {
                    Text(
                        text = "%",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false}
            ) {
                tipOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text("$selectionOption%") },
                        onClick =  {
                            tipInput = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Round up tip?")
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }

        Text(
            text = "Tip Amount: $tip",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

private fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): String {

    var tip = (tipPercent / 100) * amount


    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }

    return getCurrencyInstance().format(tip)
}