package com.jesse.c25a.Income

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jesse.c25a.Income.HourlyIncomeCategory.Ft
import com.jesse.c25a.Income.HourlyIncomeCategory.Snd
import com.jesse.c25a.Income.MonthlyExpensesCategory.Car
import com.jesse.c25a.Income.MonthlyExpensesCategory.Col
import com.jesse.c25a.Income.MonthlyExpensesCategory.Insurance
import com.jesse.c25a.Income.MonthlyExpensesCategory.Phone
import com.jesse.c25a.Income.MonthlyExpensesCategory.Streaming
import com.jesse.c25a.Income.WeeklyExpensesCategory.Food
import com.jesse.c25a.Income.WeeklyExpensesCategory.Gas
import com.jesse.c25a.Income.WeeklyExpensesCategory.Mer
import kotlinx.serialization.Serializable

@Composable
fun IncomeScreen(viewModel: IncomeVMod = hiltViewModel()) {
    var expensesState by remember { mutableStateOf(ExpensesState()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadExpensesState(context).collect {
            expensesState = it
        }
    }

    Row(
        modifier = Modifier.fillMaxSize().padding(top = 16.dp),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            MonthlyExpensesCard(expenses = expensesState.monthlyExpenses,
                onExpensesChange = { newExpenses ->
                    expensesState = expensesState.copy(monthlyExpenses = newExpenses)
                    expensesState.calculateTotals()
                    viewModel.saveExpensesState(context = context, expensesState)
                })

            WeeklyExpensesCard(expenses = expensesState.weeklyExpenses,
                onExpensesChange = { newExpenses ->
                    expensesState = expensesState.copy(weeklyExpenses = newExpenses)
                    expensesState.calculateTotals()
                    viewModel.saveExpensesState(context = context, expensesState)
                })

        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TotalExpenses(
                totalMonthly = expensesState.totalMonthlyExpenses,
                totalWeekly = expensesState.totalWeeklyExpenses,
                totalCombined = expensesState.totalCombinedExpenses,
            )
            IncomeHourlyCard(income = expensesState.incomeHourly,
                onExpensesChange = { newIncome ->
                    expensesState = expensesState.copy(incomeHourly = newIncome)
                    expensesState.calculateTotals()
                    viewModel.saveExpensesState(context = context, expensesState)
                })
            Snapshot(expensesState = expensesState)
        }

    }
}

@Composable
fun MonthlyExpensesCard(
    expenses: MonthlyExpenses, onExpensesChange: (MonthlyExpenses) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Mo Expenses", style = MaterialTheme.typography.bodyLarge)
            MonthlyExpensesCategory.entries.forEach { category ->
                ExpenseTextField(label = category.name, value = when (category) {
                    Car -> expenses.car
                    Insurance -> expenses.insurance
                    Phone -> expenses.phone
                    Streaming -> expenses.streaming
                    Col -> expenses.col
                    MonthlyExpensesCategory.Rent -> expenses.rent
                    MonthlyExpensesCategory.TC -> expenses.tc
                }, onValueChange = {
                    onExpensesChange(
                        when (category) {
                            Car -> expenses.copy(car = it)
                            Insurance -> expenses.copy(insurance = it)
                            Phone -> expenses.copy(phone = it)
                            Streaming -> expenses.copy(streaming = it)
                            Col -> expenses.copy(col = it)
                            MonthlyExpensesCategory.Rent -> expenses.copy(rent = it)
                            MonthlyExpensesCategory.TC -> expenses.copy(tc = it)
                        }
                    )
                })
            }
        }
    }
}

