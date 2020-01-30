package com.pannoniaexpertise.audit.usernameProviders.impl;

import com.pannoniaexpertise.audit.usernameProviders.UsernameProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Username provider class {@link UserDetails}
 */
@Component
public class UserDetailsUsernameProvider implements UsernameProvider {

  @Override
  public boolean support(Object principal) {
    if (principal == null) {
      return false;
    }
    return principal instanceof UserDetails;
  }

  @Override
  public String getUsername(Object principal) {
    return ((UserDetails) principal).getUsername();
  }
}
