package io.wisoft.core.accounts.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {

  UserDetails findMemberByEmail(String email);

}
