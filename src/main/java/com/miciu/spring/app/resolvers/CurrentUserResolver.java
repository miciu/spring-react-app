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
import java.util.stream.Collectors;

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
    
    String roles = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(", "));
    
    return new CurrentUser(principal.getName(), roles);    
  }
}
