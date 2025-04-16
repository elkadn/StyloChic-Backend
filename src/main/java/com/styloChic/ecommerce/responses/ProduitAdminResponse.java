package com.styloChic.ecommerce.responses;

import com.styloChic.ecommerce.models.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ProduitAdminResponse extends BaseProduitResponse{

    private String admin;
    private LocalDateTime dateAjout;
    private LocalDateTime dateModification;
    private double prixAchat;
    private Long fournisseurId;

    private Long categorieId;
    private Long couleurId;

    public ProduitAdminResponse() {
    }

    public ProduitAdminResponse(Long id, String titre, String description, double tva, double prixVenteHT, double prixVenteTTC, double prixVenteTTCReduit, double pourcentageReduction, String categorieParente, String categorieMoyenne, String categorieBase, int quantiteEnStock, String imagePrincipale, String saison, String conseilEntretien, String nomCouleur, Set<Taille> tailles, List<ImageProduit> imagesProduit, List<Vote> votes, List<Avis> avis, List<Inspiration> inspirations, int total_votes, int total_avis, String admin, LocalDateTime dateAjout, LocalDateTime dateModification, double prixAchat, Long fournisseurId, Long couleurId, Long categorieId) {
        super(id,titre,description,tva,prixVenteHT,prixVenteTTC,prixVenteTTCReduit,pourcentageReduction,categorieParente,categorieMoyenne,categorieBase,quantiteEnStock,imagePrincipale,saison,conseilEntretien,nomCouleur,tailles,imagesProduit,votes,avis,inspirations,total_votes,total_avis);
        this.admin = admin;
        this.dateAjout = dateAjout;
        this.dateModification = dateModification;
        this.prixAchat = prixAchat;
        this.fournisseurId = fournisseurId;
        this.couleurId = couleurId;
        this.categorieId = categorieId;
    }



    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public Long getCouleurId() {
        return couleurId;
    }

    public void setCouleurId(Long couleurId) {
        this.couleurId = couleurId;
    }
}
