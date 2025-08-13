package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.DashboardAdminService;
import com.parqueadero.parkplace.dto.DashboardAdminDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/dashboard-admin")
@RequiredArgsConstructor
public class DashboardAdminController {
    private final DashboardAdminService dashboardAdminService;

    @GetMapping()
    DashboardAdminDto obtenerDatosDashboard() {
        return dashboardAdminService.obtenerDatosDashboard();
    }

}
