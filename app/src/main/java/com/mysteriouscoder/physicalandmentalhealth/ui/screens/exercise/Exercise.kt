package com.mysteriouscoder.physicalandmentalhealth.ui.screens.exercise

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mysteriouscoder.physicalandmentalhealth.Heading
import com.mysteriouscoder.physicalandmentalhealth.PreferenceManager
import com.mysteriouscoder.physicalandmentalhealth.dataclass.exTypes
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.exercise.component.ExerciseCard
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.exercise.component.ExerciseDetail


@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun Exercise() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val selectedEx = remember { mutableStateOf(0) }
    val prefs = PreferenceManager(LocalContext.current)
    val meditationTimeSpent = prefs.get("exercise")?.toLong() ?: 0
    val context = LocalContext.current

    val activity = remember(context) { context as? ComponentActivity }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Heading("Exercise",
             modifier=Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(exTypes.size) { index ->
                ExerciseCard(
                    index = index
                ) {
                    selectedEx.value = index
                    showBottomSheet = true
                }
            }
        }

        if (showBottomSheet) {
            val startTime by remember {
                mutableLongStateOf(System.currentTimeMillis())
            }
            DisposableEffect(key1 = true) {
                onDispose {
                    val endTime = System.currentTimeMillis()
                    val timeSpent = (endTime - startTime) / 1000
                    val totalTime = (meditationTimeSpent + timeSpent)
                    Log.d("prefs", "start: $startTime end: $endTime timeSpent: $timeSpent, totalTime: $totalTime")
                    prefs.save("exercise", totalTime.toString())
                }
            }
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                ExerciseDetail(exTypes[selectedEx.value])
            }
        }
    }
    BackHandler(onBack = {
        activity?.finishAffinity()
    })
}

//@Preview(showBackground = true)
//@Composable
//private fun ExerciseDetailPreview() {
//    ExerciseDetail(exTypes[0])
//}
