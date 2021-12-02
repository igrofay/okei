package com.training.okei.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Month(
    var nameMonth: String ="", // Имя месяца
    var lastChange : String="", // Последнее изменение за этот месяц
    var leader: String="", // Лидер за этот месяц
    var underway : Boolean? = null, // Идёт ли сейчас
    var progress : Float=0f // На сколько выполнен это месяц ( Ну тип сумма всех баллов деленна на 1980 балла)
)