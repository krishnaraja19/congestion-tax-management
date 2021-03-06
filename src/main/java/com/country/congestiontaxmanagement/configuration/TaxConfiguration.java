package com.country.congestiontaxmanagement.configuration;

import com.country.congestiontaxmanagement.service.DataLoader;
import com.country.congestiontaxmanagement.service.TollDataReader;
import com.country.congestiontaxmanagement.service.TollStreamingImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;


@Configuration
public class TaxConfiguration {

    @Autowired
    DataLoader dataLoader;

    @Autowired
    TollDataReader tollDataReader;

    @Autowired
    TollStreamingImplementation tollStreamingImplementation;

    @Value("${datasource.file.tax.rule.path}")
    private Resource taxRulePath;

    @Value("${datasource.file.path}")
    private Resource filePath;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadDataBeforeStartup() {
        //dataLoader.cityLoadData();
        dataLoader.getTaxRuleForGothenburg(taxRulePath);
        dataLoader.writeSpecialHolidays();
        dataLoader.vehicleTypeLoadData();
        tollDataReader.readData(filePath);

    }


}
