package com.alura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Aquí puedes devolver una vista personalizada de error o redireccionar a una página de error.
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}