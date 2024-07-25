import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jobkorea.app.R
import com.jobkorea.app.ui.screens.main.defaultPadding
import com.jobkorea.app.ui.screens.main.maxHeightTopbar
import com.jobkorea.app.ui.screens.main.minHeightTopBar

@Composable
fun DynamicTopBar(isTopbarCollapsed: Int) {

    var minWidthSearchInputBox by remember { mutableFloatStateOf(Float.MAX_VALUE) }
    var maxWidthSearchInputBox by remember { mutableFloatStateOf(Float.MAX_VALUE) }

    val dynamicTopbarHeight by animateFloatAsState(
        targetValue = (maxHeightTopbar - isTopbarCollapsed * minHeightTopBar).coerceIn(
            minHeightTopBar,
            maxHeightTopbar
        ).toFloat(),
        label = "상단바 가변 높이"
    )
    val dynamicTopPadding by animateFloatAsState(
        targetValue = (minHeightTopBar - isTopbarCollapsed * minHeightTopBar).coerceIn(
            defaultPadding / 2,
            minHeightTopBar
        ).toFloat(),
        label = "검색 박스 동적 위치"
    )
    val dynamicInputBoxWidth by animateFloatAsState(
        targetValue = (maxWidthSearchInputBox - isTopbarCollapsed * minWidthSearchInputBox).coerceIn(
            minWidthSearchInputBox,
            maxWidthSearchInputBox
        ),
        label = "검색 박스 동적 넓이"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green)
            .height(Dp(dynamicTopbarHeight))
            .onSizeChanged { newSize ->
                maxWidthSearchInputBox = newSize.width.toFloat()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_default_character),
            contentDescription = "Main Logo",
            modifier = Modifier.size(Dp(dynamicTopbarHeight))
        )
        SearchInputBox(dynamicTopPadding, dynamicInputBoxWidth)
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(minHeightTopBar.dp)
            ) {
                Spacer(modifier = Modifier
                    .weight(1f)
                    .onSizeChanged { newSize ->
                        minWidthSearchInputBox = newSize.width.toFloat()
                    })
                Spacer(modifier = Modifier.size(20.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_alarm),
                    contentDescription = "Notification",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun SearchInputBox(dynamicTopPadding: Float, dynamicWidthPixels: Float) {
    val dynamicWidth = with(LocalDensity.current) { dynamicWidthPixels.toDp() }

    Surface(
        modifier = Modifier
            .padding(
                top = animateDpAsState(
                    Dp(dynamicTopPadding),
                    label = "SearchInputBox Top Padding"
                ).value,
                start = defaultPadding.dp,
                end = defaultPadding.dp,
                bottom = defaultPadding.dp / 2
            )
            .width(animateDpAsState(dynamicWidth, label = "SearchInputBox Width").value),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Blue)
    ) {
        Box(modifier = Modifier.fillMaxHeight())
    }
}
