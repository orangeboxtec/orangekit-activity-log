package com.orangebox.kit.activitylog

import com.orangebox.kit.core.dao.AbstractDAO
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ActivityLogDAO : AbstractDAO<ActivityLog>(ActivityLog::class.java) {
    override fun getId(bean: ActivityLog): Any? {
        return bean.id
    }
}