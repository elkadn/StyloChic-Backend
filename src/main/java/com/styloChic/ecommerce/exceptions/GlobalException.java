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
        if (ex.getMessage().contains("Acc40ès interdit")) {
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


    @ExceptionHandler(CategorieException.class)
    public ResponseEntity<Object> CategorieException(CategorieException ex) {
        if (ex.getMessage().contains("Accès interdit")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("Catégorie parente introuvable") || ex.getMessage().contains("non trouvée") || ex.getMessage().contains("Aucune")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        if (ex.getMessage().contains("déjà existante") || ex.getMessage().contains("doit avoir") || ex.getMessage().contains("Impossible") || ex.getMessage().contains("invalide")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FournisseurException.class)
    public ResponseEntity<Object> FournisseurException(FournisseurException ex) {
        if (ex.getMessage().contains("Accès interdit")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("non trouvé")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        if (ex.getMessage().contains("existe déjà") || ex.getMessage().contains("Impossible de supprimer ce fournisseur")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CouleurException.class)
    public ResponseEntity<Object> CouleurException(CouleurException ex) {
        if (ex.getMessage().contains("Accès interdit") || ex.getMessage().contains("pas autorisé")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("Couleur introuvable") || ex.getMessage().contains("Couleur non trouvée avec l'ID")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        if (ex.getMessage().contains("Une autre couleur avec le nom") || ex.getMessage().contains("Impossible") || ex.getMessage().contains("existe déjà")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProduitException.class)
    public ResponseEntity<Object> ProduitException(ProduitException ex) {
        if (ex.getMessage().contains("Accès interdit")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("introuvable") || ex.getMessage().contains("non trouvé")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ElementPanierException.class)
    public ResponseEntity<Object> ElementPanierException(ElementPanierException ex) {
        if (ex.getMessage().contains("Accès interdit") || ex.getMessage().contains("pas autorisé") || ex.getMessage().contains("Ce n'est pas votre")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("non trouvé")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        if (ex.getMessage().contains("invalide")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CodeReductionException.class)
    public ResponseEntity<Object> CodeReductionException(CodeReductionException ex) {
        if (ex.getMessage().contains("Accès interdit")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("non trouvé") || ex.getMessage().contains("invalide")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        if (ex.getMessage().contains("existe déjà")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommandeException.class)
    public ResponseEntity<Object> CommandeException(CommandeException ex) {

        if (ex.getMessage().contains("Ce n'est pas votre commande")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("non trouvée") || ex.getMessage().contains("Aucune commande trouvée")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OffreSpecialeException.class)
    public ResponseEntity<Object> OffreSpecialeException(OffreSpecialeException ex) {
        if (ex.getMessage().contains("Accès interdit")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("non trouvé")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PubiliciteException.class)
    public ResponseEntity<Object> PubiliciteException(PubiliciteException ex) {
        if (ex.getMessage().contains("Accès interdit")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage().contains("introuvable")) {
            return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ErreurResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}

