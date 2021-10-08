import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.ui.theme.JetPackTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Movie(val id: Int, val name: String, val genre: String, var year: Int)
@Composable
fun SwipeRefreshLayout() {
    val movieList = listOf(
        Movie(id = 1, name = "GOLD", year = 2018, genre = "Sports"),
        Movie(id = 2, name = "URI", year = 2019, genre = "Patriotic"),
        Movie(id = 3, name = "DHOOM", year = 2004, genre = "Action"),
        Movie(id = 4, name = "LOC", year = 2003, genre = "Action,War"),
        Movie(id = 5, name = "BORDER", year = 1997, genre = "Action,War"),
        Movie(id = 6, name = "ZNMG", year = 2011, genre = "Adventure"),
        Movie(id = 7, name = "RUSTOM", year = 2016, genre = "Mystery"),
        Movie(id = 8, name = "MIRROR", year = 2008, genre = "Horror"),
        Movie(id = 9, name = "SHOLAY", year = 1975, genre = "Action,Drama,Comedy"),
        Movie(id = 10, name = "LAGAAN", year = 2001, genre = "Adventure,Sports"),
        Movie(id = 11, name = "TITANIC", year = 1997, genre = "Disaster")
    )
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    JetPackTheme(
        darkTheme = true,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        isRefreshing = true
                    },
                ) {
                    LazyColumn(content = {
                        items(
                            items = movieList,
                            itemContent = {
                                MovieListItemView(it)
                            })
                    })
                }

            },
            topBar = {
                TopAppBar(title = { Text("Swipe To Refresh") }
                )
            },
        )
        LaunchedEffect(isRefreshing) {
            if (isRefreshing) {
                delay(1000L)
                isRefreshing= false
            }
        }
    }
}

@Composable
fun MovieListItemView(it: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp), content = {
            val color =
                Color(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                )
            Box(
                content = {
                    Text(
                        text = it.name.get(0).uppercase(),
                        fontSize = 24.sp,
                        color = color
                    )
                }, modifier = Modifier
                    .size(80.dp)
                    .border(width = 1.2.dp, color = color, shape = CircleShape),
                contentAlignment = Alignment.Center
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier.weight(2F),
                content = {
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = it.name.uppercase(),
                        color = color,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${it.year}",
                        color = Color.White,
                        fontSize = 14.6.sp
                    )
                    Text(
                        text = it.genre,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                })
        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SwipeRefreshLayout()
}