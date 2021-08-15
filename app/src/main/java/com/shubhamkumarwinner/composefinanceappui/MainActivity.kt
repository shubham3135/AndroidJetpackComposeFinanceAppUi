package com.shubhamkumarwinner.composefinanceappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.shubhamkumarwinner.composefinanceappui.ui.theme.*

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFinanceAppUiTheme {
                Surface(color = BackgroundColor) {
                    Scaffold(
                        backgroundColor = BackgroundColor,
                        bottomBar = { BottomNavigationBar()}
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 10.dp),
                            horizontalAlignment = Alignment
                                .CenterHorizontally
                        ) {
                            Header()
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier.height(200.dp)
                            ){
                                Carousel()
                            }
                            OptionMenu()
                            Spacer(modifier = Modifier.height(10.dp))
                            ItemHeader()

                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(bottom = 50.dp)
                            ){
                                LazyColumn{
                                    items(5){
                                        Item()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.pic),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp, start = 10.dp)
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Hello", color = SubtitleColor)
                Text(
                    text = "Shubham Kumar",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Carousel() {
    Column(modifier = Modifier.fillMaxWidth()) {
        val pagerState = rememberPagerState(
            pageCount = 3,
            initialOffscreenLimit = 2
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) { page ->
            // Our page content
            CardItem(page)
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )

    }
}

@Composable
fun CardItem(id: Int) {
    val img = when(id){
        0 -> R.drawable.master_card
        1 -> R.drawable.visa
        2 -> R.drawable.paytm
        else -> R.drawable.ic_launcher_foreground
    }

    val amount = when(id){
        0 -> "₹1,320.00"
        1 -> "₹560.00"
        2 -> "₹1,450.00"
        else -> "₹1,320.00"
    }

    val cardNo = when(id){
        0 -> "5282 3456 7890 1289"
        1 -> "4591 1500 2725 8284"
        2 -> "5256 2204 0543 3719"
        else -> "5282 3456 7890 1289"
    }

    val expDt = when(id){
        0 -> "9/25"
        1 -> "5/25"
        2 -> "2/25"
        else -> "9/25"
    }
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .height(150.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        backgroundColor = CardColor,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clickable { }
                .clip(RoundedCornerShape(30.dp))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Total Balance",
                        color = CardSubtitleColor
                    )
                    Text(
                        text = amount,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                }
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "",
                    modifier = Modifier
                        .height(40.dp)
                        .width(64.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = cardNo, color = CardSubtitleColor)
                Text(text = expDt, color = CardSubtitleColor)
            }
        }

    }
}

@Composable
fun OptionMenu() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        OptionItem("Transfer", R.drawable.ic_arrow_up)
        OptionItem("Request", R.drawable.ic_arrow_down)
        OptionItem("Pay Bill", R.drawable.ic_description)
        OptionItem("Top Up", R.drawable.ic_add_box)
    }
}

@Composable
fun OptionItem(title: String, id: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(
                RoundedCornerShape(15.dp),
            )
            .clickable { }
            .padding(10.dp),
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color = ItemBackgroundColor)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = id),
                contentDescription = "",
                tint = CardColor
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = title)
    }
}

@Composable
fun ItemHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 10.dp)
    ) {
        Text(
            text = "Transactions",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
        )
        Row {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    modifier = Modifier.padding(10.dp),
                    tint = SubtitleColor
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "",
                    modifier = Modifier.padding(10.dp),
                    tint = SubtitleColor
                )
            }
        }
    }
}

@Composable
fun Item() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(65.dp)
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 20.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = ItemBackgroundColor)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_description),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Playstation Plus",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
                Text(
                    text = "19 August, 2021",
                    fontWeight = FontWeight.Bold,
                    color = SubtitleColor,
                    fontSize = 14.sp
                )
            }
        }
        Text(
            text = "- ₹56.90",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Composable
fun BottomNavigationBar(initialSelectedItemIndex: Int = 0) {
    val items = listOf(
        NavigationItem.Main,
        NavigationItem.Cards,
        NavigationItem.Support,
        NavigationItem.More
    )
    BottomNavigation(
        backgroundColor = ItemBackgroundColor,
        contentColor = Color.White
    ) {
        var selectedItemIndex by remember {
            mutableStateOf(initialSelectedItemIndex)
        }
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(
                    painterResource(id = item.icon),
                    contentDescription = item.title
                ) },
                label = { Text(text = item.title) },
                selectedContentColor = CardColor,
                unselectedContentColor = SubtitleColor,
                alwaysShowLabel = true,
                selected = index == selectedItemIndex,
                onClick = {
                    selectedItemIndex = index
                }
            )
        }
    }
}
