package com.unicom.fmos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhaojb on 2016/12/8.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "resource not found")
public class ResourceNotFoundException extends RuntimeException{
}
