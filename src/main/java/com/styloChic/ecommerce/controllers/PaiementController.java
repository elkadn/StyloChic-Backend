package com.styloChic.ecommerce.controllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.PayPalException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Commande;
import com.styloChic.ecommerce.models.DetailsPaiement;
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


    @PostMapping("/paypal/ajouter/{commandeId}")
    public ResponseEntity<PaiementUrlResponse> creerLinkPaiement(@PathVariable Long commandeId) throws CommandeException, PayPalException {

        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);
        try {
            String urlAnnulation = "http://localhost:3000/paymentCanceled";
            String urlSuccess = "http://localhost:3000/payment/"+commandeId;

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

    @GetMapping("/paypal/success")
    public ResponseEntity<ApiResponse> paiementSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId, @RequestParam("commandeId") Long commandeId)
            throws CommandeException, PayPalException {

        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);
        System.out.println("1");


        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {

                if (commande.getDetailsPaiement() == null) {
                    commande.setDetailsPaiement(new DetailsPaiement());
                }
                commande.getDetailsPaiement().setPaypalPaymentId(paymentId);
                commande.getDetailsPaiement().setStatusPaiement("Complet");
                commande.getDetailsPaiement().setMethodePaiement("PayPal");
                commande.getDetailsPaiement().setPaypalPayerId(payerId);
                commande.setStatutCommande("En cours de préparation");
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


    @PostMapping("/stripe/ajouter/{commandeId}")
    public ResponseEntity<PaiementUrlResponse> creerPaiementStripe(@PathVariable Long commandeId)
            throws Exception {

        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);
        try {
            String urlSuccess = "http://localhost:3000/payment/success";
            String urlAnnulation = "http://localhost:3000/paymentCanceled";

            Map<String, String> session = stripeService.createCheckoutSession(
                    commande.getMontantReduit(),
                    "USD",
                    "Paiement commande #" + commandeId,
                    commandeId,
                    urlSuccess,
                    urlAnnulation
            );

            PaiementUrlResponse reponse = new PaiementUrlResponse();
            reponse.setPaiement_liaison_id(session.get("id"));
            reponse.setPaiement_liaison_url(session.get("url"));

            return new ResponseEntity<>(reponse, HttpStatus.CREATED);
        } catch (StripeException e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/stripe/success")
    public ResponseEntity<ApiResponse> stripePaiementSuccess(
            @RequestParam("sessionId") String sessionId,
            @RequestParam("commandeId") Long commandeId)
            throws Exception {

        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);

        try {
            Session session = Session.retrieve(sessionId);
            PaymentIntent paymentIntent = PaymentIntent.retrieve(session.getPaymentIntent());
            if ("succeeded".equals(paymentIntent.getStatus())) {
                if (commande.getDetailsPaiement() == null) {
                    commande.setDetailsPaiement(new DetailsPaiement());
                }

                commande.getDetailsPaiement().setStripePaymentIntentId(paymentIntent.getId());
                commande.getDetailsPaiement().setStatusPaiement("Complet");
                commande.getDetailsPaiement().setMethodePaiement("Stripe");
                commande.setStatutCommande("En cours de préparation");
                commande.getDetailsPaiement().setStripeClientSecret(paymentIntent.getClientSecret());
                commandeRepository.save(commande);
            }

            panierService.viderPanier(commande.getUtilisateur().getId());
            ApiResponse res = new ApiResponse();
            res.setMessage("Commande placée avec succès via Stripe !");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        } catch (StripeException e) {
            throw new Exception(e.getMessage());
        } catch (UtilisateurException e) {
            throw new RuntimeException(e);
        }
    }
}

