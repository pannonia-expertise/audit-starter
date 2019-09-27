package com.pannoniaexpertise.audit;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit a);
}
