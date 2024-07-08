package com.jovelcasais.kotlinlibraryutilisation.ui.uievents

sealed class UIEvent {
    data object CallApi : UIEvent()
}