package com.glsi.xpress.Service;

import com.glsi.xpress.Entity.AuditLog;

import java.util.List;

public interface AuditLogService {

    //addAuditLog
    AuditLog addAuditLog(AuditLog auditLog);
    //getAuditLogs
    List<AuditLog> getAuditLogs();

}
