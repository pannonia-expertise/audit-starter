package autoconfigure.com.pannoniaexpertise.audit;

import com.pannoniaexpertise.audit.AuditUsernameProvider;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AuditUsernameProvider provider() {
    return new PrincipalUsernameProvider();
  }
}
