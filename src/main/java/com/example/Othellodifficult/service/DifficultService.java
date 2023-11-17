package com.example.Othellodifficult.service;

import com.example.Othellodifficult.dto.DifficultRequest;
import com.example.Othellodifficult.dto.DifficultResponse;
import com.example.Othellodifficult.entity.DifficultEntity;
import com.example.Othellodifficult.mapper.DifficultMapper;
import com.example.Othellodifficult.repository.DifficultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class DifficultService {
    private final DifficultRepository difficultRepository;

    public List<DifficultResponse> getList(){
        return difficultRepository.findAll().stream()
                .map(DifficultMapper::getResponseFromEntity)
                .sorted(Comparator.comparing(DifficultResponse::getAmount))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DifficultResponse> exchange(List<DifficultRequest> requestList){
        difficultRepository.deleteAll();
        List<DifficultEntity> difficultEntities = requestList.stream()
                .map(DifficultMapper::getEntityFromInput)
                .collect(Collectors.toList());
        difficultRepository.saveAll(difficultEntities);

        return difficultEntities.stream()
                .map(DifficultMapper::getResponseFromEntity)
                .sorted(Comparator.comparing(DifficultResponse::getAmount))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DifficultResponse> reset(){
        difficultRepository.deleteAll();
        List<DifficultEntity> difficultEntities = new ArrayList<>();
        difficultEntities.add(
                DifficultEntity.builder()
                        .amount(1)
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .amount(2)
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .amount(3)
                        .build()
        );
        difficultRepository.saveAll(difficultEntities);
        return difficultEntities.stream()
                .map(DifficultMapper::getResponseFromEntity)
                .sorted(Comparator.comparing(DifficultResponse::getAmount))
                .collect(Collectors.toList());
    }
}
