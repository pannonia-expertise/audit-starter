package com.pannoniaexpertise.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * This is embeddable class used for auditing.
 * Add it to entity that needs to be audited with @Embedded annotation.
 * [ @Embedded private Audit audit = new Audit() ]
 * When creating SQL table following fields should be added for auditing.
 *
 * (created_on        bigint                    NOT NULL,
 * created_by        character varying(255)    NOT NULL,
 * updated_on        bigint,
 * updated_by        character varying(255))
 */


@Embeddable
@Getter
@Setter
public class Audit implements Serializable {

    /**
     * Username to be used if {@link Audit#useSystemUser} is set to true.
     */
    public static final String SYSTEM_USERNAME = "system";

    /**
     * If set, result from {@link AuditUsernameProvider::getUsername()} will be ignored,
     * and {@link AuditUsernameProvider::systemUserName} used instead.
     * This variable is in ThreadLocal, so that it has same access level as spring's security context, so the same rules apply.
     */
    private static final ThreadLocal<Boolean> useSystemUser = ThreadLocal.withInitial(() -> Boolean.FALSE);

    @NotNull
    @Column(name = "created_on", updatable = false)
    private Long createdOn;

    @NotNull
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_on")
    private Long updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * Force audit mechanism to use 'system' user.
     * NOTE: This method is using thread local storage,
     * it will apply to every audit update until end of thread.
     * @param useSystem boolean
     */
    public static void useSystemUser(boolean useSystem) {
        useSystemUser.set(useSystem);
    }

    static boolean useSystemUser() {
        return useSystemUser.get();
    }
}
