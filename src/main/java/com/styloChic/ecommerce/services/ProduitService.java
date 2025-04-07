package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.exceptions.FournisseurException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.ImageProduit;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.requests.CreationProduitRequest;
import com.styloChic.ecommerce.responses.ImageProduitAdminResponse;
import com.styloChic.ecommerce.responses.ImageProduitClientResponse;
import com.styloChic.ecommerce.responses.ProduitAdminResponse;
import com.styloChic.ecommerce.responses.ProduitClientResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitService {
    public Produit creerProduit(CreationProduitRequest requete,String jwt) throws ProduitException, FournisseurException;

    public ProduitClientResponse chercherProduitParIdPourClient(Long id) throws ProduitException;

    public ProduitAdminResponse chercherProduitParIdPourAdmin(Long id,String jwt) throws ProduitException;


    public String supprimerProduit(Long idProduit, String jwt) throws ProduitException;

    public Page<ProduitClientResponse> avoirTousProduits(String categorie, List<String> couleurs, List<String> tailles, Integer prixMin, Integer prixMax, String trie, String stock, Integer numeroPage, Integer taillePage);
    public List<Produit> chercherTousProduits();

    void ajouterImageProduit(Long idProduit, String image,String jwt) throws ProduitException;

    public Page<ProduitClientResponse> chercherProduits(String requete, Integer NumeroPage, Integer TaillePage);

    public void supprimerImageProduit(Long idProduit, Long imageId,String jwt) throws ProduitException;

    public List<ImageProduitClientResponse> obtenirImagesProduitPourClient(Long idProduit) throws ProduitException;

    public List<ImageProduitAdminResponse> obtenirImagesProduitPourAdmin(Long idProduit,String jwt) throws ProduitException;

    public void modifierImageProduit(Long idProduit, Long imageId, String nouvelleImage,String jwt) throws ProduitException;


    public ImageProduitClientResponse avoirImageProduitAvecIdPourClient(Long idProduit, Long imageId) throws ProduitException;

    public ImageProduitAdminResponse avoirImageProduitAvecIdPourAdmin(Long idProduit, Long imageId, String jwt) throws ProduitException;

    public Produit miseAjourProduit(Long idProduit, CreationProduitRequest requete,String jwt) throws ProduitException,FournisseurException;

    public Produit chercherProduit(Long id) throws ProduitException;
}
