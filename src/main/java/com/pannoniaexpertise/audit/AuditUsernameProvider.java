package com.pannoniaexpertise.audit;

/**
 * Provides current user for audit.
 * Should provide user in ThreadLocal manner.
 */

public interface AuditUsernameProvider {

    /**
     * Mehtod that should be implemented to return current user, in ThreadLocal manner.
     *
     * @return username
     */
    String getUsername();
}
