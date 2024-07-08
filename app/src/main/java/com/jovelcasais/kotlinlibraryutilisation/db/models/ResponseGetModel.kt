package com.jovelcasais.kotlinlibraryutilisation.db.models

import com.google.gson.annotations.SerializedName

/* Generic Response Holder for List */
data class ResponseGetModel<T> (
    @SerializedName("data") var data : ArrayList<T> = arrayListOf(),
)
