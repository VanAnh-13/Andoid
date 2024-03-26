package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityDarkModeBinding
import com.example.calculator.databinding.ActivityMainBinding

class DarkMode : AppCompatActivity() {
    private val binding by lazy { ActivityDarkModeBinding.inflate(layoutInflater) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addEvent()
    }

    // Variable get operator
    private var operator = ""

    // Variable result
    private var result = 0.0

    /**
     * This method is use to get number of calculator
     *
     * @author Le Van Anh
     */
    private fun getCalculate() {
        binding.btnNumber0.setOnClickListener(this::getCalculate)
        binding.btnNumber1.setOnClickListener(this::getCalculate)
        binding.btnNumber2.setOnClickListener(this::getCalculate)
        binding.btnNumber3.setOnClickListener(this::getCalculate)
        binding.btnNumber4.setOnClickListener(this::getCalculate)
        binding.btnNumber5.setOnClickListener(this::getCalculate)
        binding.btnNumber6.setOnClickListener(this::getCalculate)
        binding.btnNumber7.setOnClickListener(this::getCalculate)
        binding.btnNumber8.setOnClickListener(this::getCalculate)
        binding.btnNumber9.setOnClickListener(this::getCalculate)
        binding.btnDivision.setOnClickListener(this::getCalculate)
        binding.btnMultiplication.setOnClickListener(this::getCalculate)
        binding.btnSummation.setOnClickListener(this::getCalculate)
        binding.btnSubtraction.setOnClickListener(this::getCalculate)
        binding.btnComma.setOnClickListener(this::getCalculate)
    }

    /**
     * This method is use to get calculator
     *
     * @author Le Van Anh
     */
    private fun getCalculate(view: View) {
        when (view.id) {
            binding.btnNumber0.id -> {
                operator += binding.btnNumber0.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber1.id -> {
                operator += binding.btnNumber1.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber2.id -> {
                operator += binding.btnNumber2.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber3.id -> {
                operator += binding.btnNumber3.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber4.id -> {
                operator += binding.btnNumber4.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber5.id -> {
                operator += binding.btnNumber5.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber6.id -> {
                operator += binding.btnNumber6.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber7.id -> {
                operator += binding.btnNumber7.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber8.id -> {
                operator += binding.btnNumber8.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnNumber9.id -> {
                operator += binding.btnNumber9.text.toString()
                binding.txtOutput.text = operator
            }

            binding.btnDivision.id -> {
                operator += " : "
                binding.txtOutput.text = operator
            }

            binding.btnMultiplication.id -> {
                operator += " x "
                binding.txtOutput.text = operator
            }

            binding.btnSummation.id -> {
                operator += " " + binding.btnSummation.text.toString() + " "
                binding.txtOutput.text = operator
            }

            binding.btnSubtraction.id -> {
                operator += " " + binding.btnSubtraction.text.toString() + " "
                binding.txtOutput.text = operator
            }

            binding.btnComma.id -> {
                operator += "."
                binding.txtOutput.text = operator
            }
        }
    }

    /**
     * This function is use to process string
     *
     * @author Le Van Anh
     */
    @SuppressLint("SetTextI18n")
    private fun calculator(calculate: String) {
        if (!calculate[0].isDigit()) {
            binding.txtOutput.text = "SYNTAX ERROR"
            return
        }

        val (number1, operator, number2) = calculate.split(" ")

        when (operator) {
            "+" -> result = number1.toDouble() + number2.toDouble()
            "-" -> result = number1.toDouble() - number2.toDouble()
            "x" -> result = number1.toDouble() * number2.toDouble()
            ":" -> result = number1.toDouble() / number2.toDouble()
        }
    }

    /**
     * display result onto screen
     *
     * @author Le Van Anh
     */
    @SuppressLint("SetTextI18n")
    private fun addEvent() {
        getCalculate()

        binding.btnResult.setOnClickListener {
            calculator(calculate = operator)
            binding.txtOutput.text = String.format("%s =\n %.3f", operator, result)
        }

        binding.btnReset.setOnClickListener {
            operator = ""
            result = 0.0
            binding.txtOutput.text = ""
        }

        binding.btnLightMode.setOnClickListener {
            finish()
        }
    }
}