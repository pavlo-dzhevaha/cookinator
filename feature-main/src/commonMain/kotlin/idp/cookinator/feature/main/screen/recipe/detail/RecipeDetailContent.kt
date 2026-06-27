package idp.cookinator.feature.main.screen.recipe.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.recipe_detail_stub
import cookinator.localisation.generated.resources.recipe_detail_title
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.navigation.extension.Navigator
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RecipeDetailContent(
    recipeId: Int,
    bottomBarHeight: Dp,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            bottom = bottomBarHeight + Theme.size.s16,
        ),
        modifier = modifier.fillMaxSize(),
    ) {
        stickyHeader {
            AppTopBar(
                title = stringResource(Res.string.recipe_detail_title),
                leadingIcon = Icons.ArrowLeft,
                onLeadingAction = onBack,
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Theme.size.s16),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(Res.string.recipe_detail_stub, recipeId),
                    style = Theme.typography.regular.p,
                )
            }
        }
    }
}
