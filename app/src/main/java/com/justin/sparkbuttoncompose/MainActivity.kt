package com.justin.sparkbuttoncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justin.sparkbutton.SparkButton
import com.justin.sparkbuttoncompose.ui.theme.SparkButtonComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SparkButtonComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        SparkButtonView()
                    }
                }
            }
        }
    }
}


@Composable
fun SparkButtonView() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {

        SparkButton(
            modifier = Modifier.size(50.dp),
            animationTime = 900,
            dotSize = 15f,
            enabledIcon = R.drawable.ic_thumb_filled,
            disableIcon = R.drawable.ic_thumb_outline,
            bigDotColor = Color.Green.copy(alpha = .6f),
            smallDotColor = Color.Black.copy(alpha = .5f),
            onClickListener = {}
        )

    }
}
