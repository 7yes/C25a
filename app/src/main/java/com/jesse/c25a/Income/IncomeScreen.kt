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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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

@Composable
fun IncomeScreen() {
    var expensesState by remember { mutableStateOf(ExpensesState()) }

    Row(
        modifier = Modifier.fillMaxSize(),
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
                })

            WeeklyExpensesCard(expenses = expensesState.weeklyExpenses,
                onExpensesChange = { newExpenses ->
                    expensesState = expensesState.copy(weeklyExpenses = newExpenses)
                    expensesState.calculateTotals()
                })

        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TotalExpenses(
                totalMonthly = expensesState.totalMonthly,
                totalWeekly = expensesState.totalWeekly,
                totalCombined = expensesState.totalCombined
            )
            IncomeHourlyCard(income = expensesState.incomeHourly, onExpensesChange = { newIncome ->
                expensesState = expensesState.copy(incomeHourly = newIncome)
                expensesState.calculateTotals()
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
            Text(text = "Monthly Expenses", style = MaterialTheme.typography.bodyLarge)
            MonthlyExpensesCategory.entries.forEach { category ->
                ExpenseTextField(label = category.name, value = when (category) {
                    Car -> expenses.car
                    Insurance -> expenses.insurance
                    Phone -> expenses.phone
                    Streaming -> expenses.streaming
                    Col -> expenses.col
                }, onValueChange = {
                    onExpensesChange(
                        when (category) {
                            Car -> expenses.copy(car = it)
                            Insurance -> expenses.copy(insurance = it)
                            Phone -> expenses.copy(phone = it)
                            Streaming -> expenses.copy(streaming = it)
                            Col -> expenses.copy(col = it)
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
            Text(text = "Income: ${expensesState.totalIncome }")
            Text(text = "10%: ${expensesState.tot10Percent}")
            Text(text = "lqsmdlg: ${expensesState.tot10Percent}")
        }
    }
}

data class MonthlyExpenses(
    var car: String = "",
    var insurance: String = "",
    var phone: String = "",
    var streaming: String = "",
    var col: String = ""
)

data class WeeklyExpenses(
    var food: String = "", var gas: String = "", var mer: String = ""
)

data class IncomeHourly(
    var ft: String = "",
    var snd: String = "",
)

data class ExpensesState(
    var monthlyExpenses: MonthlyExpenses = MonthlyExpenses(),
    var weeklyExpenses: WeeklyExpenses = WeeklyExpenses(),
    var incomeHourly: IncomeHourly = IncomeHourly(),
    var totalMonthly: Int = 0,
    var totalWeekly: Int = 0,
    var totalCombined: Int = 0,
    var totalIncome: Int = 0,
    var tot10Percent: Int = 0
) {
    fun calculateTotals() {
        totalMonthly = calculateMonthlyTotal()
        totalWeekly = calculateWeeklyTotal()
        totalCombined = (totalMonthly * 70 / 3) + totalWeekly
        totalIncome = calculateIncome()
        tot10Percent = totalIncome / 10
    }

    private fun calculateMonthlyTotal(): Int {
        return listOf(
            monthlyExpenses.car,
            monthlyExpenses.insurance,
            monthlyExpenses.phone,
            monthlyExpenses.streaming,
            monthlyExpenses.col
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
    Car, Insurance, Phone, Streaming, Col
}

enum class HourlyIncomeCategory {
    Ft, Snd,
}
