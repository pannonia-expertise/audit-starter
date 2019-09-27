# Auditing Mechanism

To use auditing, you need to provide bean of type AuditUsernameProvider, which will provide current's user username in ThreadLocal manner. 

> If project is also using resource server (v: 2.1.0 or above), this bean can be created like following:
```java
public AuditUsernameProvider provider() {  
   return ResourceServerUsernameProvider::getUsername;  
}
```

After bean has been provided, auditing system is used by implementing Auditable interface in entity, adding AuditListener entity listener, and embeded Audit class object.
```java
@Getter  
@Setter  
@Entity  
@EntityListeners(AuditListener.class)  
public class TheAuditedEntity extends BaseEntity implements Auditable {  
	  
    @Embedded
    Audit audit;
}
```
