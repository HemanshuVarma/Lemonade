package com.varma.hemanshu.compose.lemonade

import android.content.res.Configuration
import android.os.Bundle
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
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Counter variable for holding current STEP of making lemonade.
        var step by remember { mutableStateOf(1) }
        val textResource = when (step) {
            1 -> R.string.lemon_tree
            2 -> R.string.lemon
            3 -> R.string.drink_lemonade
            else -> R.string.empty_glass
        }
        val imageResource = when (step) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }
        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Image(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(105, 205, 216, 255)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    step = when (step) {
                        // Incrementing count of STEP until 3. Resetting to 1 in other case
                        1, 2, 3 -> step + 1
                        else -> 1
                    }
                }
                .padding(all = 16.dp),
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(
                id = R.string.lemon_tree_desc
            )
        )
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
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreviewDarkTheme() {
    LemonadeTheme {
        Surface {
            LemonadeApp()
        }
    }
}