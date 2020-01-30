package autoconfigure.com.pannoniaexpertise.audit;

import com.pannoniaexpertise.audit.AuditUsernameProvider;
import com.pannoniaexpertise.audit.usernameProviders.UsernameProvider;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AuditUsernameProvider provider(List<UsernameProvider> usernameProviders) {
    return new PrincipalUsernameProvider(usernameProviders);
  }
}
