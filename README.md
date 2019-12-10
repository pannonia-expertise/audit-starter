[![Known Vulnerabilities](https://snyk.io/test/github/pannonia-expertise/audit-starter/badge.svg)](https://snyk.io/test/github/pannonia-expertise/audit-starter)  [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.pannoniaexpertise%3Aaudit-starter&metric=coverage)](https://sonarcloud.io/dashboard?id=com.pannoniaexpertise%3Aaudit-starter) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.pannoniaexpertise%3Aaudit-starter&metric=bugs)](https://sonarcloud.io/dashboard?id=com.pannoniaexpertise%3Aaudit-starter) [![Maintainability](https://api.codeclimate.com/v1/badges/9c8903dda63db997c66c/maintainability)](https://codeclimate.com/github/pannonia-expertise/audit-starter/maintainability)



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
