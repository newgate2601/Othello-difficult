package com.example.Othellodifficult.mapper;

import com.example.Othellodifficult.dto.DifficultRequest;
import com.example.Othellodifficult.dto.DifficultResponse;
import com.example.Othellodifficult.entity.DifficultEntity;
import org.springframework.stereotype.Component;

public class DifficultMapper {
    public static DifficultEntity getEntityFromInput(DifficultRequest request){
        return new DifficultEntity(null, request.getAmount());
    }

    public static DifficultResponse getResponseFromEntity(DifficultEntity entity){
        return new DifficultResponse(entity.getId(), entity.getAmount());
    }
}
