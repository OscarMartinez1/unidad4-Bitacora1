package com.example.android.guesstheword.pantallas.juego

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JuegoViewModel : ViewModel() {
    // The current word
    private val _palabra = MutableLiveData<String>()
    val palabra : LiveData<String>get()=_palabra

    // The current score
    private val _puntuacion = MutableLiveData<Int>()
    val puntuacion : LiveData<Int>get()=_puntuacion

    private val _elEventoTermino=MutableLiveData<Boolean>()
    val elEventoTermino : LiveData<Boolean>get()=_elEventoTermino

    // The list of words - the front of the list is the next word to guess
    private lateinit var listaDePalabras: MutableList<String>


    init {

        Log.i("JuegoViewModel","JuegoViewModel creado!!")

        _elEventoTermino.value=false
        reiniciarLista()
        siguientePalabra()
        _puntuacion.value=0


    }

    override fun onCleared() {
        super.onCleared()
        Log.i("JuegoViewModel","JuegoViewModel destruido!!")
    }
    /**
     * Resets the list of words and randomizes the order
     */
    private fun reiniciarLista() {
        listaDePalabras = mutableListOf(
                "princesa",
                "hospital",
                "baloncesto",
                "gato",
                "monedas",
                "perro",
                "sopa",
                "calendario",
                "triste",
                "escritorio",
                "guitarra",
                "casa",
                "carretera",
                "elefante",
                "llanta",
                "carro",
                "silla",
                "teléfono",
                "bolsa",
                "botella",
                "arma"
        )
        listaDePalabras.shuffle()
    }


    private fun siguientePalabra() {
        //Select and remove a word from the list
        if (listaDePalabras.isEmpty()) {
           // juegoTerminado()
            _elEventoTermino.value=true
        } else {
            _palabra.value = listaDePalabras.removeAt(0)
        }

    }

    /** Methods for buttons presses **/

    fun clickOmitir() {
        _puntuacion.value = (_puntuacion.value)?.minus(1)
        siguientePalabra()
    }
    fun clickLoConseguiste() {
        _puntuacion.value = (_puntuacion.value)?.plus(1)
        siguientePalabra()
    }

    fun terminarJuego(){
        _elEventoTermino.value=false
    }


}