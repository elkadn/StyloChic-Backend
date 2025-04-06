package com.styloChic.ecommerce.responses;

public class BaseImageProduitResponse {

    private Long id;
    private String image;

    public BaseImageProduitResponse() {
    }

    public BaseImageProduitResponse(Long id, String image) {
        this.id = id;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
