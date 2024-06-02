package com.example.appdetailfree.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.appdetailfree.R
import com.example.appdetailfree.databinding.ActivityMainBinding
import com.example.appdetailfree.databinding.LayoutCustomDialogueBinding
import com.example.appdetailfree.fragment.IncomeFragment
import com.example.appdetailfree.fragment.SpendingFragment
import com.example.appdetailfree.model.DetailCost
import com.example.appdetailfree.model.DetailCostModel
import com.example.appdetailfree.model.FireBaseCostService
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { DetailCostModel() }
    private val fragmentSpending = SpendingFragment()
    private val fragmentIncome = IncomeFragment()
    private var type = "Spending"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        handleEvent()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleEvent() {
        changeBackgroundCustom()
        replaceFragment()
        selectDate()
        putDatabase()
        viewDetail()
        exitApp()
    }

    private fun exitApp() {
        binding.linearLogOut.setOnClickListener {
            showCustomDialogBox()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showCustomDialogBox() {
        val dialogueBinding by lazy { LayoutCustomDialogueBinding.inflate(layoutInflater) }
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(dialogueBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogueBinding.tvMessageDialogue.text = "Are you sure want to log out ?"

        dialogueBinding.imgYes.setOnClickListener {
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginRegisterActivity::class.java))
            finishAffinity()
        }

        dialogueBinding.imgNo.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun viewDetail() {
        binding.linearStatistics.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }

    private fun putDatabase() {
        binding.btnSave.setOnClickListener {
            val dataPut = DetailCost(
                userId = FireBaseCostService.authentication.currentUser!!.uid,
                type = type,
                dateCreate = binding.tvDate.text.toString(),
                note = binding.edtNote.text.toString(),
                cash = binding.edtTotalFee.text.toString().toDouble(),
                category = binding.edtDetailCategories.text.toString()
            )

            val result = viewModel.putData(data = dataPut)

            if (result) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun selectDate() {
        val calendar = Calendar.getInstance()

        binding.tvDate.setOnClickListener {
            showDataPicker(calendar)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDataPicker(calendar: Calendar) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.tvDate.text = dateFormat.format(calendar.time)

                binding.imgBack.setOnClickListener {
                    calendar.add(Calendar.DAY_OF_MONTH, -1)
                    binding.tvDate.text = dateFormat.format(calendar.time)
                }

                binding.imgNext.setOnClickListener {
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                    binding.tvDate.text = dateFormat.format(calendar.time)
                }
            },
            year, month, day,
        )

        datePickerDialog.show()
    }

    private fun replaceFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentSpendingIncome.id, fragmentSpending)
            .commit()
    }

    @SuppressLint("SetTextI18n")
    private fun changeBackgroundCustom() {
        var isChange = true

        binding.tvSpending.setOnClickListener {
            type = "Spending"
            isChange = !isChange
            change(isChange = isChange)
            binding.edtDetailCategories.hint = "Eating"
            resetAll()
            replace(fragment = fragmentSpending)
        }

        binding.tvIncome.setOnClickListener {
            type = "Income"
            isChange = !isChange
            change(isChange = isChange)
            binding.edtDetailCategories.hint = "Salary"
            resetAll()
            replace(fragment = fragmentIncome)
        }
    }

    private fun resetAll() {
        binding.tvDate.text = ""
        binding.edtNote.setText("")
        binding.edtTotalFee.setText("")
        binding.edtDetailCategories.setText("")
    }

    private fun replace(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentSpendingIncome.id, fragment)
            .setReorderingAllowed(true)
            .commit()
    }

    private fun change(isChange: Boolean) {
        if (isChange) {
            binding.tvSpending.background = ContextCompat.getDrawable(this, R.drawable.custom_edt)
            binding.tvIncome.background =
                ContextCompat.getDrawable(this, R.drawable.custom_edt_hide)
        } else {
            binding.tvIncome.background = ContextCompat.getDrawable(this, R.drawable.custom_edt)
            binding.tvSpending.background =
                ContextCompat.getDrawable(this, R.drawable.custom_edt_hide)
        }
    }
}