package com.miciu.spring.app.resolvers;

import com.miciu.spring.app.currentuser.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

public class CurrentUserResolver implements HandlerMethodArgumentResolver {
  
  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.getParameterType() == CurrentUser.class;
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Principal principal = nativeWebRequest.getUserPrincipal();
    
    if (principal == null) {
      return null;
    }

    if (authentication == null) {
      return null;
    }
    
    //TODO create and return CurrentUser object 
    //use principal and authentication to create current user
    // map authentication.getAuthorities() to coma separated string - use streams and joining(", ")
    return null;
  }
}
