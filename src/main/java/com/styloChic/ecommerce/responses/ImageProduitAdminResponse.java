package com.styloChic.ecommerce.responses;


import java.time.LocalDateTime;

public class ImageProduitAdminResponse extends BaseImageProduitResponse{

    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private String admin;

    public ImageProduitAdminResponse() {
    }

    public ImageProduitAdminResponse(Long id, String image, LocalDateTime dateCreation, LocalDateTime dateModification, String admin) {
        super(id,image);
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.admin = admin;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
