package com.unicom.fmos.exception.handler;
import com.unicom.fmos.exception.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Created by zhaojb on 2016/12/8.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundHandler(Model model) {
        model.addAttribute("reason", "resource not found");
        return "error.jsp";
    }

}
