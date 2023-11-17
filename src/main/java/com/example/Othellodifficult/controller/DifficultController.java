package com.example.Othellodifficult.controller;

import com.example.Othellodifficult.dto.DifficultRequest;
import com.example.Othellodifficult.dto.DifficultResponse;
import com.example.Othellodifficult.entity.DifficultEntity;
import com.example.Othellodifficult.mapper.DifficultMapper;
import com.example.Othellodifficult.service.DifficultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/difficult")
@AllArgsConstructor
public class DifficultController {

    private final DifficultService difficultService;

    @GetMapping
    @Operation(summary = "Lấy danh sách độ khó")
    public List<DifficultResponse> getList(){
        return difficultService.getList();
    }

    @PutMapping
    @Operation(summary = "Chỉnh sửa độ khó")
    public List<DifficultResponse> exchange(@RequestBody List<DifficultRequest> requestList){
        return difficultService.exchange(requestList);
    }

    @PutMapping
    @Operation(summary = "Reset ộ khó")
    public List<DifficultResponse> reset(){
        return difficultService.reset();
    }
}
