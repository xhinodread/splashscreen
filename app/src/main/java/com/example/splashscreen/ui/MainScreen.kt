package com.example.splashscreen.ui

import androidx.compose.Model
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.ui.material.DataTable
import com.example.splashscreen.R

@Model
data class MenuItem(val name: String, val unitPrice: Int)
@Model
data class Person(val name: String, val age: Int, val hasDrivingLicence:Boolean, val email: String)

object DataProvider {

    val coffeeMenu = listOf(
        MenuItem("Espresso", unitPrice = 3),
        MenuItem("Double Espresso", unitPrice = 4),
        MenuItem("Americano", unitPrice = 4),
        MenuItem("Macchiato", unitPrice = 5),
        MenuItem("Caramel Macchiato", unitPrice = 5),
        MenuItem("Ristretto", unitPrice = 3),
        MenuItem("Latte", unitPrice = 5),
        MenuItem("Cappuchino", unitPrice = 5),
        MenuItem("Mocha", unitPrice = 5)
    )

}

@Composable
fun MainScreen(){
    Column(
        modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        HeadScreen()
        // SimpleTableM(menuItems = DataProvider.coffeeMenu)
        //Demo_Table()
        MapaBase()
    }
}

@Composable
fun HeadScreen(){
    Text("Pantalla principal")
    Text(
        "Bienveni@s",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}



@androidx.compose.Composable
fun SimpleTableM(menuItems: List<MenuItem>) {
    DataTable(columns = 2) {
        headerRow { index ->
            when (index) {
                0 -> {
                    androidx.ui.core.Text(text = "Name")
                }
                1 -> androidx.ui.core.Text(text = "Price")
            }
        }

        for (menuItem in menuItems) {
            dataRow { index ->
                when (index) {
                    0 -> androidx.ui.core.Text(menuItem.name)
                    1 -> androidx.ui.core.Text("$ ${menuItem.unitPrice}")
                }
            }
        }
    }
}