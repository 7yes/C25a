package com.jesse.c25a.mlkit.mlkScanner

import android.app.Activity.RESULT_OK
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.jesse.c25a.MainActivity
import java.io.File
import java.io.FileOutputStream

@Composable
fun MlkScanScreen(activity: MainActivity) {
    val context = LocalContext.current.applicationContext
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val options = GmsDocumentScannerOptions.Builder()
            .setPageLimit(10)
            .setScannerMode(SCANNER_MODE_FULL)
            .setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_JPEG, RESULT_FORMAT_PDF)
            .build()

        val scanner = GmsDocumentScanning.getClient(options)

        var imageUri by remember {
            mutableStateOf<List<Uri>>(emptyList())
        }

        val scannerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { it ->
                if (it.resultCode == RESULT_OK) {
                    val result =
                        GmsDocumentScanningResult.fromActivityResultIntent(it.data)
                    imageUri = result?.pages?.map { it.imageUri } ?: emptyList()

                    //hago algo, lo guardo en local dir
                    result?.pdf?.let { pdf->
                        val fos = FileOutputStream(File(context.filesDir,"scanned.pdf"))
                        context.contentResolver.openInputStream(pdf.uri)?.use { newPdf->
                            newPdf.copyTo(fos)
                        }
                    }
                }
            }
        )

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            imageUri.forEach { uri ->
                AsyncImage(
                    model = uri, contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    //modifier = Modifier.fillMaxSize()
                )
            }
            Button(onClick = {
                scanner.getStartScanIntent(
                   activity
                )
                    .addOnSuccessListener {
                        scannerLauncher.launch(
                            IntentSenderRequest.Builder(it).build()
                        )
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }) {
                Text(text = "Scan Document")
            }
        }
    }
}