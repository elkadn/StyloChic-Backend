package com.styloChic.ecommerce.responses;

public class ImageProduitClientResponse {

    private Long id;
    private String image;

    public ImageProduitClientResponse() {
    }

    public ImageProduitClientResponse(Long id, String image) {
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
