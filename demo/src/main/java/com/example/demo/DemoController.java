package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

  @GetMapping("/foo")
  public String foo(Model model, Authentication authentication) {
    model.addAttribute("principal", authentication.getPrincipal());
    return "index";
  }

  @GetMapping("/bar")
  public String bar(Model model, Authentication authentication) {
    model.addAttribute("principal", authentication.getPrincipal());
    return "index";
  }
}
