package com.country.congestiontaxmanagement.service;

import com.country.congestiontaxmanagement.model.TollStreaming;
import com.country.congestiontaxmanagement.model.Vehicle;
import com.country.congestiontaxmanagement.repository.CityRepository;
import com.country.congestiontaxmanagement.repository.TollStreamingRepository;
import com.country.congestiontaxmanagement.repository.VehicleRepository;
import com.country.congestiontaxmanagement.repository.VehicleTypeRepository;
import com.country.congestiontaxmanagement.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TollDataReader {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    TollStreamingRepository tollStreamingRepository;

    public void readData(Resource filePath){
        List<TollStreaming> tollStreamingList = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new InputStreamReader(filePath.getInputStream()))) {
            tollStreamingList = csvReader.lines().skip(1).map(mapToTollStreamingData).collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Function<String, TollStreaming> mapToTollStreamingData = (line) -> {
        char quotes = '"';

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] data = line.split(";");

        var vehicleType = vehicleTypeRepository.findById(Long.parseLong(data[4])).get();
        var vehicle = vehicleRepository.findByVehicleNumber(Long.parseLong(data[1]));

        if(vehicle.isEmpty()){
            var vehicle1  = Vehicle.builder()
                    .vehicleNumber(Long.parseLong(data[1]))
                    .createDate(Constants.getLocalDateTime())
                    .updateDate(Constants.getLocalDateTime())
                    .vehicleType(vehicleType).build();
            vehicleRepository.saveAndFlush(vehicle1);
            var rawData =TollStreaming.builder()
                    .InOrOutTime(LocalDateTime
                            .parse(data[2].replaceAll(""+quotes,""),formatter))
                    .city(cityRepository.findById(Long.parseLong(data[3])).get())
                    .createDate(Constants.getLocalDateTime())
                    .updateDate(Constants.getLocalDateTime())
                    .vehicle(vehicle1).build();
            tollStreamingRepository.saveAndFlush(rawData);
            return rawData;
        }else{

            var rawData =TollStreaming.builder()
                    .InOrOutTime(LocalDateTime
                            .parse(data[2].replaceAll(""+quotes,""),formatter))
                    .city(cityRepository.findById(Long.parseLong(data[3])).get())
                    .createDate(Constants.getLocalDateTime())
                    .updateDate(Constants.getLocalDateTime())
                    .vehicle(vehicle.get()).build();
            tollStreamingRepository.saveAndFlush(rawData);
            return rawData;
        }
    };
}
