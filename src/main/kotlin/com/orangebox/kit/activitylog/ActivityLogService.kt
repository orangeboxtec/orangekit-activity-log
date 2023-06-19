package com.orangebox.kit.activitylog

import com.orangebox.kit.core.user.GeneralUser
import com.orangebox.kit.notification.NotificationBuilder
import com.orangebox.kit.notification.NotificationService
import com.orangebox.kit.notification.TypeSendingNotificationEnum
import java.util.Date
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ActivityLogService {

    @Inject
    private lateinit var activityLogDAO: ActivityLogDAO

    @Inject
    private lateinit var notificationService: NotificationService

    fun listByIdObj(idObj: String): List<ActivityLog>?{
        return activityLogDAO.search(activityLogDAO.createBuilder()
            .appendParamQuery("idObj", idObj)
            .appendSort("date", -1)
            .build())
    }

    fun save(activityLog: ActivityLog){
        if(activityLog.id == null){
            activityLog.date = Date()
            println("Date: " + Date().toString())
        }
        activityLogDAO.insert(activityLog)
        if(activityLog.sendNotification == true){
            activityLog.listUsersNotification?.forEach { user ->
                notificationService.sendNotification(NotificationBuilder()
                    .setTypeSending(TypeSendingNotificationEnum.APP)
                    .setMessage(activityLog.activity)
                    .setIdLink(activityLog.idObj)
                    .setTo(user)
                    .build())
            }
        }
    }
}