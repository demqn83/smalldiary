package ru.demqn.smalldiary.data.db

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.demqn.smalldiary.data.repositories.ListToDoEntity

class ToDoJson {

    val todoEntity = Json.decodeFromString<ListToDoEntity>(
        """
        {
           "list":[
            {"id":1,
            "date_start":"1618822081000",
            "date_finish":"1618825681000",
            "name":"My task 1",
            "description":"Значение соображения эксперимент интересный подготовки модель по интересный задача условий организац"},
            
            {"id":2,
            "date_start":"1618836481000",
            "date_finish":"1618836961000",
            "name":"My task 2",
            "description":"Рост также место требуют в сфера соображения существенных что и же показывает, активности играет рос"},
            
            {"id":3,
            "date_start":"1618942921000",
            "date_finish":"1618943101000",
            "name":"My task 3",
            "description":"Условий а рост практика и что проверки занимаемых финансовых кадров повседневная условий направлений"},
            
            {"id":4,
            "date_start":"1619005021000",
            "date_finish":"1619005026000",
            "name":"My task 4",
            "description":"Поставленных условий нашей кадров важные играет разработке заданий позволяет а сложившаяся также акт"}
            ]
        } 
    """
    ).list

}