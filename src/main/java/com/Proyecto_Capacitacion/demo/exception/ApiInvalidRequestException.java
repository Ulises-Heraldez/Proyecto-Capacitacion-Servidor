/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.exception;

/**
 *
 * @author ULISES
 */
public class ApiInvalidRequestException extends Exception {

    public ApiInvalidRequestException(String message) {
        super(message);
    }

    public ApiInvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
