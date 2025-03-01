package com.jesse.c25a.quick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.requestFocus
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.error
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.text.isBlank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickScreen() {
    var address by remember { mutableStateOf("") }
    var addressLine2 by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }

    var addressError by remember { mutableStateOf<String?>(null) }
    var cityError by remember { mutableStateOf<String?>(null) }
    var stateError by remember { mutableStateOf<String?>(null) }
    var zipCodeError by remember { mutableStateOf<String?>(null) }

    var isExpanded by remember { mutableStateOf(false) }
    val states = listOf("CA", "NY", "TX", "FL", "IL") // Example states

    val focusManager = LocalFocusManager.current
    val addressFocusRequester = remember { FocusRequester() }
    val addressLine2FocusRequester = remember { FocusRequester() }
    val cityFocusRequester = remember { FocusRequester() }
    val zipCodeFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        addressFocusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = address,
            onValueChange = {
                address = it
                addressError = null
            },
            label = { Text("Address") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(addressFocusRequester),
            isError = addressError != null,
            supportingText = {
                if (addressError != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = addressError!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addressLine2,
            onValueChange = { addressLine2 = it },
            label = { Text("Address Line 2 (Optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(addressLine2FocusRequester),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = city,
            onValueChange = {
                city = it
                cityError = null
            },
            label = { Text("City") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(cityFocusRequester),
            isError = cityError != null,
            supportingText = {
                if (cityError != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = cityError!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                isExpanded = true
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = state,
                onValueChange = {
                    state = it
                    stateError = null
                },
                readOnly = true,
                label = { Text("State") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                isError = stateError != null,
                supportingText = {
                    if (stateError != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stateError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                states.forEach { selectedState ->
                    DropdownMenuItem(
                        text = { Text(text = selectedState) },
                        onClick = {
                            state = selectedState
                            isExpanded = false
                            stateError = null
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = zipCode,
            onValueChange = {
                zipCode = it
                zipCodeError = null
            },
            label = { Text("Zip Code") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(zipCodeFocusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            isError = zipCodeError != null,
            supportingText = {
                if (zipCodeError != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = zipCodeError!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                addressError = if (address.isBlank()) "Address is required" else null
                cityError = if (city.isBlank()) "City is required" else null
                stateError = if (state.isBlank()) "State is required" else null
                zipCodeError = when {
                    zipCode.isBlank() -> "Zip Code is required"
                    zipCode.length != 5 -> "Zip Code must be 5 digits"
                    else -> null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}
