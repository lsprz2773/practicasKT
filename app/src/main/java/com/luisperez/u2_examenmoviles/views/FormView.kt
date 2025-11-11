package com.luisperez.u2_examenmoviles.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luisperez.u2_examenmoviles.components.PersonCard
import com.luisperez.u2_examenmoviles.models.Person
import com.luisperez.u2_examenmoviles.viewModels.FormViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormView(navController: NavController, formViewModel: FormViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Agregar personas")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        FormViewContent(it, formViewModel)
    }
}

@Composable
fun FormViewContent(paddingValues: PaddingValues, fVM: FormViewModel){
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var personId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ingresa los datos de la nueva persona")
        Spacer(modifier = Modifier .height(10.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("Ingresa tu(s) nombre(s)")},
            modifier = Modifier
                .width(300.dp)
        )

        Spacer(modifier = Modifier .height(10.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = {lastName = it},
            label = {Text("Ingresa tu(s) apellido(s)")},
            modifier = Modifier
                .width(300.dp)
        )

        Spacer(modifier = Modifier .height(10.dp))
        OutlinedTextField(
            value = personId,
            onValueChange = {personId = it},
            label = {Text("Ingresa tu CURP o RFC")},
            modifier = Modifier
                .width(300.dp)
        )

        Spacer(modifier = Modifier .height(20.dp))
        Button(
            modifier = Modifier
                .width(300.dp),
            onClick = {
                fVM.addPerson(
                    Person(
                        name = name,
                        lastName = lastName,
                        personId = personId
                    )
                )
                name = ""
                lastName = ""
                personId = ""
            }
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier
            .height(10.dp)
        )

        val personList by fVM.personList.collectAsState()
        LazyColumn {
            if (!personList.isEmpty()){
                items(personList){item ->
                    val delete = SwipeAction(
                        icon = rememberVectorPainter(Icons.Default.Delete),
                        background = Color.Red,
                        onSwipe = {fVM.deletePerson(item)}
                    )
                    SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 270.dp) {
                        PersonCard(item.name, item.lastName, item.personId)
                    }
                }
            }
        }
    }


}