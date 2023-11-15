package com.proyecto_turismo_ufpso.turismo.profile.controller;


import com.proyecto_turismo_ufpso.turismo.profile.dto.ProfileDto;
import com.proyecto_turismo_ufpso.turismo.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;


    @GetMapping("/all")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        List<ProfileDto> profiles = profileService.getAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDto> findProfileById(@PathVariable UUID profileId) {
        ProfileDto profile = profileService.getProfileById(profileId);
        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ProfileDto> saveProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto savedProfile = profileService.createProfile(profileDto.getProfileName());
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    @PutMapping("/update/{profileId}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable UUID profileId, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.updateProfile(profileId, profileDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID profileId) {
        profileService.deleteProfile(profileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}