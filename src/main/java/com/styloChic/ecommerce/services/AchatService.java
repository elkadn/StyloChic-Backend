package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.AchatDTO;
import com.styloChic.ecommerce.exceptions.AchatException;
import com.styloChic.ecommerce.requests.AchatRequest;

import java.util.List;

public interface AchatService {
    AchatDTO creerAchat(AchatRequest request, String jwt) throws Exception;
    AchatDTO chercherAchatParId(Long achatId, String jwt) throws AchatException;
    List<AchatDTO> historiqueAchatsFournisseur(Long fournisseurId, String jwt) throws AchatException;
    AchatDTO confirmerAchat(Long achatId, String jwt) throws Exception;
    AchatDTO receptionnerAchat(Long achatId, String jwt) throws Exception;
    AchatDTO annulerAchat(Long achatId, String jwt) throws Exception;
    List<AchatDTO> avoirTousAchats(String jwt) throws AchatException;
}



