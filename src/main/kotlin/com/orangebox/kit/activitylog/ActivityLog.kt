package com.orangebox.kit.activitylog

import com.orangebox.kit.core.annotation.OKEntity
import com.orangebox.kit.core.annotation.OKId
import com.orangebox.kit.core.user.GeneralUser
import org.bson.codecs.pojo.annotations.BsonIgnore
import java.util.Date
import javax.json.bind.annotation.JsonbDateFormat

@OKEntity(name = "activityLog")
class ActivityLog {

    @OKId
    var id: String? = null

    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    var date: Date? = null

    var idObj: String? = null

    var idUser: String? = null

    var nameUser: String? = null

    var activity: String? = null

    var obj: Any? = null

    var sendNotification: Boolean? = null

    @BsonIgnore
    var listUsersNotification: List<GeneralUser>? = null
}