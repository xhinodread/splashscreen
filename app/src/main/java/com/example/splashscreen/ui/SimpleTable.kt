package com.example.splashscreen.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.material.DataTable
import androidx.ui.tooling.preview.Preview



@Preview
@Composable
fun PreviewSimpleTable(){
    SimpleTable(menuItems = DataProvider.coffeeMenu)
}

@Composable
fun SimpleTable(menuItems: List<MenuItem>) {
    DataTable(columns = 2) {
        headerRow { index ->
            when (index) {
                0 -> {
                    Text(text = "Name")
                }
                1 -> Text(text = "Price")
            }
        }

        for (menuItem in menuItems) {
            dataRow { index ->
                when (index) {
                    0 -> Text(menuItem.name)
                    1 -> Text("$ ${menuItem.unitPrice}")
                }
            }
        }
    }
}