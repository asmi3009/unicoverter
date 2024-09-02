package com.example.uniconvert

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val lengthtv = findViewById<TextView>(R.id.lengthtv)
        val weighttv = findViewById<TextView>(R.id.weighttv)
        val edlen = findViewById<EditText>(R.id.editTextNumber3)
        val edweight = findViewById<EditText>(R.id.editTextweight)
        val convertlength = findViewById<Button>(R.id.lenconvernt)
        val convertweight = findViewById<Button>(R.id.weightconvern)
        val spinnerid = findViewById<Spinner>(R.id.snipper)
        val spinnerweight = findViewById<Spinner>(R.id.snipperweight)
        val spinnercovertlen = findViewById<Spinner>(R.id.spinnerconvertlength)
        val spinnerconertweight = findViewById<Spinner>(R.id.spinnerconvertweight)
        val length = arrayOf("Kilometer", "Centimeter", "Meter", "Mile", "Decimeter")
        val weight = arrayOf(
            "Gigatonne",
            "Megatonne",
            "Tonne",
            "Kilogram",
            "Gram",
            "Miligram",
            "Microgram",
            "Nanogram"
        )
        val arrayadp =
            ArrayAdapter(this@MainActivity, android.R.layout.simple_selectable_list_item, length)
        spinnerid.adapter = arrayadp
        val arrayadpwe =
            ArrayAdapter(this@MainActivity, android.R.layout.simple_selectable_list_item, weight)
        spinnerweight.adapter = arrayadpwe
        val arrayadpconverlen =
            ArrayAdapter(this@MainActivity, android.R.layout.simple_selectable_list_item, length)
        spinnercovertlen.adapter = arrayadpconverlen
        val arrayadpconverwe =
            ArrayAdapter(this@MainActivity, android.R.layout.simple_selectable_list_item, weight)
        spinnerconertweight.adapter = arrayadpconverwe
        var selectedlengthUnit = ""
        var selectedweightunit = ""
        var selectedConvertUnit = ""
        var selectedconvertweightunit = ""

        spinnerid?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedlengthUnit = length[position]
                Toast.makeText(
                    this@MainActivity,
                    "length selected: $selectedlengthUnit",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // You should also implement this method
            }
        }

        spinnerweight?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedweightunit = weight[position]

                Toast.makeText(
                    this@MainActivity,
                    "weight selected: $selectedweightunit", // Change length to weight
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinnercovertlen?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedConvertUnit = length[position]
                Toast.makeText(this@MainActivity, "Converted len", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinnerconertweight?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedconvertweightunit = weight[position] // Change length to weight
                Toast.makeText(this@MainActivity, "Converted weight", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Nothing selected", Toast.LENGTH_SHORT).show()
            }
        }
        convertlength.setOnClickListener {
            val currentLength = edlen.text.toString()
            if (currentLength.isEmpty()) {
                Toast.makeText(this@MainActivity, "Input the length", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val lengthValue = currentLength.toDouble()
                val convertedLength =
                    this.convertLengthUnit(lengthValue, selectedlengthUnit, selectedConvertUnit)
                val resultText =
                    "$currentLength $selectedlengthUnit is equal to $convertedLength $selectedConvertUnit"
                Toast.makeText(this, resultText, Toast.LENGTH_SHORT).show()
                lengthtv.text = resultText // Set the text of the lengthtv TextView
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this@MainActivity,
                    "Invalid input. Please enter a valid number",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        convertweight.setOnClickListener {
            val currentweight = edweight.text.toString()
            if (currentweight.isEmpty()) {
                Toast.makeText(this@MainActivity, "Input the weight", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            try {
                val weightvalue = currentweight.toDouble()
                val convertedweight = this.convertWeightunit(
                    weightvalue,
                    selectedweightunit,
                    selectedconvertweightunit
                )
                val resulttext =
                    "$currentweight $selectedweightunit is equal to $convertedweight $selectedconvertweightunit"
                Toast.makeText(this, resulttext, Toast.LENGTH_SHORT).show()
                weighttv.text = resulttext
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this@MainActivity,
                    "Invalid Weight.Enter a vsl weight",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    fun convertLengthUnit(length: Double, fromUnit: String, toUnit: String): Double {
        val meter = when (fromUnit) {
            "Kilometer" -> length * 1000
            "Centimeter" -> length / 100
            "Meter" -> length
            "Mile" -> length * 1609.34
            "Decimeter" -> length / 10
            else -> 0.0
        }

        return when (toUnit) {
            "Kilometer" -> meter / 1000
            "Centimeter" -> meter * 100
            "Meter" -> meter
            "Mile" -> meter / 1609.34
            "Decimeter" -> meter * 10
            else -> 0.0
        }
    }
    fun convertWeightunit(weight:Double, fromUnit: String,toUnit: String): Double {
        val gram = when(fromUnit){
            "Gigatonne" ->weight * 1000000000000000
            "Megatonne" -> weight *1000000000000
            "Tonne"-> weight * 1000000
            "Kilogram"-> weight * 1000
            "Gram"-> weight
            "Miligram"->weight * 0.001
            "Microgram"-> weight * 0.000001
            "Nanogram"-> weight * 0.000000001
            else -> 0.0
        }
        return when (toUnit) {
            "Gigatonne" ->gram / 1000000000000000
            "Megatonne" -> gram / 1000000000000
            "Tonne"-> gram / 1000000
            "Kilogram"-> gram / 1000
            "Gram"-> gram
            "Miligram"->gram / 0.001
            "Microgram"-> gram / 0.000001
            "Nanogram"-> gram / 0.000000001

            else -> 0.0
        }

        }
}

