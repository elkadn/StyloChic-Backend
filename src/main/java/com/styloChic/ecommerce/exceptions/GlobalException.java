package com.styloChic.ecommerce.exceptions;


import com.styloChic.ecommerce.responses.ErreurResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(UtilisateurException.class)
    public ResponseEntity<Object> UtilisateurException(UtilisateurException ex) {
        if (ex.getMessage().contains("Accès interdit")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("Utilisateur non existant")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        if (ex.getMessage().contains("L'email est déjà utilisé")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

