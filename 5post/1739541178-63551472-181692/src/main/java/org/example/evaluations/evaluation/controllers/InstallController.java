package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.InstallRequestDto;
import org.example.evaluations.evaluation.dtos.InstallResponseDto;
import org.example.evaluations.evaluation.models.InstallStatus;
import org.example.evaluations.evaluation.services.IDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/install")
public class InstallController {

    @Autowired
    private IDeployService deployService;

    //Implement your API here
    @PostMapping
    public ResponseEntity<?> addInstall(@RequestBody InstallRequestDto installRequestDto)  {

        try {
            if(installRequestDto.getAppId() == null){
                throw new IllegalAccessException("AppId is null");
            }
        } catch (Exception e) {
          return new ResponseEntity<>(e, HttpStatusCode.valueOf(400));
        }

        InstallStatus installStatus = deployService.install(installRequestDto.getAppId());
        InstallResponseDto installResponseDto = new InstallResponseDto();
        installResponseDto.setAppId(installRequestDto.getAppId());
        installResponseDto.setStatus(installStatus);
        return new ResponseEntity< >(installResponseDto    , HttpStatus.OK);

    }
}
