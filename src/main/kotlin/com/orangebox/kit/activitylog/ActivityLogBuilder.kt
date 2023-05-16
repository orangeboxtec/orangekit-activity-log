package com.orangebox.kit.activitylog

import com.orangebox.kit.core.exception.BusinessException
import com.orangebox.kit.core.user.GeneralUser
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

    fun date(date: Date?): ActivityLogBuilder {
        activityLog.date = date
        return this
    }

    fun idObj(idObj: String): ActivityLogBuilder {
        activityLog.idObj = idObj
        return this
    }

    fun user(user: GeneralUser): ActivityLogBuilder {
        activityLog.user = user
        return this
    }

    fun activity(activity: String): ActivityLogBuilder {
        activityLog.activity = activity
        return this
    }

    fun obj(obj: Any?): ActivityLogBuilder {
        activityLog.obj = obj
        return this
    }

    fun sendNotification(sendNotification: Boolean?): ActivityLogBuilder {
        activityLog.sendNotification = sendNotification
        return this
    }

    fun listUsersNotification(listUsersNotification: List<GeneralUser>?): ActivityLogBuilder {
        activityLog.listUsersNotification = listUsersNotification
        return this
    }
}