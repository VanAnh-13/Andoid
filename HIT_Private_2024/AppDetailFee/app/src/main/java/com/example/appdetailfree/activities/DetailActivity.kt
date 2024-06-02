package com.example.appdetailfree.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdetailfree.R
import com.example.appdetailfree.adapter.DetailItemAdapter
import com.example.appdetailfree.databinding.ActivityDetailBinding
import com.example.appdetailfree.dialogueCustom.showLoading
import com.example.appdetailfree.model.DetailCategoryItem
import com.example.appdetailfree.model.DetailCost
import com.example.appdetailfree.model.DetailCostModel
import com.example.appdetailfree.model.DetailItem
import com.example.appdetailfree.model.FireBaseCostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[DetailCostModel::class.java] }
    private var listDetailCost = mutableListOf<DetailCost>()
    private var listAdapter = mutableListOf<DetailItem>()
    private val loadingDialogue by lazy {
        Dialog(this)
    }
    private val adapter by lazy {
        DetailItemAdapter() { liner, label, cost ->
            onItemClickListener(liner = liner, label = label, cost = cost)
        }
    }

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
        getDatabase()
        nextAndPeriodMonth()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun nextAndPeriodMonth() {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dateFill = LocalDate.parse("01/${binding.tvDateDetail.text}", formatter)
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("MM/yyyy", Locale.getDefault())
        calendar.set(dateFill.year, dateFill.monthValue, 1)

        binding.imgBackDetail.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            binding.tvDateDetail.text = format.format(calendar.time)
            listAdapter = emptyList<DetailItem>().toMutableList()
            calculateFee()
        }
        binding.imgNextDetail.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            binding.tvDateDetail.text = format.format(calendar.time)
            listAdapter = emptyList<DetailItem>().toMutableList()
            calculateFee()
        }
    }

    private fun setAdapter() {
        val listItem = mutableMapOf<String, Double>()

        listAdapter.forEach { item ->
            if (listItem.containsKey(item.label)) {
                listItem[item.label]?.let { total ->
                    listItem.put(
                        item.label,
                        total.plus(item.total)
                    )
                }
            } else {
                listItem[item.label] = item.total
            }
        }

        listAdapter = emptyList<DetailItem>().toMutableList()

        listItem.forEach { (label, result) ->
            listAdapter.add(
                DetailItem(
                    img = getImgRes(label = label),
                    label = label,
                    total = result
                )
            )
        }

        handleRecyclerView(listItem = listAdapter)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateFee() {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dateFill = LocalDate.parse("01/${binding.tvDateDetail.text}", formatter)

        var spending = 0.0
        var income = 0.0

        listDetailCost.forEach { detailCost ->
            val date = LocalDate.parse(detailCost.dateCreate, formatter)

            if (
                detailCost.type.lowercase() == "spending"
                && detailCost.userId == FireBaseCostService.authentication.currentUser!!.uid
                && date.year == dateFill.year
                && date.monthValue == dateFill.monthValue
            ) {
                spending += detailCost.cash

                listAdapter.add(
                    DetailItem(
                        img = null,
                        label = detailCost.category,
                        total = detailCost.cash
                    )
                )
            } else if (
                detailCost.type.lowercase() == "income"
                && detailCost.userId == FireBaseCostService.authentication.currentUser!!.uid
                && date.year == dateFill.year
                && date.monthValue == dateFill.monthValue
            ) {
                income += detailCost.cash

                listAdapter.add(
                    DetailItem(
                        img = null,
                        label = detailCost.category,
                        total = detailCost.cash
                    )
                )
            }
        }

        val formatNumber = DecimalFormat("#.###")
        formatNumber.minimumFractionDigits = 3
        binding.tvSpendingDetail.text = formatNumber.format(spending)
        binding.tvIncomeDetail.text = formatNumber.format(income)
        binding.tvTotalDetail.text = formatNumber.format(spending + income)

        setAdapter()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDatabase() {
        loadingDialogue.showLoading(this)

        viewModel.listDetailCost.observe(this) { listCosts ->
            listDetailCost.addAll(listCosts!!)
            loadingDialogue.dismiss()
            calculateFee()
        }
    }

    private fun getImgRes(label: String) =
        when (label.lowercase()) {
            "eating" -> R.drawable.eating
            "monthly spending" -> R.drawable.spending
            "clothes and cosmetics" -> R.drawable.dress
            "collaborate fee" -> R.drawable.collaborate
            "medical" -> R.drawable.medical
            "study" -> R.drawable.study
            "transport" -> R.drawable.car
            "contact" -> R.drawable.smartphone
            "electric bill, house fee" -> R.drawable.home
            "income" -> R.drawable.salary
            "allowance" -> R.drawable.receive_cash
            "bonus" -> R.drawable.bonus
            "secondary income" -> R.drawable.euro_money
            "invest" -> R.drawable.profit
            "temporary income" -> R.drawable.duration_finance

            else -> 0
        }

    private fun onItemClickListener(liner: LinearLayout, label: String, cost: String) =
        liner.setOnClickListener {
            val formatNumber = DecimalFormat("#.###")
            formatNumber.minimumFractionDigits = 3

            val listItem = listDetailCost.filter { detailCost ->
                detailCost.userId == FireBaseCostService.authentication.currentUser!!.uid
                        && detailCost.category == label
            }.toMutableList()

            val listDetailCategory = listItem.map { item ->
                DetailCategoryItem(
                    label = item.note,
                    cost = formatNumber.format(item.cash),
                    date = item.dateCreate
                )
            }

            val intent = Intent(this, DetailCategoryActivity::class.java)
            intent.putExtra("label", label)
            intent.putExtra("total", cost)
            intent.putParcelableArrayListExtra("myListDetail", ArrayList(listDetailCategory))

            startActivity(intent)
        }


    private fun handleRecyclerView(listItem: MutableList<DetailItem>) {
        adapter.oldList = listItem
        binding.rvDetail.adapter = adapter
        binding.rvDetail.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}