[![Known Vulnerabilities](https://snyk.io/test/github/pannonia-expertise/audit-starter/badge.svg)](https://snyk.io/test/github/pannonia-expertise/audit-starter)

pannonia-expertise/audit-starter:pom.xml

# Auditing Mechanism

Audit starter module provides mechanism for auditing write operations on database. 
Default implementation of type AuditUsernameProvider - PrincipalUsernameProvider will provide current's user name from principal (using spring security) in ThreadLocal manner.  
To override it, one just need to provide custom implementation. 

After AuditUsernameProvider bean has been provided, auditing system is used by implementing Auditable interface in entity, adding AuditListener entity listener, and embeded Audit class object.
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
