package com.example.volume.controller;

import com.example.volume.service.VolumeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VolumeController {

    private final VolumeService volumeService;

    public VolumeController(VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @GetMapping("/volume/**")
    public String listVolume() {
        return volumeService.listVolume();
    }

}
