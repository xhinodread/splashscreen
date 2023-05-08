package com.example.splashscreen.ui

import android.widget.Toast
import android.util.Log
import androidx.compose.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splashscreen.R


/**
 * The horizontally scrollable table with header and content.
 * @param columnCount the count of columns in the table
 * @param cellWidth the width of column, can be configured based on index of the column.
 * @param data the data to populate table.
 * @param modifier the modifier to apply to this layout node.
 * @param headerCellContent a block which describes the header cell content.
 * @param cellContent a block which describes the cell content.
 */
@Composable
fun <T> Table(
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: List<T>,
    modifier: Modifier = Modifier,
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        LazyRow(
            modifier = Modifier.padding(16.dp)
        ) {
            items((0 until columnCount).toList()) { columnIndex ->
                Column {
                    (0..data.size).forEach { index ->
                        Surface(
                            border = BorderStroke(1.dp, Color.LightGray),
                            contentColor = Color.Transparent,
                            modifier = Modifier.width(cellWidth(columnIndex))
                        ) {
                            if (index == 0) {
                                headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[index - 1])
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Demo_Table() {
    val people = listOf(
        Person("Alex", 21, false, "alex@demo-email.com"),
        Person("Adam X", 35, true, "adam@demo-email.com"),
        Person("Iris", 26, false, "iris@demo-email.com"),
        Person("Marie", 32, false, "maria@demo-email.com"),
        Person("Iris", 26, false, "iris@demo-email.com"),
        Person("Marii", 32, false, "maria@demo-email.com"),
        Person("Iris Y", 26, false, "iris@demo-email.com"),
        Person("Maria", 32, false, "maria@demo-email.com"),
        Person("Iris X", 29, false, "iris@demo-email.com"),
        Person("Marie", 32, false, "maria@demo-email.com"),
        Person("Adam", 35, true, "adam@demo-email.com"),
        Person("Mario", 32, false, "maria@demo-email.com"),
    )

    val cellWidth: (Int) -> Dp = { index ->
        when (index) {
            2 -> 150.dp
            3 -> 250.dp
            else -> 100.dp
        }
    }
    val headerCellTitle: @Composable (Int) -> Unit = { index ->
        val value = when (index) {
            0 -> "Name"
            1 -> "Age"
            2 -> "License"
            3 -> "Email"
            4 -> "Ver"
            else -> "..."
        }

        Text(
            text = value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Black,
            textDecoration = TextDecoration.Underline
        )
    }
    val cellText: @Composable (Int, Person) -> Unit = { index, item ->
        val value = when (index) {
            0 -> item.name
            1 -> item.age.toString()
            2 -> if (item.hasDrivingLicence) "YES" else "NO"
            3 -> item.email
            4 -> "ImagenVer()"
            else -> "..."
        }

        val context = LocalContext.current
        //CajaDeTexto(context, value)
        if(index == 4){
            BotonVer(context, item.email)
            //ImagenVer(context, item.email)
        }else {
            CajaDeTextoClickable(context, value)
        }
    }

    Table(
        columnCount = 5,
        cellWidth = cellWidth,
        data = people,
        modifier = Modifier.verticalScroll(rememberScrollState()),
        headerCellContent = headerCellTitle,
        cellContent = cellText
    )
}

@Composable
fun CajaDeTexto(context:Context ,value:String){
    Text(
        text = value,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .clickable() {
                unToad(context, value)
            },
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun CajaDeTextoClickable(context:Context , value:String){
    ClickableText(
        text = AnnotatedString(value),
        onClick = { offset ->
            //Log.d("ClickableText", "$offset -th character is clicked.")
            //Log.d("ClickableText", value)
            unToad(context, value)
        },
        modifier = Modifier.padding(16.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun ImagenVer(context:Context, valor:String){
    Image(
        painter = painterResource(id = R.drawable.logochileregion),
        contentDescription = "Logo Chileregión",
        Modifier
            .size(50.dp, 50.dp)
            .alpha(alpha = 1f)
            .clickable { unToad(context, valor) }
    )
}

@Composable
fun BotonVer(context:Context, texto:String){
    Button(
        onClick = { unToad(context, texto) }
    ){
        Text("Ver")
    }
    Icon(Icons.Default.Add ,contentDescription = "content description", tint=Color(0XFF0F9D58))

}


fun unToad(context:Context, mensaje:String){
    Toast.makeText(context, "Hola $mensaje", Toast.LENGTH_SHORT).show()
}