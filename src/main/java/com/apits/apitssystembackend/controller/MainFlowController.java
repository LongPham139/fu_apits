package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.custom.CandidateCustom.CandidateCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.Special_SkillsCustomResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.CustomResponseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("main-flow")
@RequiredArgsConstructor
@Tag(name = "Main Flow Controller")
public class MainFlowController {
    private final CustomResponseService customResponseService;

}
