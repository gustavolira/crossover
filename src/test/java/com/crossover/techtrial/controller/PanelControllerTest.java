package com.crossover.techtrial.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.PanelRepository;
import com.crossover.techtrial.service.HourlyElectricityService;

/**
 * PanelControllerTest class will test all APIs in PanelController.java.
 * 
 * @author Crossover
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PanelControllerTest {

	MockMvc mockMvc;

	@Mock
	private PanelController panelController;
	
	@Autowired
	private PanelRepository panelRepository;
	
	@Autowired
	private TestRestTemplate template;
	
	@Autowired
	HourlyElectricityService hourlyElectricityService;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(panelController).build();
	}
	
	@Test
	public void testPanelShouldBeRegistered() throws Exception {
		RandomString randomString = new RandomString(16);
		
		String json = "{\"serial\": \""+randomString.nextString()+"\", \"longitude\": \"54.123232\","
				+ " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }";
		
		HttpEntity<Object> panel = getHttpEntity(json);
		ResponseEntity<Panel> response = template.postForEntity("/api/register", panel, Panel.class);
		Assert.assertEquals(202, response.getStatusCode().value());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

	@Test
	public void testHourlyElectricityShoudBeSaved() {
		RandomString randomString = new RandomString(16);
		String serial = randomString.nextString();
		HourlyElectricity hourlyElectricity = new HourlyElectricity();
		Panel panel = new Panel();
		panel.setBrand("Test");
		panel.setLatitude(new BigDecimal("20.999456"));
		panel.setLongitude(new BigDecimal("30.990000"));
		panel.setSerial(serial);
		Panel panelSaved = panelRepository.save(panel);

		hourlyElectricity.setGeneratedElectricity(501020L);
		hourlyElectricity.setReadingAt(LocalDateTime.now());
		hourlyElectricity.setPanel(panelSaved);
		hourlyElectricityService.save(hourlyElectricity);

		String url = "/api/panels/" + serial + "/hourly";
		ResponseEntity<HourlyElectricity> response = template.getForEntity(url, HourlyElectricity.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}	
	


	@Test
	public void testAllDailyElectricityFromYesterday() throws Exception {
		RandomString randomString = new RandomString(16);
		String serial = randomString.nextString();
		HourlyElectricity hourlyElectricity = new HourlyElectricity();
		Panel panel = new Panel();
		panel.setBrand("Test");
		panel.setLatitude(new BigDecimal("20.890000"));
		panel.setLongitude(new BigDecimal("30.800000"));
		panel.setSerial(serial);
		
		Panel panelSaved = panelRepository.save(panel);

		hourlyElectricity.setGeneratedElectricity(501020L);
		hourlyElectricity.setReadingAt(LocalDateTime.now());
		hourlyElectricity.setPanel(panelSaved);

		String url = "/api/panels/" + serial + "/daily";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}	
	

}
