package com.pannoniaexpertise.audit.usernameProviders;

public interface UsernameProvider {

  /**
   * Checks if {@link java.security.Principal} is a supported type.
   *
   * @param principal Principal from {@link org.springframework.security.core.Authentication}
   * @return true if it is an instance of the implemented class.
   */
  boolean support(Object principal);

  /**
   * Gets the username from {@link java.security.Principal}.
   *
   * @param principal {@link java.security.Principal}
   * @return username
   */
  String getUsername(Object principal);
}
