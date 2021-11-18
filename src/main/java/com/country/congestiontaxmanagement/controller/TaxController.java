package com.country.congestiontaxmanagement.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0")
public class TaxController {
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/vehicle/{id}")
    public String getTaxInvoice(){
        return "20 SEK";
    }

}
