/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author TESTER
 */
public class ApiInvalidRequestException extends Exception {

    public ApiInvalidRequestException(String message) {
        super(message);
    }

//    public ApiInvalidRequestException(String message, HttpStatus httpStatus) {
//        super(message, httpStatus);
//    }


    public ApiInvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
