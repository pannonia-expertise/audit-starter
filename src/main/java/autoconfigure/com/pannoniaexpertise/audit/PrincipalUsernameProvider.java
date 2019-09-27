package autoconfigure.com.pannoniaexpertise.audit;

import com.pannoniaexpertise.audit.AuditUsernameProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

public class PrincipalUsernameProvider implements AuditUsernameProvider {

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

    if (principal instanceof UserDetails) {
      return ((UserDetails) principal).getUsername();
    } else if (principal instanceof ClientDetails) {
      return ((ClientDetails) principal).getClientId();
    }

    return (String) principal;
  }
}
