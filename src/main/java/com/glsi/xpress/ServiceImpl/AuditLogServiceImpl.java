package com.glsi.xpress.ServiceImpl;

import com.glsi.xpress.Entity.AuditLog;
import com.glsi.xpress.Repository.AuditLogRepository;
import com.glsi.xpress.Repository.UserRepository;
import com.glsi.xpress.Service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public addAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }
}
