package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.AchatDTO;
import com.styloChic.ecommerce.requests.AchatRequest;
import com.styloChic.ecommerce.services.AchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/achats/admin")
public class AchatAdminController {

    @Autowired
    private AchatService achatService;

    @PostMapping("/")
    public ResponseEntity<AchatDTO> creerAchat(@RequestBody AchatRequest request,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        AchatDTO achatDTO = achatService.creerAchat(request, jwt);
        return new ResponseEntity<>(achatDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchatDTO> chercherAchatParId(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        AchatDTO achatDTO = achatService.chercherAchatParId(id, jwt);
        return new ResponseEntity<>(achatDTO, HttpStatus.OK);
    }

    @GetMapping("/fournisseur/{fournisseurId}")
    public ResponseEntity<List<AchatDTO>> historiqueAchatsFournisseur(@PathVariable Long fournisseurId,
                                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        List<AchatDTO> achats = achatService.historiqueAchatsFournisseur(fournisseurId, jwt);
        return new ResponseEntity<>(achats, HttpStatus.OK);
    }

    @PutMapping("/{id}/confirmer")
    public ResponseEntity<AchatDTO> confirmerAchat(@PathVariable Long id,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        AchatDTO achatDTO = achatService.confirmerAchat(id, jwt);
        return new ResponseEntity<>(achatDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}/receptionner")
    public ResponseEntity<AchatDTO> receptionnerAchat(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        AchatDTO achatDTO = achatService.receptionnerAchat(id, jwt);
        return new ResponseEntity<>(achatDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<AchatDTO> annulerAchat(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        AchatDTO achatDTO = achatService.annulerAchat(id, jwt);
        return new ResponseEntity<>(achatDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AchatDTO>> avoirTousAchats(@RequestHeader("Authorization") String jwt) throws Exception {
        List<AchatDTO> achats = achatService.avoirTousAchats(jwt);
        return new ResponseEntity<>(achats, HttpStatus.OK);
    }
}
