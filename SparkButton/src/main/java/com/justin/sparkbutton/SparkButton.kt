package com.justin.sparkbutton

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

private const val DOTS_COUNT = 8
private const val OUTER_DOTS_POSITION_ANGLE = 360 / DOTS_COUNT

@Composable
fun SparkButton(
    modifier: Modifier = Modifier,
    @DrawableRes enabledIcon: Int,
    @DrawableRes disableIcon: Int,
    dotSize: Float = 10f,
    animationTime: Int = 500,
    bigDotColor: Color = Color.Black,
    smallDotColor: Color = Color.Blue,
    enabledIconTintColor: Color = LocalContentColor.current,
    disableIconTintColor: Color = LocalContentColor.current,
    onClickListener: (Boolean) -> Unit = {}
) {
    val animationScope = rememberCoroutineScope()

    var showActiveState by remember { mutableStateOf(false) }

    val bigCircleAnimatable = remember { Animatable(1f) }

    val smallCircleAnimatable = remember { Animatable(1f) }

    val circleRadiusAnimatable = remember { Animatable(0f) }

    var radius = 0f
    var centerX: Float
    var centerY: Float

    var maxOuterDotsRadius: Float
    var maxInnerDotsRadius: Float

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.drawWithContent {
            centerX = this.size.width / 2f
            centerY = this.size.height / 2f

            maxOuterDotsRadius = dotSize
            maxInnerDotsRadius = 0.7f * maxOuterDotsRadius

            radius = centerX.coerceAtMost(centerY).plus(50)

            for (i in 0..DOTS_COUNT) {
                val cX =
                    (centerX + bigCircleAnimatable.value * cos(i * OUTER_DOTS_POSITION_ANGLE * Math.PI / 180)).toFloat()
                val cY =
                    (centerY + bigCircleAnimatable.value * sin(i * OUTER_DOTS_POSITION_ANGLE * Math.PI / 180)).toFloat()

                drawCircle(
                    color = bigDotColor,
                    radius = maxOuterDotsRadius.times(circleRadiusAnimatable.value),
                    center = Offset(x = cX, y = cY),
                )
            }

            for (i in 0..DOTS_COUNT) {
                val cX =
                    (centerX + smallCircleAnimatable.value * cos((i * OUTER_DOTS_POSITION_ANGLE - 20) * Math.PI / 180)).toFloat()
                val cY =
                    (centerY + smallCircleAnimatable.value * sin((i * OUTER_DOTS_POSITION_ANGLE - 20) * Math.PI / 180)).toFloat()

                drawCircle(
                    color = smallDotColor,
                    radius = maxInnerDotsRadius.times(circleRadiusAnimatable.value),
                    center = Offset(x = cX, y = cY),
                )
            }

            drawContent()
        }
    ) {
        Icon(
            painter = if (showActiveState) {
                painterResource(enabledIcon)
            } else {
                painterResource(disableIcon)
            },
            tint = if (showActiveState) {
                enabledIconTintColor
            } else {
                disableIconTintColor
            },
            contentDescription = "",
            modifier = modifier.clickable {

                showActiveState = !showActiveState

                onClickListener(showActiveState)

                if (showActiveState) {

                    val circleRadiusAnimationJob = animationScope.async {
                        circleRadiusAnimatable.animateTo(
                            targetValue = 0f,
                            animationSpec = keyframes {
                                durationMillis = animationTime
                                1f at (animationTime * 0.4).toInt() with  LinearOutSlowInEasing
                                1f at (animationTime * 0.8).toInt() with LinearOutSlowInEasing
                                0f at animationTime with LinearOutSlowInEasing
                            }
                        )
                    }

                    val bigCircleAnimationJob = animationScope.async {
                        bigCircleAnimatable.animateTo(
                            targetValue = radius,
                            animationSpec = tween(
                                durationMillis = animationTime,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }

                    val smallCircleAnimationJob = animationScope.async {
                        smallCircleAnimatable.animateTo(
                            targetValue = radius,
                            animationSpec = tween(
                                durationMillis = animationTime,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }

                    bigCircleAnimationJob.invokeOnCompletion {
                        animationScope.launch {
                            bigCircleAnimatable.snapTo(targetValue = 0f)
                        }
                    }

                    smallCircleAnimationJob.invokeOnCompletion {
                        animationScope.launch {
                            smallCircleAnimatable.snapTo(targetValue = 0f)
                        }
                    }

                    circleRadiusAnimationJob.invokeOnCompletion {
                        animationScope.launch {
                            circleRadiusAnimatable.snapTo(targetValue = 0f)
                        }
                    }
                }

            }
        )
    }
}