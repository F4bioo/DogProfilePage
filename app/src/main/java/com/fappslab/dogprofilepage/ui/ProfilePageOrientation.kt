package com.fappslab.dogprofilepage.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.fappslab.dogprofilepage.R

@Composable
fun ProfilePageOrientation() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (cardContainer) = createRefs()
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 0.dp, modifier = Modifier
                .padding(16.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp))
                .constrainAs(cardContainer) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.matchParent
                }
        ) {
            BoxWithConstraints {
                ConstraintLayout(portraitConstraints()) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.husky),
                        contentDescription = "husky",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(16.dp)
                            .clip(CircleShape)
                            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                            .layoutId(layoutId = "imageProfile")
                    )

                    Text(
                        text = "Siberian Husky",
                        modifier = Modifier.layoutId(layoutId = "textName")
                    )
                    Text(
                        text = "Germany",
                        modifier = Modifier
                            .layoutId(layoutId = "textCountry")
                            .padding(bottom = 16.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .layoutId(layoutId = "rowStats")
                    ) {
                        ProfileStatsOrientation(count = "150", title = "Followers")
                        ProfileStatsOrientation(count = "86", title = "Following")
                        ProfileStatsOrientation(count = "27", title = "Posts")
                    }

                    Button(
                        onClick = { println("<L> Profile") },
                        modifier = Modifier
                            .padding(16.dp)
                            .layoutId(layoutId = "buttonProfile")
                    ) {
                        Text(text = "Profile")
                    }
                }
            }
        }
    }
}

private fun portraitConstraints(): ConstraintSet {
    return ConstraintSet {
        val imageProfile = createRefFor(id = "imageProfile")
        val textName = createRefFor(id = "textName")
        val textCountry = createRefFor(id = "textCountry")
        val rowStats = createRefFor(id = "rowStats")
        val buttonProfile = createRefFor(id = "buttonProfile")
        constrain(imageProfile) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(textName) {
            top.linkTo(imageProfile.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(textCountry) {
            top.linkTo(textName.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats) {
            top.linkTo(textCountry.bottom)
        }
        constrain(buttonProfile) {
            top.linkTo(rowStats.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}

private fun landscapeConstraints(): ConstraintSet {
    return ConstraintSet {
        val imageProfile = createRefFor(id = "imageProfile")
        val textName = createRefFor(id = "textName")
        val textCountry = createRefFor(id = "textCountry")
        val rowStats = createRefFor(id = "rowStats")
        val buttonProfile = createRefFor(id = "buttonProfile")
        constrain(imageProfile) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        constrain(textName) {
            top.linkTo(imageProfile.bottom)
            start.linkTo(imageProfile.start)
            end.linkTo(imageProfile.end)
        }
        constrain(textCountry) {
            top.linkTo(textName.bottom)
            start.linkTo(imageProfile.start)
            end.linkTo(imageProfile.end)
        }
        constrain(rowStats) {
            top.linkTo(imageProfile.top)
            bottom.linkTo(imageProfile.bottom)
            start.linkTo(imageProfile.end)
            end.linkTo(parent.end)
        }
        constrain(buttonProfile) {
            top.linkTo(rowStats.bottom)
            start.linkTo(rowStats.start)
            end.linkTo(parent.end)
        }
    }
}

@Composable
fun ProfileStatsOrientation(count: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count, fontWeight = FontWeight.Bold
        )
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreviewOrientation() {
    ProfilePageOrientation()
}
