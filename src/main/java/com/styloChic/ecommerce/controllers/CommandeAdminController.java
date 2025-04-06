package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.dtos.CommandeDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.responses.ApiResponse;
import com.styloChic.ecommerce.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/commandes/admi,")
public class CommandeAdminController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/")
    public ResponseEntity<List<CommandeDTO>> obtenierToutesCommandes(@RequestHeader("Authorization") String jwt) throws CommandeException{
        List<CommandeDTO> commandeDTOS = commandeService.avoirToutesCommandes(jwt);
        return new ResponseEntity<List<CommandeDTO>>(commandeDTOS, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{idCommande}/confirmee")
    public ResponseEntity<CommandeDTO> commandeConfirmee(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {
        CommandeDTO commandeDTO = commandeService.commandeConfirmee(idCommande,jwt);

        return new ResponseEntity<>(commandeDTO, HttpStatus.OK);
    }

    @PutMapping("/{idCommande}/expediee")
    public ResponseEntity<CommandeDTO> commandeExpedie(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {
        CommandeDTO commandeDTO = commandeService.commandeExpediee(idCommande,jwt);

        return new ResponseEntity<>(commandeDTO,HttpStatus.OK);
    }

    @PutMapping("/{idCommande}/livree")
    public ResponseEntity<CommandeDTO> DeliverOrderHandler(@PathVariable Long idCommande, @RequestHeader("Authorization") String jwt)
            throws CommandeException {
        CommandeDTO commandeDTO = commandeService.commandeLivree(idCommande,jwt);

        return new ResponseEntity<>(commandeDTO,HttpStatus.OK);
    }

    @PutMapping("/{idCommande}/annulee")
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
}
