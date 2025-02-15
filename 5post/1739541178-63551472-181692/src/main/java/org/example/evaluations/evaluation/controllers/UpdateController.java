package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.CheckUpdateResponseDto;
import org.example.evaluations.evaluation.dtos.UpdateRequestDto;
import org.example.evaluations.evaluation.dtos.UpdateResponseDto;
import org.example.evaluations.evaluation.models.CheckUpdateStatus;
import org.example.evaluations.evaluation.models.UpdateStatus;
import org.example.evaluations.evaluation.models.Version;
import org.example.evaluations.evaluation.services.IDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private IDeployService deployService;

    //Implement your API here
    @PostMapping
    public ResponseEntity<?> updateIntall(@RequestBody UpdateRequestDto updateRequestDto){
        System.out.println(" updateRequestDto123>>> " + updateRequestDto);
        if(updateRequestDto != null) System.out.println(" updateRequestDto123>>> " + updateRequestDto.toString());
        try{
           if(updateRequestDto.getAppId() == null || updateRequestDto.getVersion() == null)
               throw new IllegalArgumentException ("Either AppId or version is missing");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        UUID appId = updateRequestDto.getAppId();
        String version = updateRequestDto.getVersion();
        UpdateStatus updateStatus = deployService.update(appId,version);
        UpdateResponseDto updateResponseDto = new UpdateResponseDto();
        updateResponseDto.setStatus(updateStatus);
        updateResponseDto.setAppId(appId);
        Version newVersion = deployService.getLatestVersion(appId, version);
        updateResponseDto.setVersion(newVersion);
        return new ResponseEntity<>(updateRequestDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/installedVersion/{version}")
    public  CheckUpdateResponseDto getUpdate(@PathVariable("id") UUID id, @PathVariable("version") String version ){
        Version version1 = deployService.getLatestVersion(id, version);
        CheckUpdateResponseDto checkUpdateResponseDto = new CheckUpdateResponseDto();
        if(version1 != null) {
            checkUpdateResponseDto.setVersion(version1);
            checkUpdateResponseDto.setStatus(CheckUpdateStatus.UPDATE_AVAILABLE);
        }
        else{
            checkUpdateResponseDto.setStatus(CheckUpdateStatus.NO_UPDATE);
        }
        checkUpdateResponseDto.setAppId(id);

        return checkUpdateResponseDto;
    }
}
