package com.jesse.c25a.table

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val titles = listOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6")
val info = listOf(
    listOf("Row 1", "Data 1.1", "Data 1.2", "Data 1.3", "Data 1.4", "Data 1.5", "Data 1.6"),
    listOf("Row 2", "Data 2.1", "Data 2.2", "Data 2.3", "Data 2.4", "Data 2.5", "Data 2.6"),
    listOf("Row 3", "Data 3.1", "Data 3.2", "Data 3.3", "Data 3.4", "Data 3.5", "Data 3.6"),
    listOf("Row 4", "Data 4.1", "Data 4.2", "Data 4.3", "Data 4.4", "Data 4.5", "Data 4.6"),
    listOf("Row 5", "Data 5.1", "Data 5.2", "Data 5.3", "Data 5.4", "Data 5.5", "Data 5.6"),
    listOf("Row 6", "Data 6.1", "Data 6.2", "Data 6.3", "Data 6.4", "Data 6.5", "Data 6.6"),
    listOf("Row 7", "Data 7.1", "Data 7.2", "Data 7.3", "Data 7.4", "Data 7.5", "Data 7.6"),
    listOf("Row 8", "Data 8.1", "Data 8.2", "Data 8.3", "Data 8.4", "Data 8.5", "Data 8.6"),
    listOf("Row 9", "Data 9.1", "Data 9.2", "Data 9.3", "Data 9.4", "Data 9.5", "Data 9.6"),
    listOf("Row 10", "Data 10.1", "Data 10.2", "Data 10.3", "Data 10.4", "Data 10.5", "Data 10.6"),
    listOf("Row 11", "Data 11.1", "Data 11.2", "Data 11.3", "Data 11.4", "Data 11.5", "Data 11.6"),
    listOf("Row 12", "Data 12.1", "Data 12.2", "Data 12.3", "Data 12.4", "Data 12.5", "Data 12.6"),
    listOf("Row 13", "Data 13.1", "Data 13.2", "Data 13.3", "Data 13.4", "Data 13.5", "Data 13.6"),
    listOf("Row 14", "Data 14.1", "Data 14.2", "Data 14.3", "Data 14.4", "Data 14.5", "Data 14.6"),
    listOf("Row 15", "Data 15.1", "Data 15.2", "Data 15.3", "Data 15.4", "Data 15.5", "Data 15.6"),
)

@Composable
fun TableScreen() {
    Box(Modifier.fillMaxSize()) {
        TableScreen(titles = titles, info = info)
    }
}

@Composable
fun TableScreen(
    titles: List<String>,
    info: List<List<String>>,
    cellWidth: Dp = 100.dp,
    firstColumnWidth: Dp = getFirstColumnWidth(info.first()),
    firstRowHeight: Dp = 50.dp,
    otherRowHeight: Dp = 40.dp,
) {
    val columnScrollState = rememberLazyListState()
    val rowScrollState = rememberLazyListState()

    val firstColumnScrollState = rememberScrollState()
    val firstRowScrollState = rememberScrollState()

    val isFirstColumnScrolling by remember {
        derivedStateOf { firstColumnScrollState.value > 0 }
    }
    val isFirstRowScrolling by remember {
        derivedStateOf { firstRowScrollState.value > 0 }
    }

    LaunchedEffect(isFirstColumnScrolling) {
        if (isFirstColumnScrolling) {
            columnScrollState.scrollToItem(0)
        }
    }
    LaunchedEffect(isFirstRowScrolling) {
        if (isFirstRowScrolling) {
            rowScrollState.scrollToItem(0)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Fixed First Row (Titles)
        Row(
            modifier = Modifier
                .horizontalScroll(firstRowScrollState)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Box(
                modifier = Modifier
                    .width(firstColumnWidth)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
            titles.forEach { title ->
                TableCell(
                    text = title,
                    width = cellWidth,
                    height = firstRowHeight,
                    isHeader = true
                )
            }
            //
        }
        HorizontalDivider()

        // Scrollable Content
        Row(modifier = Modifier.weight(1f)) {
            // Fixed First Column
            Column(
                modifier = Modifier
                    .verticalScroll(firstColumnScrollState)
                    .width(firstColumnWidth)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                info.forEach { row ->
                    TableCell(
                        text = row.first(),
                        width = firstColumnWidth,
                        height = otherRowHeight,
                        isHeader = true
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .width(1.dp)
            )

            // Scrollable Content
            LazyRow(
                state = rowScrollState,
                modifier = Modifier.weight(1f)
            ) {
                items(info.first().lastIndex) { columnIndex ->
                    LazyColumn(
                        state = columnScrollState,
                        modifier = Modifier.width(cellWidth)
                    ) {
                        items(info.size) { rowIndex ->
                            TableCell(
                                text = info[rowIndex][columnIndex + 1],
                                width = cellWidth,
                                height = otherRowHeight,
                                isHeader = false
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getFirstColumnWidth(firstColumn: List<String>): Dp {
    var firstColumnMaxLetters = 0
    firstColumn.forEach {
        if (it.length > firstColumnMaxLetters) {
            firstColumnMaxLetters = it.length
        }
    }
    return (firstColumnMaxLetters * 10).dp
}

@Composable
fun TableCell(text: String, width: Dp, height: Dp, isHeader: Boolean) {
    Box(
        modifier = Modifier
            .width(width)
            .background(if (isHeader) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    HorizontalDivider()
}