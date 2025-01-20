package com.jesse.c25a.hg.parallax

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jesse.bopag.ui.theme.Purple40
import com.jesse.c25a.R
import java.lang.Float.min

@Composable
fun ParallaxEffectScreen() {

    val scrollState = rememberScrollState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .graphicsLayer {
                        alpha =  if(scrollState.value.toFloat() / scrollState.maxValue>0.5f){0f}
                        else 1f - (scrollState.value.toFloat() / scrollState.maxValue)
                        translationY = 0.5f * scrollState.value
                    }, contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.king_kohli),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = stringResource(id = R.string.title),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(id = R.string.desc),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodyLarge
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .graphicsLayer {
                    alpha = min(1f, (scrollState.value.toFloat() / scrollState.maxValue))
                }
                .background(color = Purple40), contentAlignment = Alignment.CenterStart
        ) {

            Text(
                text = "King is Back",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(horizontal = 12.dp),
                color = Color.White
            )
        }
    }
}