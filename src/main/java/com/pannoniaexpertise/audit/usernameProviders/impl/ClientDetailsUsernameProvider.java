package com.pannoniaexpertise.audit.usernameProviders.impl;

import com.pannoniaexpertise.audit.usernameProviders.UsernameProvider;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

/**
 * Username provider class for {@link ClientDetails}
 */
@Component
public class ClientDetailsUsernameProvider implements UsernameProvider {

  @Override
  public boolean support(Object principal) {
    if (principal == null) {
      return false;
    }
    return principal instanceof ClientDetails;
  }

  @Override
  public String getUsername(Object principal) {
    return ((ClientDetails) principal).getClientId();
  }
}
