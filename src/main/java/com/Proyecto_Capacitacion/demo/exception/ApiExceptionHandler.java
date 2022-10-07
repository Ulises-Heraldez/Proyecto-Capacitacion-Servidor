/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author TESTER
 */
@ControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(value = {ApiInvalidRequestException.class})
//    public ResponseEntity<Object> handleApiRequestException(ApiInvalidRequestException e, HttpStatus httpStatus){
    public ResponseEntity<Object> handleApiRequestException(ApiInvalidRequestException e){
        
        //1.- Detalles de la excepción
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        ApiException apiException = new ApiException (
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("GMT-6"))
        );
        
        //2.- Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }
    
    
    
    @ExceptionHandler(value = {ApiEmptyRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiEmptyRequestException e){
        
        //1.- Detalles de la excepción
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        
        ApiException apiException = new ApiException (
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("GMT-6"))
        );
        
        //2.- Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }
    
    
        
    @ExceptionHandler(value = {ApiDuplicatedEntryException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiDuplicatedEntryException e){
        
        //1.- Detalles de la excepción
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        
        ApiException apiException = new ApiException (
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("GMT-6"))
        );
        
        //2.- Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
