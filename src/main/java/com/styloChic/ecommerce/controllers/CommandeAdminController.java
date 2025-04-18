package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.dtos.CommandeDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.requests.UpdateDateLivraisonRequest;
import com.styloChic.ecommerce.responses.ApiResponse;
import com.styloChic.ecommerce.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/commandes/admin")
public class CommandeAdminController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/")
    public ResponseEntity<List<CommandeDTO>> obtenierToutesCommandes(@RequestHeader("Authorization") String jwt) throws CommandeException{
        List<CommandeDTO> commandeDTOS = commandeService.avoirToutesCommandes(jwt);
        return new ResponseEntity<List<CommandeDTO>>(commandeDTOS, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> chercherCommandeParIdPourAdmin(@PathVariable("id") Long commandeId,
                                                             @RequestHeader("Authorization") String jwt) throws CommandeException {

        CommandeDTO commandeDTO = commandeService.chercherCommandeParIdPourAdmin(commandeId,jwt);

        return new ResponseEntity<>(commandeDTO,HttpStatus.CREATED);
    }

    @PutMapping("/{idCommande}/confirmer")
    public ResponseEntity<CommandeDTO> commandeConfirmee(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {
        CommandeDTO commandeDTO = commandeService.commandeConfirmee(idCommande,jwt);

        return new ResponseEntity<>(commandeDTO, HttpStatus.OK);
    }

    @PutMapping("/{idCommande}/expedier")
    public ResponseEntity<CommandeDTO> commandeExpedie(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {
        CommandeDTO commandeDTO = commandeService.commandeExpediee(idCommande,jwt);

        return new ResponseEntity<>(commandeDTO,HttpStatus.OK);
    }

    @PutMapping("/{idCommande}/livrer")
    public ResponseEntity<CommandeDTO> DeliverOrderHandler(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {
        CommandeDTO commandeDTO = commandeService.commandeLivree(idCommande,jwt);

        return new ResponseEntity<>(commandeDTO,HttpStatus.OK);
    }

    @PutMapping("/{idCommande}/annuler")
    public ResponseEntity<CommandeDTO> CancelOrderHandler(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {
        CommandeDTO commandeDTO = commandeService.commandeAnnulee(idCommande,jwt);

        return new ResponseEntity<>(commandeDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{idCommande}/supprimer")
    public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {


        commandeService.supprimerCommande(idCommande,jwt);
        ApiResponse res = new ApiResponse();
        res.setMessage("Commande supprimée avec succès !");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    @PutMapping("/{idCommande}/dateLivraison")
    public ResponseEntity<CommandeDTO> mettreAJourDateLivraison(
            @PathVariable Long idCommande,
            @RequestBody UpdateDateLivraisonRequest request,
            @RequestHeader("Authorization") String jwt) throws CommandeException {

        CommandeDTO commandeDTO = commandeService.mettreAJourDateLivraison(
                idCommande,
                request.getNouvelleDateLivraison(),
                jwt
        );
        return new ResponseEntity<>(commandeDTO, HttpStatus.OK);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> compterCommandes(@RequestHeader("Authorization") String jwt) throws CommandeException {
        long total = commandeService.compterCommandes(jwt);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

}
