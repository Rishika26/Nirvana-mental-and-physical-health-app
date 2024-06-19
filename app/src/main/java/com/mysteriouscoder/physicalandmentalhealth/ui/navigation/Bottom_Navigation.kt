package com.mysteriouscoder.physicalandmentalhealth.ui.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mysteriouscoder.physicalandmentalhealth.PreferenceManager
import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.auth.AuthActivity
import com.mysteriouscoder.physicalandmentalhealth.ui.navigation.customdialog.CustomDialogViewModel
import com.mysteriouscoder.physicalandmentalhealth.ui.navigation.customdialog.dialog.ConnectUsDialog
import com.mysteriouscoder.physicalandmentalhealth.ui.navigation.customdialog.dialog.ExitDialog
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.aifriend.AppContent
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.exercise.Exercise
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.faq.FAQ
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.MeditationAndSounds
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.binauralbeats.BinauralBeats
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.binauralbeats.musiccontroller.MusicPlayerViewModel
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.binauralbeats.musiccontroller.SongScreen
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.guidedmeditationsession.Meditation
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.guidedmeditationsession.musiccontroller.MusicPlayerViewModel3
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.guidedmeditationsession.musiccontroller.SongScreen3
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.soothingmusic.SoothingMusic
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.soothingmusic.musiccontroller.MusicPlayerViewModel2
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.soothingmusic.musiccontroller.SongScreen2
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.profile.Profile
import com.syedwaqarul.tipcalculator.Navigation.Navigationitems


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(viewModel: CustomDialogViewModel, auth: FirebaseAuth, db: FirebaseFirestore) {

    var isSheetOpen by rememberSaveable {
        mutableStateOf(true)
    }

    val context = LocalContext.current
    val prefs = remember(context) { PreferenceManager(context) }

    val sendEmailIntent = remember {
        mutableStateOf(Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:nirvanaofficialconnect@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        })
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        }

    val showConnectUsDialog = remember { mutableStateOf(false) }
    val showExitDialog = remember { mutableStateOf(false) }
    val navigationController = rememberNavController()
    val items = listOf(
        BottomNavigationItem(
            title = "Meditation",
            selectedIcon = painterResource(id = R.drawable.meditation_icon_selected),
            unselectedIcon = painterResource(id = R.drawable.meditation_icon_unselected),

            ),

        BottomNavigationItem(
            title = "Exercise",
            selectedIcon = painterResource(id = R.drawable.exercises_icon_selected),
            unselectedIcon = painterResource(id = R.drawable.exercises_icon_unselected),

            ),


        BottomNavigationItem(
            title = "AI friend",
            selectedIcon = painterResource(id = R.drawable.ai_friend_icon_selected),
            unselectedIcon = painterResource(id = R.drawable.ai_friend_icon_unselected),

            ),
        BottomNavigationItem(
            title = "FAQ",
            selectedIcon = painterResource(id = R.drawable.faq_icon_selected),
            unselectedIcon = painterResource(id = R.drawable.faq_icon_unselected),


            ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = painterResource(id = R.drawable.profile_selected),
            unselectedIcon = painterResource(id = R.drawable.profile_unselected),

            )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "\tNirvana",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),

                        )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    var showMenu by remember { mutableStateOf(false) }
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Connect") },
                            onClick = {
                                CustomDialogViewModel.onClick()
                                if (CustomDialogViewModel.isDialogShown) {
                                    showConnectUsDialog.value = true
                                }
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Mail") },
                            onClick = {
                                launcher.launch(sendEmailIntent.value)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Rate") },
                            onClick = {
                                val appPackageName = context.packageName
//                                val appPackageName = "com.igg.android.doomsdaylastsurvivors"
                                try {
                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
                                } catch (e: android.content.ActivityNotFoundException) {
                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
                                }
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Share") },
                            onClick = {
                                val message="Let me recommend you this application\n\n"
                                val appPackageName = context.packageName
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, message +"https://play.google.com/store/apps/details?id=$appPackageName")
                                    type = "text/plain"
                                }

                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = {
                                auth.signOut()
                                navigateToAuthScreen(context)
                                prefs.clear()

                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Exit") },
                            onClick = {
                                CustomDialogViewModel.onClick()
                                if (CustomDialogViewModel.isDialogShown) {
                                    showExitDialog.value = true
                                }

                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (isSheetOpen) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.background,
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(selected = selectedItemIndex == index, onClick = {
                            selectedItemIndex = index
                            navigationController.navigate(item.title)

                        }, label = {
                            Text(
                                text = item.title,
                                fontFamily = FontFamily.Serif,
                                fontSize = if (selectedItemIndex == index) 10.sp else 8.sp,
                                color = if (selectedItemIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                                modifier = if (selectedItemIndex == index) Modifier.padding(top = 4.dp) else Modifier.padding(
                                    top = 0.dp
                                )
                            )
                        }, icon = {

                            if (index == selectedItemIndex) {

                                Icon(
                                    painter = item.selectedIcon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colorScheme.primary

                                )
                            } else {
                                Icon(
                                    painter = item.unselectedIcon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colorScheme.onSurface

                                )
                            }
                        })
                    }
                }
            }
        }) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Navigationitems.Meditation.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Navigationitems.Meditation.route) {
                isSheetOpen = true
                MeditationAndSounds(
                    navigationController
                )
            }
            composable(Navigationitems.FAQ.route) {
                isSheetOpen = true
                FAQ()

            }
            composable(Navigationitems.BinauralBeats.route) {
                isSheetOpen = true
                BinauralBeats(onCardSelect =  { idx ->
                    navigationController.navigate(
                        Navigationitems.MusicPlayerWithIndex.route.replace(
                            "{idx}", idx.toString()
                        )
                    ) {
                        popUpTo(Navigationitems.BinauralBeats.route) {
                            saveState = true
                            inclusive = true
                        }
                        popUpTo(Navigationitems.FAQ.route) {
                            saveState = true
                            inclusive = true

                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                    navigationController)
            }
            composable(Navigationitems.MeditationTypes.route) {
                isSheetOpen = true
                Meditation(onCardSelect ={ idx ->
                    navigationController.navigate(
                        Navigationitems.MusicPlayerWithIndex3.route.replace(
                            "{idx}",
                            idx.toString()
                        )
                    )
                },
                    navigationController
                )
            }
            composable(Navigationitems.Profile.route) {
                isSheetOpen = true
                Profile()
            }
            composable(Navigationitems.AI_friend.route) {
                isSheetOpen = true
                AppContent()
            }

            composable(Navigationitems.Exercise.route) {
                isSheetOpen = true

                Exercise()
            }
            composable(Navigationitems.SoothingMusic.route) {
                isSheetOpen = true
                SoothingMusic(onCardSelect =  { idx ->
                    navigationController.navigate(
                        Navigationitems.MusicPlayerWithIndex2.route.replace(
                            "{idx}",
                            idx.toString()
                        )
                    )
                },
                    navigationController)
            }
            composable(Navigationitems.MusicPlayerScreen.route) {

                isSheetOpen = false
                // Player screen
                val vm: MusicPlayerViewModel = viewModel(
                    factory = MusicPlayerViewModel.Factory(1)
                )
                val state = vm.playerState.collectAsState().value

                SongScreen(
                    state = state,
                    onBackPress = {
                        navigationController.popBackStack()
                    })
            }

            composable(Navigationitems.MusicPlayerScreen2.route) {

                isSheetOpen = false

                // Player screen
                val vm: MusicPlayerViewModel2 = viewModel(
                    factory = MusicPlayerViewModel2.Factory(1)
                )
                val state = vm.playerState.collectAsState().value

                SongScreen2(
                    state = state,
                    onBackPress = {
                        navigationController.popBackStack()
                    })
            }
            composable(Navigationitems.MusicPlayerScreen3.route) {

                isSheetOpen = false

//                 Player screen
                val vm: MusicPlayerViewModel3 = viewModel(
                    factory = MusicPlayerViewModel3.Factory(1)
                )
                val state = vm.playerState.collectAsState().value

                SongScreen3(
                    state = state,
                    onBackPress = {
                        navigationController.popBackStack()
                    })
            }

            composable(
                Navigationitems.MusicPlayerWithIndex.route,
                arguments = listOf(navArgument("idx") { type = NavType.IntType })
            ) {
                val idx = it.arguments?.getInt("idx") ?: 0
                isSheetOpen = true
                Log.d("MusicPlayerWithIndex", "MusicPlayerWithIndex")
                val vm: MusicPlayerViewModel = viewModel(
                    factory = MusicPlayerViewModel.Factory(idx)
                )
                val state = vm.playerState.collectAsState().value

                SongScreen(
                    state = state,
                    onBackPress = {
                        navigationController.popBackStack()
                    }
                )
                isSheetOpen = false


            }

            composable(
                Navigationitems.MusicPlayerWithIndex2.route,
                arguments = listOf(navArgument("idx") { type = NavType.IntType })
            ) {
                val idx = it.arguments?.getInt("idx") ?: 0
                isSheetOpen = true
                Log.d("MusicPlayerWithIndex", "MusicPlayerWithIndex")
                val vm: MusicPlayerViewModel2 = viewModel(
                    factory = MusicPlayerViewModel2.Factory(idx)
                )
                val state = vm.playerState.collectAsState().value

                SongScreen2(
                    state = state,
                    onBackPress = {
                        navigationController.popBackStack()
                    }
                )
                isSheetOpen = false


            }
            composable(
                Navigationitems.MusicPlayerWithIndex3.route,
                arguments = listOf(navArgument("idx") { type = NavType.IntType })
            ) {
                val idx = it.arguments?.getInt("idx") ?: 0
                isSheetOpen = true
//                Log.d("MusicPlayerWithIndex", "MusicPlayerWithIndex")
                val vm: MusicPlayerViewModel3 = viewModel(
                    factory = MusicPlayerViewModel3.Factory(idx)
                )
                val state = vm.playerState.collectAsState().value

                SongScreen3(
                    state = state,
                    onBackPress = {
                        navigationController.popBackStack()
                    }
                )
                isSheetOpen = false


            }
        }
        if (showConnectUsDialog.value) {
            ConnectUsDialog(
                onDismiss = {
                    showConnectUsDialog.value = false
                    CustomDialogViewModel.onDismissDialog()
                },
            )
        }
        if (showExitDialog.value) {
            ExitDialog(
                onDismiss = {
                    showExitDialog.value = false
                    CustomDialogViewModel.onDismissDialog()
                },
            )
        }
    }
}

fun navigateToAuthScreen(context: Context) {
    val intent = Intent(context, AuthActivity::class.java)
    context.startActivity(intent)
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
)

