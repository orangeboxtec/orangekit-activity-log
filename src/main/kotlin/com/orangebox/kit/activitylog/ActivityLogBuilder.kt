package com.orangebox.kit.activitylog

import com.orangebox.kit.core.exception.BusinessException
import java.util.*

class ActivityLogBuilder {
    private val activityLog: ActivityLog

    init {
        activityLog = ActivityLog()
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

    fun setDate(date: Date): ActivityLogBuilder {
        activityLog.date = date
        return this
    }

    fun setIdObj(idObj: String): ActivityLogBuilder {
        activityLog.idObj = idObj
        return this
    }

    fun setNameUser(nameUser: String): ActivityLogBuilder {
        activityLog.nameUser = nameUser
        return this
    }

    fun setIdUser(idUser: String): ActivityLogBuilder {
        activityLog.idUser = idUser
        return this
    }

    fun setActivity(activity: String): ActivityLogBuilder {
        activityLog.activity = activity
        return this
    }

    fun setObj(obj: Any): ActivityLogBuilder {
        activityLog.obj = obj
        return this
    }
}