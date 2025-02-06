package com.jesse.c25a.Income

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.jesse.c25a.Income.MonthlyExpensesCategory.AnyMail
import com.jesse.c25a.Income.MonthlyExpensesCategory.Car
import com.jesse.c25a.Income.MonthlyExpensesCategory.Insurance
import com.jesse.c25a.Income.MonthlyExpensesCategory.Phone
import com.jesse.c25a.Income.MonthlyExpensesCategory.Streaming
import com.jesse.c25a.Income.WeeklyExpensesCategory.Food
import com.jesse.c25a.Income.WeeklyExpensesCategory.Gas
import com.jesse.c25a.Income.WeeklyExpensesCategory.Mer

@Composable
fun IncomeScreen() {
    var expensesState by remember { mutableStateOf(ExpensesState()) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    MonthlyExpensesCard(
                        expenses = expensesState.monthlyExpenses,
                        onExpensesChange = { newExpenses ->
                            expensesState = expensesState.copy(monthlyExpenses = newExpenses)
                            expensesState.calculateTotals()
                        }
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    WeeklyExpensesCard(
                        expenses = expensesState.weeklyExpenses,
                        onExpensesChange = { newExpenses ->
                            expensesState = expensesState.copy(weeklyExpenses = newExpenses)
                            expensesState.calculateTotals()
                        }
                    )
                }
            }

        TotalExpenses(
            totalMonthly = expensesState.totalMonthly,
            totalWeekly = expensesState.totalWeekly,
            totalCombined = expensesState.totalCombined
        )
        Column (Modifier.fillMaxWidth().weight(3f)){ Text(text = "Total Expenses") }

    }
}

@Composable
fun MonthlyExpensesCard(
    expenses: MonthlyExpenses,
    onExpensesChange: (MonthlyExpenses) -> Unit
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
                ExpenseTextField(
                    label = category.name,
                    value = when (category) {
                        Car -> expenses.car
                        Insurance -> expenses.insurance
                        Phone -> expenses.phone
                        Streaming -> expenses.streaming
                        AnyMail -> expenses.anymail
                    },
                    onValueChange = {
                        onExpensesChange(
                            when (category) {
                                Car -> expenses.copy(car = it)
                                Insurance -> expenses.copy(insurance = it)
                                Phone -> expenses.copy(phone = it)
                                Streaming -> expenses.copy(streaming = it)
                                AnyMail -> expenses.copy(anymail = it)
                            })
                    }
                )
            }
        }
    }
}

@Composable
fun WeeklyExpensesCard(
    expenses: WeeklyExpenses,
    onExpensesChange: (WeeklyExpenses) -> Unit
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
                ExpenseTextField(
                    label = category.name,
                    value = when (category) {
                        Food -> expenses.food
                        Gas -> expenses.gas
                        Mer -> expenses.mer
                    },
                    onValueChange = {
                        onExpensesChange(
                            when (category) {
                                Food -> expenses.copy(food = it)
                                Gas -> expenses.copy(gas = it)
                                Mer -> expenses.copy(mer = it)
                            }
                        )
                    }
                )
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
fun TotalExpenses(totalMonthly: Double, totalWeekly: Double, totalCombined: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
           // verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Total Expenses", style = MaterialTheme.typography.headlineSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Monthly: ")
                Text(text = "$$totalMonthly")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Weekly: ")
                Text(text = "$$totalWeekly")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Combined Wkly: ")
                Text(text = "$$totalCombined")
            }
        }
    }
}

data class MonthlyExpenses(
    var car: String = "",
    var insurance: String = "",
    var phone: String = "",
    var streaming: String = "",
    var anymail: String = ""
)

data class WeeklyExpenses(
    var food: String = "",
    var gas: String = "",
    var mer: String = ""
)

data class ExpensesState(
    var monthlyExpenses: MonthlyExpenses = MonthlyExpenses(),
    var weeklyExpenses: WeeklyExpenses = WeeklyExpenses(),
    var totalMonthly: Double = 0.0,
    var totalWeekly: Double = 0.0,
    var totalCombined: Double = 0.0
) {
    fun calculateTotals() {
        totalMonthly = calculateMonthlyTotal()
        totalWeekly = calculateWeeklyTotal()
        totalCombined = (totalMonthly*70/3).toInt().toDouble()/100 + totalWeekly
    }

    private fun calculateMonthlyTotal(): Double {
        return listOf(
            monthlyExpenses.car,
            monthlyExpenses.insurance,
            monthlyExpenses.phone,
            monthlyExpenses.streaming,
            monthlyExpenses.anymail
        ).sumOf { it.toDoubleOrZero() }
    }

    private fun calculateWeeklyTotal(): Double {
        return listOf(
            weeklyExpenses.food,
            weeklyExpenses.gas,
            weeklyExpenses.mer
        ).sumOf { it.toDoubleOrZero() }
    }
}

fun String.toDoubleOrZero(): Double {
    return this.toDoubleOrNull() ?: 0.0
}

enum class WeeklyExpensesCategory {
    Food, Gas, Mer
}

enum class MonthlyExpensesCategory {
    Car, Insurance, Phone, Streaming, AnyMail
}
