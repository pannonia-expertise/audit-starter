package autoconfigure.com.pannoniaexpertise.audit;

import com.pannoniaexpertise.audit.AuditUsernameProvider;
import com.pannoniaexpertise.audit.usernameProviders.UsernameProvider;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class PrincipalUsernameProvider implements AuditUsernameProvider {

  private final List<UsernameProvider> usernameProviders;

  public PrincipalUsernameProvider(final List<UsernameProvider> usernameProviders) {
    this.usernameProviders = usernameProviders;
  }

  @Override
  public String getUsername() {
    final SecurityContext context = SecurityContextHolder.getContext();
    if (context == null) {
      throw new UsernameProviderException("Security context not found");
    }
    final Authentication authentication = context.getAuthentication();
    if (authentication == null) {
      throw new UsernameProviderException("Authentication not found in security context");
    }
    final Object principal = authentication.getPrincipal();
    if (principal == null) {
      throw new UsernameProviderException("User not found in SecurityContext");
    }

    for (UsernameProvider usernameProvider : usernameProviders) {
      if (usernameProvider.support(principal)) {
        return usernameProvider.getUsername(principal);
      }
    }

    return (String) principal;
  }
}
