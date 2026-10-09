package com.example.smarthomegitops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smarthomegitops.presentation.viewmodel.MainViewModel
import com.example.smarthomegitops.presentation.viewmodel.UiState
import com.example.smarthomegitops.ui.theme.SmartHomeGitOpsTheme
import androidx.compose.material3.Button

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SmartHomeGitOpsTheme {

                val viewModel: MainViewModel = viewModel()
                val uiState by viewModel.uiState.collectAsState()

                SecurityScreen(
                    uiState = uiState,
                    viewModel = viewModel
                )
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun SecurityScreen(
    uiState: UiState,
    viewModel: MainViewModel
) {

    when (uiState) {

        UiState.Normal -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "NORMAL",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF2E7D32)
                )

                Text(
                    text = "No security threats detected.",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        is UiState.SecurityAlert -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "SECURITY ALERT",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Red
                )

                Text(
                    text = "Confidence: ${uiState.confidence}%",
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = uiState.rawText,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(
                    onClick = {
                        viewModel.forceReject(uiState.pullNumber)
                    },
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text("FORCE REJECT")
                }
                Button(
                    onClick = {
                        viewModel.forceMerge(uiState.pullNumber)
                    },
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Text("FORCE MERGE")
                }
            }
        }
        is UiState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ERROR",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Red
                )

                Text(
                    text = uiState.message,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}