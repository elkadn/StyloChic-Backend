package com.styloChic.ecommerce.controllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.model.PaymentIntent;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.PayPalException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Commande;
import com.styloChic.ecommerce.repositories.CommandeRepository;
import com.styloChic.ecommerce.responses.ApiResponse;
import com.styloChic.ecommerce.responses.PaiementUrlResponse;
import com.styloChic.ecommerce.services.CommandeService;
import com.styloChic.ecommerce.services.PanierService;
import com.styloChic.ecommerce.services.PaypalService;
import com.styloChic.ecommerce.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/styloChic/paiement")
public class PaiementController {
    @Autowired
    private PaypalService paypalService;

    @Autowired
    private StripeService stripeService;

    @Autowired
    private CommandeService commandeService;


    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private PanierService panierService;


    @PostMapping("/ajouter/{commandeId}")
    public ResponseEntity<PaiementUrlResponse> creerLinkPaiement(@PathVariable Long commandeId) throws CommandeException, PayPalException {

        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);
        try {
            String urlAnnulation = "http://localhost:6069/annulee";
            String urlSuccess = "http://localhost:6069/paiement"+commandeId;

            Payment payment = paypalService.createPayment(commande.getMontantReduit(), "USD", "paypal", "sale",
                    "Paiement commande", urlAnnulation, urlSuccess);

            String paymentId = payment.getId();
            String approvalLink = payment.getLinks().stream()
                    .filter(link -> link.getRel().equals("approval_url"))
                    .findFirst().get().getHref();

            PaiementUrlResponse reponse = new PaiementUrlResponse();
            reponse.setPaiement_liaison_id(paymentId);
            reponse.setPaiement_liaison_url(approvalLink);

            return new ResponseEntity<>(reponse, HttpStatus.CREATED);
        } catch (PayPalRESTException e) {
            throw new PayPalException(e.getMessage());
        }
    }

    @GetMapping("/succes")
    public ResponseEntity<ApiResponse> paiementSucces(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @RequestParam("commandeId") Long commandeId)
            throws CommandeException, PayPalException {

        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);

            if (payment.getState().equals("approved")) {
                commande.getDetailsPaiement().setPaymentId(paymentId);
                commande.getDetailsPaiement().setStatus("COMPLETED");
                commande.setStatutCommande("PLACÉE");
                commandeRepository.save(commande);
            }

            panierService.viderPanier(commande.getUtilisateur().getId());
            ApiResponse res = new ApiResponse();
            res.setMessage("Commande placée avec succès !");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        } catch (PayPalRESTException e) {
            throw new PayPalException(e.getMessage());
        } catch (UtilisateurException e) {
            throw new RuntimeException(e);
        }
    }
}
