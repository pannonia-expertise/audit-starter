package autoconfigure.com.pannoniaexpertise.audit;

import static org.assertj.core.api.Assertions.assertThat;

import com.pannoniaexpertise.audit.AuditUsernameProvider;

import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class AuditAutoConfigurationTest {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
      .withConfiguration(AutoConfigurations.of(AuditAutoConfiguration.class));

  @Test
  public void when_provider_not_provided_then_principal_username_provider_is_created() {
    contextRunner //
        .run(context -> {
          assertThat(context).hasSingleBean(AuditUsernameProvider.class);
          assertThat(context.getBean(PrincipalUsernameProvider.class)).isSameAs( //
              context.getBean(AuditAutoConfiguration.class).provider() //
          );
        });
  }
}
