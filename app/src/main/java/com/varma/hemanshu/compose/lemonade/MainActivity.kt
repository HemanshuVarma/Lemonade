package com.varma.hemanshu.compose.lemonade

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varma.hemanshu.compose.lemonade.model.LemonadeResources
import com.varma.hemanshu.compose.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Counter variable for holding current STEP of making lemonade.
        var step by remember { mutableStateOf(1) }

        // No. of taps required to squeeze a lemon
        val tapsRequired = (2..4).random()
        var tapCount = 1
        val TAG = "Lemonade Counter"

        val res: LemonadeResources = when (step) {
            1 -> LemonadeResources(
                textResource = R.string.lemon_tree,
                imageResource = R.drawable.lemon_tree,
                imageDescResource = R.string.lemon_tree_desc
            )
            2 -> LemonadeResources(
                textResource = R.string.lemon,
                imageResource = R.drawable.lemon_squeeze,
                imageDescResource = R.string.lemon_desc
            )
            3 -> LemonadeResources(
                textResource = R.string.drink_lemonade,
                imageResource = R.drawable.lemon_drink,
                imageDescResource = R.string.lemonade_glass_desc
            )
            else -> LemonadeResources(
                textResource = R.string.empty_glass,
                imageResource = R.drawable.lemon_restart,
                imageDescResource = R.string.empty_glass_desc
            )
        }

        Text(
            text = stringResource(id = res.textResource), fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Image(modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 2.dp, color = Color(105, 205, 216, 255)
                ), shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                Log.d(TAG, "STEP: $step")
                step = when (step) {
                    // Incrementing count of STEP until 3. Resetting to 1 in other case
                    1, 3 -> step + 1
                    2 -> {
                        Log.d(TAG, "TAPS REQUIRED: $tapsRequired")
                        Log.d(TAG, "COUNT: $tapCount")
                        if (tapCount <= tapsRequired) {
                            tapCount++
                            step
                        } else {
                            step + 1
                        }
                    }
                    else -> {
                        tapCount = 0
                        1
                    }
                }
            }
            .padding(all = 16.dp),
            painter = painterResource(id = res.imageResource),
            contentDescription = stringResource(
                id = res.imageDescResource
            ))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

@Preview(
    showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreviewDarkTheme() {
    LemonadeTheme {
        Surface {
            LemonadeApp()
        }
    }
}