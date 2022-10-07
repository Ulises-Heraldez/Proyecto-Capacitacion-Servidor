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
public class ApiDuplicatedEntryException extends Exception {

    public ApiDuplicatedEntryException(String message) {
        super(message);
    }

}
