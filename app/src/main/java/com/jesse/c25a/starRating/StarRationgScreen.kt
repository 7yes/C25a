package com.jesse.c25a.starRating

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StarRatingScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var rating1 by remember { mutableFloatStateOf(3.5f) }
        RatingBar(
            modifier = Modifier.size(30.dp),
            rating = rating1,
            onRatingChanged = { rating1 = it })
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
    spaceBetween: Dp = 0.dp,
    onRatingChanged: (Float) -> Unit
) {
    var isHalfStar: Boolean = rating % 1 != 0f
    Row {
        for (i in 1..stars) {
            Icon(
                modifier = modifier.clickable {onRatingChanged(i.toFloat())},
                contentDescription = null,
                tint = starsColor,
                imageVector = if (i <= rating) Icons.Filled.Star else if (isHalfStar) {
                    isHalfStar = false
                    Icons.AutoMirrored.Rounded.StarHalf
                } else Icons.Rounded.StarBorder
            )

        }
    }
}