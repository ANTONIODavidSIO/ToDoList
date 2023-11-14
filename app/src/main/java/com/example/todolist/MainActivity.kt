@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var toDoList by remember { mutableStateOf(emptyArray<String>())}

    Column (modifier = Modifier.fillMaxSize()) {
        //Titre
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text ="To Do List",
                fontSize = 25.sp,
                modifier = modifier
                    .padding(bottom = 20.dp, top = 16.dp)
            )
        }

        //Liste des taches

        //Cette partie permet d'affiche chaque tâche au centre de l'écran avec un bouton sur la droite pour supprimer
        if (toDoList.size != 0) {
            for (elem in toDoList) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = elem,
                        modifier = modifier
                    )
                    // filternot permet de choisir uniquement la tache lié au bouton
                    Button(onClick = {
                        toDoList = toDoList.filterNot { it == elem }.toTypedArray()
                    })
                    {
                        Text(text = "-")
                    }
                }

            }
        }
        }

        //Ajouter une tache
        Row (
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = input,
                onValueChange = {newText -> input = newText },
                singleLine = true,
                label = {
                    Text(text = "Ajoutez une tache...")
                }
            )
            // permet d'ajouter la tache (le if permet de ne pas créer de tache si c'est vide
            Button(onClick = {
                if (input != "") {
                    toDoList += input
                    input = ""
                }
            })
            {
                Text(text = "+")
            }
        }
    }

