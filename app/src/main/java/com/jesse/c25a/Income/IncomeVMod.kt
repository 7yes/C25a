package com.jesse.c25a.Income

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class IncomeVMod @Inject constructor(): ViewModel(){

        // Create the DataStore instance
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "expenses_data")

        // Define the keys for the data
        private object ExpensesKeys {
            val MONTHLY_EXPENSES = stringPreferencesKey("monthly_expenses")
            val WEEKLY_EXPENSES = stringPreferencesKey("weekly_expenses")
            val HOURLY_INCOME = stringPreferencesKey("hourly_income")
        }

        // Function to save the ExpensesState
        fun saveExpensesState(context: Context, expensesState: ExpensesState) {
            viewModelScope.launch {
                context.dataStore.edit { preferences ->
                    // Serialize the MonthlyExpenses and WeeklyExpenses to JSON strings
                    val monthlyExpensesJson = Json.encodeToString(expensesState.monthlyExpenses)
                    val weeklyExpensesJson = Json.encodeToString(expensesState.weeklyExpenses)
                    val hourlyIncomeJson = Json.encodeToString(expensesState.incomeHourly)
                    Log.d("TAj", "saveExpensesState: $expensesState ")
                    // Save the JSON strings to DataStore
                    preferences[ExpensesKeys.MONTHLY_EXPENSES] = monthlyExpensesJson
                    preferences[ExpensesKeys.WEEKLY_EXPENSES] = weeklyExpensesJson
                    preferences[ExpensesKeys.HOURLY_INCOME] = hourlyIncomeJson
                }
            }
        }

        // Function to load the ExpensesState
        fun loadExpensesState(context: Context): Flow<ExpensesState> {
            return context.dataStore.data.map { preferences ->
                // Get the JSON strings from DataStore
                val monthlyExpensesJson = preferences[ExpensesKeys.MONTHLY_EXPENSES] ?: "{}"
                val weeklyExpensesJson = preferences[ExpensesKeys.WEEKLY_EXPENSES] ?: "{}"
                val hourlyIncomeJson = preferences[ExpensesKeys.HOURLY_INCOME] ?: "{}"

                // Deserialize the JSON strings back to MonthlyExpenses and WeeklyExpenses
                val monthlyExpenses = Json.decodeFromString<MonthlyExpenses>(monthlyExpensesJson)
                val weeklyExpenses = Json.decodeFromString<WeeklyExpenses>(weeklyExpensesJson)
                val hourlyIncome = Json.decodeFromString<IncomeHourly>(hourlyIncomeJson)
                Log.d("TAj", "loadExpensesState: mon $monthlyExpenses ")
                Log.d("TAj", "loadExpensesState: wee $weeklyExpenses ")
                // Create and return the ExpensesState
                ExpensesState(
                    monthlyExpenses = monthlyExpenses,
                    weeklyExpenses = weeklyExpenses,
                    incomeHourly = hourlyIncome
                ).apply { calculateTotals() }
            }
        }
}