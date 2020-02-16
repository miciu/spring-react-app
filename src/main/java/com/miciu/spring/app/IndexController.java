package com.miciu.spring.app;

import com.miciu.spring.app.currentuser.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

  @GetMapping(value = {"/", "index.html"})
  public ModelAndView index(CurrentUser currentUser) {
    if (currentUser != null) {
      log.info("Logged in user: {} with roles: {}",currentUser.getName(), currentUser.getRoles());
    }
    return new ModelAndView("index");
  }
}