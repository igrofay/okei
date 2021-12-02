package com.training.okei.data

data class Evaluation(
    var numEval : String ="", // Тип номер вороса, например -> 1.2 , 2.2 , 3.1
    var lastChangeEval : String = "", // Последнее изменение этой оценки
    var lastEvaluating : String = "", // Последний оценсчик который оценивал
    var countPoints: Int = 0 // Кол-во очков
)
