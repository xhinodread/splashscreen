package com.example.splashscreen.ui

import androidx.compose.animation.core.*
import android.media.Image
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splashscreen.R
import com.example.splashscreen.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
     var startAnimator by remember { mutableStateOf(false) }
     val alphaAnim = animateFloatAsState(
         targetValue = if(startAnimator) 0f else 1f,
         animationSpec = tween(
             durationMillis = 3500
         )
     )

    LaunchedEffect(key1 = true){
        startAnimator = true
        delay(3000)
        navController.popBackStack()
        navController.navigate(AppScreens.MainScreen.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float){
    Column(
        modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
           painter = painterResource(id = R.drawable.logochileregion),
           contentDescription = "Logo Chileregión",
           Modifier.size(150.dp, 150.dp).alpha(alpha=alpha)
        )
        /**
        Text(
            "Bienveni@s",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        **/
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
     Splash(alpha = 1f)
}