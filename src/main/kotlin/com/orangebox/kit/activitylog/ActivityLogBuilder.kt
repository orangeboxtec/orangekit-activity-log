package com.orangebox.kit.activitylog

import com.orangebox.kit.core.exception.BusinessException
import java.util.*

class ActivityLogBuilder {

    private val activityLog = ActivityLog()

    init {
        activityLog.date = Date()
    }

    fun build(): ActivityLog {
        if (activityLog.idObj == null) {
            throw BusinessException("idObj needed")
        }
        if (activityLog.activity == null) {
            throw BusinessException("activity needed")
        }
        return activityLog
    }

    fun date(date: Date): ActivityLogBuilder {
        activityLog.date = date
        return this
    }

    fun idObj(idObj: String): ActivityLogBuilder {
        activityLog.idObj = idObj
        return this
    }

    fun nameUser(nameUser: String): ActivityLogBuilder {
        activityLog.nameUser = nameUser
        return this
    }

    fun idUser(idUser: String): ActivityLogBuilder {
        activityLog.idUser = idUser
        return this
    }

    fun activity(activity: String): ActivityLogBuilder {
        activityLog.activity = activity
        return this
    }

    fun obj(obj: Any): ActivityLogBuilder {
        activityLog.obj = obj
        return this
    }
}