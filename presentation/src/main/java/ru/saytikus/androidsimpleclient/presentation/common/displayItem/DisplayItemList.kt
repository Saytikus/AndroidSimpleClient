package ru.saytikus.androidsimpleclient.presentation.common.displayItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun <Item> DisplayItemList(

    items: List<Item>,

    colors: AppColors,

    itemContent: @Composable (Item) -> Unit,

) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items.forEachIndexed { index, item ->

            itemContent(item)

            if (index < items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(start = 74.dp),
                    thickness = 1.dp,
                    color = colors.textPrimary.copy(alpha = 0.05f)
                )
            }
        }
    }
}