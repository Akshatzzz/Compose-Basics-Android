package com.example.compose_lesson_1

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_lesson_1.ui.theme.Compose_Lesson_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_Lesson_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list = buildList<Item> {
                        repeat(30) {
                            when(it.mod(3)) {
                                0 -> {
                                    add(Item(
                                        title = "Akshat",
                                        content = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nisi quam architecto iste dignissimos asperiores nulla deserunt odit laborum nesciunt reprehenderit temporibus impedit officiis dolore ea cumque saepe in, quibusdam placeat!",
                                        imageId = R.drawable.image_drawable
                                    )  )
                                }
                                1 -> {
                                    add(Item(
                                        title = "Akshat",
                                        content = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nisi quam architecto",
                                        imageId = R.drawable.image_drawable
                                    ))
                                }
                                2 -> {
                                    add(Item(
                                        title = "Akshat",
                                        content = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nisi quam architecto iste dignissimos asperiores nulla deserunt odit laborum nesciunt",
                                        imageId = R.drawable.image_drawable
                                    ))
                                }
                            }
                        }
                    }
                    ListTexts(contents = list)
                }
            }
        }
    }
}

@Composable
fun TextComposable(
    modifier: Modifier = Modifier,
    content: Item
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = content.imageId),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                }
        ) {
            Text(
                text = content.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = MaterialTheme.shapes.large,
                shadowElevation = 1.dp
            ){
                Text(
                    text = content.content,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(4.dp),
                    maxLines = if(!isExpanded) 1 else Int.MAX_VALUE
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}

@Composable
fun ListTexts(
    modifier: Modifier = Modifier,
    contents: List<Item> = emptyList()
) {
    LazyColumn {
        items(contents) {item ->
            TextComposable(content = item)
        }
    }
}