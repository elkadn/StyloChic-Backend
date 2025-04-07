package com.styloChic.ecommerce.dtos;

public class PubliciteDTO {

    String imageUrl;
    String structure;


    public PubliciteDTO() {
    }

    public PubliciteDTO(String imageUrl, String structure) {
        this.imageUrl = imageUrl;
        this.structure = structure;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }
}
