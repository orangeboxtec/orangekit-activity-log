package com.orangebox.kit.activitylog

import com.orangebox.kit.core.dao.OperationEnum
import com.orangebox.kit.core.dao.SearchBuilder
import com.orangebox.kit.core.dto.ResponseList
import com.orangebox.kit.notification.NotificationBuilder
import com.orangebox.kit.notification.NotificationService
import com.orangebox.kit.notification.TypeSendingNotificationEnum
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.util.*

@ApplicationScoped
class ActivityLogService {

    private val QUANTITY_PAGE = 12

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
        }
        activityLogDAO.insert(activityLog)
        if(activityLog.sendNotification == true){
            activityLog.listUsersNotification?.forEach { user ->
                notificationService.sendNotification(NotificationBuilder()
                    .setTypeSending(TypeSendingNotificationEnum.APP)
                    .setMessage(activityLog.activity)
                    .setIdLink(activityLog.idObj)
                    .setTypeFrom(activityLog.typeObj)
                    .setTo(user)
                    .build())
            }
        }
    }

    fun search(search: ActivityLogSearch): ResponseList<ActivityLog>? {
        var list: MutableList<ActivityLog>? = null
        val searchBuilder: SearchBuilder = activityLogDAO.createBuilder()

        if (search.queryString != null && search.queryString!!.isNotEmpty()) {
            searchBuilder.appendParamQuery("company.document|customer.document|customer.email|customer.name", search.queryString!!, OperationEnum.OR_FIELDS_LIKE)
        }

        if (search.idObj != null && search.idObj!!.isNotEmpty()) {
            searchBuilder.appendParamQuery("idObj", search.idObj!!)
        }

        if (search.typeObj != null && search.typeObj!!.isNotEmpty()) {
            searchBuilder.appendParamQuery("typeObj", search.typeObj!!)
        }

        if (search.nameObj != null && search.nameObj!!.isNotEmpty()) {
            searchBuilder.appendParamQuery("nameObj", search.nameObj!!)
        }

        if (search.beginDate != null) {
            searchBuilder.appendParamQuery("date", search.beginDate!!, OperationEnum.GTE)
        }

        if (search.endDate != null) {
            searchBuilder.appendParamQuery("date", search.endDate!!, OperationEnum.LTE)
        }

        if (search.obj != null) {
            search.obj?.keys?.forEach { key ->
                searchBuilder.appendParamQuery("info.$key", search.obj!![key]!!)
            }
        }

        searchBuilder.appendSort("date", -1)
        searchBuilder.setFirst(QUANTITY_PAGE * search.page!!)
        searchBuilder.setMaxResults(QUANTITY_PAGE)
        return activityLogDAO.searchToResponse(searchBuilder.build())
    }
}