package com.example.instagramui.ui.theme

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramui.R
import com.example.instagramui.ImageWithText

@Composable
fun ProfileScreen() {

    var selectedTabIndex = remember {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()
        Spacer(modifier = Modifier.height(5.dp))
        ProfileSection()
        ProfileDescription(
            displayName = "Android Programming",
            description = "Experienced Android Developer \uD83D\uDCF1 specializing in Kotlin \uD83D\uDEE0\uFE0F, and personalized app development sessions \uD83C\uDF93.",
            url = "https://github.com/keyurmistry316",
            followedBy = listOf("coding_guru", "android_dev"),
            otherCount = 14

        )
        ButtonSection()
        HighlightsSection(
            listOf(
                ImageWithText(R.drawable.youtube, "YouTube"),
                ImageWithText(R.drawable.qa, "Q&A"),
                ImageWithText(R.drawable.discord, "Discord"),
                ImageWithText(R.drawable.telegram, "Telegram"),
                ImageWithText(R.drawable.youtube, "YouTube"),
                ImageWithText(R.drawable.qa, "Q&A"),
                ImageWithText(R.drawable.discord, "Discord"),
                ImageWithText(R.drawable.telegram, "Telegram")
            )
        )
        PostTabView(
            modifier = Modifier, imageWithTextList = listOf(
                ImageWithText(image = R.drawable.ic_grid, "Posts"),
                ImageWithText(image = R.drawable.ic_reels, "Reels"),
                ImageWithText(image = R.drawable.ic_igtv, "IGTV"),
                ImageWithText(image = R.drawable.profile, "Profile"),
            )
        ) {
            selectedTabIndex.value = it
        }
        when (selectedTabIndex.value) {
            0 -> PostSection(
                post = listOf(
                    R.drawable.kmm,
                    R.drawable.intermediate_dev,
                    R.drawable.master_logical_thinking,
                    R.drawable.bad_habits,
                    R.drawable.multiple_languages,
                    R.drawable.learn_coding_fast,
                )
            )
            else->{}
        }

    }

}

@Composable
fun PostTabView(
    modifier: Modifier,
    imageWithTextList: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex = remember {
        mutableIntStateOf(0)
    }
    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {

        imageWithTextList.forEachIndexed { index, imageWithText ->
            Tab(
                selected = selectedTabIndex.value == index,
                onClick = {
                    selectedTabIndex.value = index
                    onTabSelected(index)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = InActiveColor
            ) {
                Icon(
                    painter = painterResource(id = imageWithText.image),
                    contentDescription = imageWithText.text,
                    tint = if (selectedTabIndex.value == index) Color.Black else InActiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }

        }

    }

}

@Composable
fun PostSection(
    post: List<Int>
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.scale(1.01f)) {
        items(post.size) {
            Image(
                painter = painterResource(id = post[it]),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(width = 1.dp, color = Color.White)
            )
        }

    }
}

@Composable
fun HighlightsSection(storyList: List<ImageWithText>) {

    LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        items(storyList.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
            ) {
                RoundImage(
                    image = storyList[it].image,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(70.dp)
                )
                Text(
                    text = storyList[it].text,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

@Composable
fun ButtonSection() {
    val minWidth = 95.dp
    val defaultHeight = 30.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            Modifier
                .width(minWidth)
                .height(defaultHeight),
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown
        )
        ActionButton(
            Modifier
                .width(minWidth)
                .height(defaultHeight),
            text = "Message",
            icon = null
        )
        ActionButton(
            Modifier
                .width(minWidth)
                .height(defaultHeight),
            text = "Email",
            icon = null
        )
        ActionButton(
            Modifier
                .width(defaultHeight)
                .height(defaultHeight),
            text = null,
            icon = Icons.Default.KeyboardArrowDown
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier,
    text: String? = null,
    icon: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(6.dp)
    ) {
        if (text != null) {
            Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = "", tint = Color.Black)
        }

    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int
) {

    val letterSpace = 0.5.sp
    val lineHeight = 20.sp
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            lineHeight = lineHeight,
            letterSpacing = letterSpace,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Text(
            text = description,
            fontWeight = FontWeight.Bold,
            lineHeight = lineHeight,
            letterSpacing = letterSpace
        )
        Text(
            text = url,
            fontWeight = FontWeight.Bold,
            lineHeight = lineHeight,
            letterSpacing = letterSpace,
            color = UrlColor,
            modifier = Modifier.clickable {

                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
                context.startActivity(urlIntent)
            }
        )
        if (followedBy.isNotEmpty()) {
            Text(text = buildAnnotatedString {

                val boldStyle = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)

                append("Followed by ")
                followedBy.forEachIndexed { index, string ->
                    pushStyle(boldStyle)
                    append(string)
                    pop()
                    if (index < followedBy.size - 1) {
                        append(", ")
                    }
                }
                if (otherCount > 2) {
                    append(" and ")
                    pushStyle(boldStyle)
                    append("$otherCount others.")
                }

            })
        }
    }

}

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundImage(
                R.drawable.profile_avatar, "profile_image",
                Modifier
                    .size(100.dp)
                    .weight(3f)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = "601",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Posts",
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = "99.6K",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Followers",
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = "73",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Following",
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }
        }

    }
}


@Composable
private fun RoundImage(image: Int, description: String = "", modifier: Modifier) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                1.dp, Color.LightGray,
                CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back",
            Modifier.size(24.dp),
            tint = Color.Black
        )
        Text(
            text = "keyur_mistry",
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "",
            modifier = Modifier.size(25.dp),
            tint = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            contentDescription = "",
            modifier = Modifier.size(20.dp),
            tint = Color.Black
        )
    }
}
