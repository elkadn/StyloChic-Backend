package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.InspirationDTO;
import com.styloChic.ecommerce.exceptions.CouleurException;
import com.styloChic.ecommerce.exceptions.InspirationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Inspiration;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.InspirationRepository;
import com.styloChic.ecommerce.repositories.ProduitRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InspirationServiceImplementation implements InspirationService{

    @Autowired
    private InspirationRepository inspirationRepository;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Value("${file.upload-dir}")
    private String repUpload;

    @Override
    public Inspiration ajouterInspiration(Long produitId, MultipartFile image, String commentaire,String jwt) throws ProduitException, UtilisateurException,Exception {
        Produit produit = produitService.chercherProduit(produitId);
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        if (produit == null){
            throw new ProduitException("Produit non trouvé avec id : "+produitId);
        }

        String imagePath = enregistrerImage(image);

        Inspiration inspiration = new Inspiration();
        inspiration.setImage(imagePath);
        inspiration.setCommentaire(commentaire);
        inspiration.setDateAjout(LocalDateTime.now());
        inspiration.setVisibilite(false);
        inspiration.setProduit(produit);
        inspiration.setUtilisateur(utilisateur);

        return inspirationRepository.save(inspiration);
    }

    private String enregistrerImage(MultipartFile image) throws IOException {



        String imageName = UUID.randomUUID().toString() + "." + avoirExtention(image);
        Path path = Paths.get(repUpload, imageName);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return "inspirations/" + imageName;
    }

    private String avoirExtention(MultipartFile image) {
        String fileName = image.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Utilisateur verifierAdmin(String jwt) throws InspirationException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new InspirationException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }


    @Override
    public List<InspirationDTO> recupererToutesInspirationsAvecTitreProduit() {
        return inspirationRepository.findAll().stream()
                .map(inspiration -> {
                    String fileName = Paths.get(inspiration.getImage()).getFileName().toString();
                    String imageUrl = "http://localhost:6069/inspirations/" + fileName;

                    return new InspirationDTO(
                            inspiration.getId(),
                            imageUrl,
                            inspiration.getCommentaire(),
                            inspiration.getDateAjout(),
                            inspiration.isVisibilite(),
                            inspiration.getProduit().getTitre(),
                            inspiration.getUtilisateur().getNom() +" "+ inspiration.getUtilisateur().getPrenom()
                    );
                })
                .collect(Collectors.toList());
    }


    @Override
    public InspirationDTO changerVisibiliteInspiration(Long inspirationId, boolean visibilite,String jwt) throws InspirationException, UtilisateurException {
        verifierAdmin(jwt);
        Inspiration inspiration = inspirationRepository.findById(inspirationId)
                .orElseThrow(() -> new InspirationException("Inspiration non trouvée avec id : " + inspirationId));

        inspiration.setVisibilite(visibilite);
        Inspiration savedInspiration = inspirationRepository.save(inspiration);

        return convertToDTO(savedInspiration);
    }

    private InspirationDTO convertToDTO(Inspiration inspiration) {
        return new InspirationDTO(
                inspiration.getId(),
                inspiration.getImage(),
                inspiration.getCommentaire(),
                inspiration.getDateAjout(),
                inspiration.isVisibilite(),
                inspiration.getProduit().getTitre(),
                inspiration.getUtilisateur().getNom() +" "+ inspiration.getUtilisateur().getPrenom()
        );
    }
}
