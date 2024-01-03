package com.dien_dan.controller;

import com.dien_dan.model.Account;
import com.dien_dan.model.Area;
import com.dien_dan.model.Location;
import com.dien_dan.service.IAccountService;
import com.dien_dan.service.IAreaService;
import com.dien_dan.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {
    @Autowired
    ILocationService locationService;

    @Autowired
    IAreaService areaService;

    @Autowired
    IAccountService accountService;

    @GetMapping("{idUser}")
    public Location getLocationByUser(@PathVariable int idUser){
        return locationService.findByIdUser(idUser);
    }

    @PostMapping
    public Location save(@RequestBody Location location){
        Location locationUser = locationService.findByIdUser(location.getAccount().getId());
        if (locationUser != null){
            location.setId(locationUser.getId());
        }
        locationService.save(location);
        List<Area> areas = areaService.getAll();
        Area area = null;
        for (Area a:areas) {
            double km = haversineDistance(location.getLatitude(),location.getLongitude(),a.getLatitude(),a.getLongitude());
            if (km < 5) {
                area = a;
                break;
            }
        }
        if (area == null){
            area = new Area(0,location.getLatitude(),location.getLongitude());
            areaService.save(area);

        }
        Account account = accountService.findById(location.getAccount().getId());
        account.setArea(area);
        accountService.save(account);
        return location;
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final double earthRadius = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}
