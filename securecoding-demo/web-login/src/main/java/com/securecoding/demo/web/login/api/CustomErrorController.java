package com.securecoding.demo.web.login.api;

import com.securecoding.demo.web.login.api.model.WebLoginRequestModel;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = {"text/html"})
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("loginRequestModel", WebLoginRequestModel.builder().build());
        Object requestUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestUri == null) {
            requestUri = "login";
        }
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (statusCode != null && exception != null) {
            model.addAttribute("error", statusCode.toString() + " - " + getRootCause(exception));
            return requestUri.toString();
        } else {
            AuthenticationException authenticationException = getLastAuthException(request);
            if (authenticationException != null) {
                model.addAttribute("error", HttpServletResponse.SC_UNAUTHORIZED + " - " +
                        authenticationException.getMessage());
                return requestUri.toString();
            }
        }
        setErrorAttribute(model, statusCode);
        return "error";
    }

    private void setErrorAttribute(Model model, Object statusCode) {
        if (statusCode != null) {
            model.addAttribute("error", statusCode.toString());
        } else {
            model.addAttribute("error", HttpServletResponse.SC_UNAUTHORIZED + " - " +
                    "Authorization error!");
        }
    }

    private AuthenticationException getLastAuthException(HttpServletRequest request) {
        Object authenticationException = request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (authenticationException == null) {
            authenticationException = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }
        return authenticationException != null ? (AuthenticationException) authenticationException : null;
    }

    private String getRootCause(Object exception) {
        Throwable throwable = (Throwable) exception;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        return throwable.getMessage();
    }

}
