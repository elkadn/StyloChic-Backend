package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.exceptions.InspirationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Inspiration;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.repositories.InspirationRepository;
import com.styloChic.ecommerce.repositories.ProduitRepository;
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
import java.util.UUID;

@Service
public class InspirationServiceImplementation implements InspirationService{

    @Autowired
    private InspirationRepository inspirationRepository;

    @Autowired
    private ProduitService produitService;

    @Value("${file.upload-dir}")
    private String repUpload;

    @Override
    public Inspiration ajouterInspiration(Long produitId, MultipartFile image, String commentaire) throws InspirationException, ProduitException,Exception {
        Produit produit = produitService.chercherProduit(produitId);

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

        return inspirationRepository.save(inspiration);
    }

    private String enregistrerImage(MultipartFile image) throws IOException {
        String imageName = UUID.randomUUID().toString() + "." + avoirExtention(image);
        Path path = Paths.get(repUpload, imageName);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return path.toString();
    }

    private String avoirExtention(MultipartFile image) {
        String fileName = image.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
