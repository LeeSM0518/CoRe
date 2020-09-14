package io.wisoft.core.accounts.service;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SignUpService {

  void signUp(HttpSession session, String email, String name, String password, List<String> interests);

}