@Composable
fun WeeklyExpensesCard(
    expenses: WeeklyExpenses, onExpensesChange: (WeeklyExpenses) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Weekly Expenses", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))

            WeeklyExpensesCategory.entries.forEach { category ->
                ExpenseTextField(label = category.name, value = when (category) {
                    Food -> expenses.food
                    Gas -> expenses.gas
                    Mer -> expenses.mer
                }, onValueChange = {
                    onExpensesChange(
                        when (category) {
                            Food -> expenses.copy(food = it)
                            Gas -> expenses.copy(gas = it)
                            Mer -> expenses.copy(mer = it)
                        }
                    )
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it.filter { it.isDigit() }) },
        label = { Text(label, style = MaterialTheme.typography.bodyMedium) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TotalExpenses(totalMonthly: Int, totalWeekly: Int, totalCombined: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "T. Expenses Weekly", style = MaterialTheme.typography.headlineSmall)
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Monthly: ")
                Text(text = "$$totalMonthly")
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Weekly: ")
                Text(text = "$$totalWeekly")
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Cmb Wkly: ")
                Text(text = "$$totalCombined")
            }
        }
    }
}

@Composable
fun IncomeHourlyCard(income: IncomeHourly, onExpensesChange: (IncomeHourly) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hourly Rate")
            HourlyIncomeCategory.entries.forEach { category ->
                ExpenseTextField(label = category.name, value = when (category) {
                    Ft -> income.ft
                    Snd -> income.snd
                }, onValueChange = {
                    onExpensesChange(
                        when (category) {
                            Ft -> income.copy(ft = it)
                            Snd -> income.copy(snd = it)
                        }
                    )
                })
            }
        }
    }
}

@Composable
fun Snapshot(expensesState: ExpensesState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Snapshot Wkly")
            Text(text = "Income: ${expensesState.totalIncome}")
            Text(text = "10%: ${expensesState.tot10Percent}")
            Text(text = "lqsmdlg: ${expensesState.tot10Percent}")
            Text(text = "Free: ${expensesState.totFree}")
        }
    }
}

@Serializable
data class MonthlyExpenses(
    var car: String = "",
    var insurance: String = "",
    var phone: String = "",
    var streaming: String = "",
    var col: String = "",
    var rent: String = "",
    var tc: String = "",
)

@Serializable
data class WeeklyExpenses(
    var food: String = "", var gas: String = "", var mer: String = ""
)

@Serializable
data class IncomeHourly(
    var ft: String = "",
    var snd: String = "",
)

data class ExpensesState(
    var monthlyExpenses: MonthlyExpenses = MonthlyExpenses(),
    var weeklyExpenses: WeeklyExpenses = WeeklyExpenses(),
    var incomeHourly: IncomeHourly = IncomeHourly(),
    var totalMonthlyExpenses: Int = 0,
    var totalWeeklyExpenses: Int = 0,
    var totalCombinedExpenses: Int = 0,
    var totalIncome: Int = 0,
    var tot10Percent: Int = 0,
    var totFree: Int = 0,
) {
    fun calculateTotals() {
        totalMonthlyExpenses = calculateMonthlyTotal()
        totalWeeklyExpenses = calculateWeeklyTotal()
        totalCombinedExpenses = (totalMonthlyExpenses * 70 / 300) + totalWeeklyExpenses
        totalIncome = calculateIncome()
        tot10Percent = totalIncome / 10
        totFree = totalIncome - totalCombinedExpenses - tot10Percent - tot10Percent
    }

    private fun calculateMonthlyTotal(): Int {
        return listOf(
            monthlyExpenses.rent,
            monthlyExpenses.car,
            monthlyExpenses.insurance,
            monthlyExpenses.phone,
            monthlyExpenses.streaming,
            monthlyExpenses.col,
            monthlyExpenses.tc,
        ).sumOf { it.toIntOrZero() }
    }

    private fun calculateWeeklyTotal(): Int {
        return listOf(
            weeklyExpenses.food, weeklyExpenses.gas, weeklyExpenses.mer
        ).sumOf { it.toIntOrZero() }
    }

    private fun calculateIncome(): Int {
        val sumPerHour = listOf(
            incomeHourly.ft, incomeHourly.snd
        ).sumOf { it.toIntOrZero() }
        return (sumPerHour * 40 * 0.76).toInt()
    }
}

fun String.toIntOrZero(): Int {
    return this.toIntOrNull() ?: 0
}

enum class WeeklyExpensesCategory {
    Food, Gas, Mer
}

enum class MonthlyExpensesCategory {
    Rent,Car, Insurance, Phone, Streaming, Col, TC
}

enum class HourlyIncomeCategory {
    Ft, Snd,
}
