package com.country.congestiontaxmanagement;

import com.country.congestiontaxmanagement.dto.CongestionTax;
import com.country.congestiontaxmanagement.model.TollStreaming;
import com.country.congestiontaxmanagement.model.Vehicle;
import com.country.congestiontaxmanagement.repository.CityRepository;
import com.country.congestiontaxmanagement.repository.TollStreamingRepository;
import com.country.congestiontaxmanagement.repository.VehicleRepository;
import com.country.congestiontaxmanagement.repository.VehicleTypeRepository;
import com.country.congestiontaxmanagement.service.DataLoader;

import com.country.congestiontaxmanagement.utility.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
class CongestionTaxManagementApplicationTests {

	@Test
	void contextLoads() {
	}
}
