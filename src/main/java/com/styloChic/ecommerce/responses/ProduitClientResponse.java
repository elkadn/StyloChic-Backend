package com.styloChic.ecommerce.responses;

import com.styloChic.ecommerce.models.*;
import java.util.List;
import java.util.Set;

public class ProduitClientResponse extends BaseProduitResponse{



    public ProduitClientResponse() {
    }

    public ProduitClientResponse(Long id, String titre, String description, double tva, double prixVenteHT, double prixVenteTTC,double prixVenteTTCReduit,double pourcentageReduction, String categorieParente, String categorieMoyenne, String categorieBase, int quantiteEnStock, String imagePrincipale, String saison, String conseilEntretien, String nomCouleur, Set<Taille> tailles, List<ImageProduit> imagesProduit, List<Vote> votes, List<Avis> avis,List<Inspiration> inspirations, int total_votes, int total_avis) {
        super(id,titre,description,tva,prixVenteHT,prixVenteTTC,prixVenteTTCReduit,pourcentageReduction,categorieParente,categorieMoyenne,categorieBase,quantiteEnStock,imagePrincipale,saison,conseilEntretien,nomCouleur,tailles,imagesProduit,votes,avis,inspirations,total_votes,total_avis);
    }


}
