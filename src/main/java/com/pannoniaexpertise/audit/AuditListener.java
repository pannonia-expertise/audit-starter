package com.pannoniaexpertise.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

    private final AuditUsernameProvider usernameProvider;

    public AuditListener(AuditUsernameProvider usernameProvider) {
      this.usernameProvider = usernameProvider;
    }

    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();

        if (audit == null) {
            audit = new Audit();
            auditable.setAudit(audit);
        }

        audit.setCreatedOn(System.currentTimeMillis());

        if (Audit.useSystemUser()) {
            audit.setCreatedBy(Audit.SYSTEM_USERNAME);
        } else {
            audit.setCreatedBy(usernameProvider.getUsername());
        }
    }

    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();

        audit.setUpdatedOn(System.currentTimeMillis());

        if (Audit.useSystemUser()) {
            audit.setUpdatedBy(Audit.SYSTEM_USERNAME);
        } else {
            audit.setUpdatedBy(usernameProvider.getUsername());
        }
    }
}
