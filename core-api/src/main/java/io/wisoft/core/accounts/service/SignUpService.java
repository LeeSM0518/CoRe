package io.wisoft.core.accounts.service;

import java.util.List;

public interface SignUpService {

  void signUp(String email, String name, String password, List<String> interests);

}
