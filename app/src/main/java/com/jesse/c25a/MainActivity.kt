package com.jesse.c25a

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jesse.c25a.Income.IncomeScreen
import com.jesse.c25a.burger.presentation.InitialScreen
import com.jesse.c25a.compTwoCom.CompMultiSlotScreen
import com.jesse.c25a.compTwoCom.ComposableAsArgumentScreen
import com.jesse.c25a.compTwoCom.ComposableWrapperScreen
import com.jesse.c25a.compTwoCom.MyText
import com.jesse.c25a.datastore.DataStoreScreen
import com.jesse.c25a.filter.presentation.FilterScreen
import com.jesse.c25a.flows.FlowsScreen
import com.jesse.c25a.hg.parallax.ParallaxEffectScreen
import com.jesse.c25a.lazys.LazysScreen
import com.jesse.c25a.mlkit.mlkScanner.MlkScanScreen
import com.jesse.c25a.paging3tutorial.presentation.Paging3Screen
import com.jesse.c25a.perritos.PerritosScreen
import com.jesse.c25a.qualifier.presentation.QualifierScreen
import com.jesse.c25a.quick.QuickScreen
import com.jesse.c25a.starRating.StarRatingScreen
import com.jesse.c25a.table.TableScreen
import com.jesse.c25a.twocomposable.TwoComposableScreen
import com.jesse.c25a.ui.theme.C25aTheme
import com.jesse.c25a.youtube.YouTubeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            C25aTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MyScreens.BaseScreen.name
                ) {
                    composable(MyScreens.BaseScreen.name) {
                        BaseScreen() {
                            Log.d("TAJ", "onCreate: $it ")
                            navController.navigate(it)
                        }
                    }
                    composable(MyScreens.Burger.name) { InitialScreen() }
                    composable(MyScreens.Perritos.name) { PerritosScreen() }
                    composable(MyScreens.Filter.name) { FilterScreen() }
                    composable(MyScreens.DataStore.name) { DataStoreScreen() }
                    composable(MyScreens.Flows.name) { FlowsScreen() }
                    composable(MyScreens.Qualifier.name) { QualifierScreen() }
                    composable(MyScreens.Paging3.name) { Paging3Screen() }
                    composable(MyScreens.Parallax.name) { ParallaxEffectScreen() }
                    composable(MyScreens.TwoCom1.name) { TwoComposableScreen() }
                    composable(MyScreens.CmpAsArg.name) { ComposableAsArgumentScreen() }
                    composable(MyScreens.CmpMultiSlot.name) { CompMultiSlotScreen() }
                    composable(MyScreens.CWrap.name) { ComposableWrapperScreen() }
                    composable(MyScreens.Quick.name) { QuickScreen() }
                    composable(MyScreens.Income.name) { IncomeScreen() }
                    composable(MyScreens.Table.name) { TableScreen() }
                    composable(MyScreens.MlKitScanner.name) { MlkScanScreen(activity = this@MainActivity) }
                    composable(MyScreens.YouTube.name) { YouTubeScreen() }
                    composable(MyScreens.StarRating.name) { StarRatingScreen() }
                    composable(MyScreens.Lazys.name) { LazysScreen() }
                }
            }
        }
    }

    enum class MyCatScreen(val color: Int) {
        HG(color = R.color.teal_200), PL(color = R.color.pl), ARIS(color = R.color.orange), YOP(color = R.color.marino),
        AG(color = R.color.white)
    }

    enum class MyScreens(val cat: MyCatScreen, val size: Int = 16) {
        BaseScreen(MyCatScreen.YOP), Burger(MyCatScreen.PL), Perritos(MyCatScreen.ARIS),
        Filter(MyCatScreen.YOP), DataStore(MyCatScreen.HG), Flows(MyCatScreen.ARIS),
        Qualifier(MyCatScreen.YOP), Paging3(MyCatScreen.ARIS), Parallax(MyCatScreen.HG),
        TwoCom1(MyCatScreen.YOP), CmpAsArg(MyCatScreen.YOP), CmpMultiSlot(MyCatScreen.YOP,13),
        CWrap(MyCatScreen.YOP), Quick(MyCatScreen.YOP), Income(MyCatScreen.YOP), Table(MyCatScreen.YOP),
        MlKitScanner(MyCatScreen.YOP),YouTube(MyCatScreen.AG),StarRating(MyCatScreen.AG),Lazys(MyCatScreen.AG),
    }

    @Composable
    fun BaseScreen(onclick: (String) -> Unit) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var buttons = ""
            MyScreens.entries.forEach {
                if (it != MyScreens.BaseScreen)
                    buttons += "${it.name} "
            }
            val buttonsChuncked = buttons.split(" ").chunked(3)
            buttonsChuncked.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    it.forEach {
                        if (it != "")
                            Button( modifier = Modifier.weight(1f), onClick = { onclick(it) }, contentPadding = ButtonDefaults.TextButtonContentPadding) {
                                val buttonData: MyScreens = getButtonInfo(it)
                                    MyText(text = it, color = buttonData.cat.color, textSizeDp = buttonData.size.dp)
                            }
                    }
                }
            }
        }
    }

    private fun getButtonInfo(title: String) = MyScreens.entries.find { it.name == title }!!

}
