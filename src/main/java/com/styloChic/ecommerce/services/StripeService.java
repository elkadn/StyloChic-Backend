package com.styloChic.ecommerce.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.api.key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent createPaymentIntent(Double amount, String currency, String description,
                                             Long commandeId, String successUrl, String cancelUrl)
            throws StripeException {
        // Convertir le montant en cents (Stripe travaille en centimes)
        long amountInCents = (long) (amount * 100);

        // Créer les paramètres du PaymentIntent
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency(currency.toLowerCase())
                .setDescription(description)
                .putMetadata("commandeId", commandeId.toString())
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .setReturnUrl(successUrl)
                .setConfirm(true)
                .build();

        // Créer le PaymentIntent
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return paymentIntent;
    }

    public PaymentIntent confirmPayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        return paymentIntent;
    }

    public Map<String, String> createCheckoutSession(Double amount, String currency,
                                                     String description, Long commandeId,
                                                     String successUrl, String cancelUrl)
            throws StripeException {
        // Convertir le montant en cents
        long amountInCents = (long) (amount * 100);

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", List.of("card"));
        params.put("line_items", List.of(Map.of(
                "price_data", Map.of(
                        "currency", currency.toLowerCase(),
                        "unit_amount", amountInCents,
                        "product_data", Map.of(
                                "name", "Commande #" + commandeId,
                                "description", description
                        )
                ),
                "quantity", 1
        )));
        params.put("mode", "payment");
        params.put("success_url", successUrl + "?session_id={CHECKOUT_SESSION_ID}&commandeId=" + commandeId);
        params.put("cancel_url", cancelUrl);
        params.put("metadata", Map.of("commandeId", commandeId.toString()));

        Session session = Session.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("id", session.getId());
        response.put("url", session.getUrl());

        return response;
    }
}