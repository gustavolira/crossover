package com.crossover.techtrial.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.PanelRepository;

import net.bytebuddy.utility.RandomString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HourlyElectricityServiceTest {

	@Autowired
	private HourlyElectricityService hourlyElectricityService;
	
	@Autowired
	private PanelRepository panelRepository;

	@Test
	public void testHourlyElectricityByPanelIdShouldBeFinded() {

		RandomString randomString = new RandomString(16);
		String serial = randomString.nextString();
		HourlyElectricity hourlyElectricity = new HourlyElectricity();
		Panel panel = new Panel();
		panel.setBrand("Test");
		panel.setLatitude(new BigDecimal("20.000000"));
		panel.setLongitude(new BigDecimal("30.000000"));
		panel.setSerial(serial);
		
		Panel panelSaved = panelRepository.save(panel);

		Random rand = new Random();
		long randomLong = rand.nextLong();
		
		LocalDateTime now = LocalDateTime.now();
		hourlyElectricity.setGeneratedElectricity(randomLong);
		hourlyElectricity.setReadingAt(now);
		hourlyElectricity.setPanel(panelSaved);
		
		HourlyElectricity hourlyElectricitySaved = hourlyElectricityService.save(hourlyElectricity);
		
		assertThat(hourlyElectricitySaved.getGeneratedElectricity()).isEqualTo(randomLong);
		assertThat(hourlyElectricitySaved.getPanel().getBrand()).isEqualTo("Test");
		assertThat(hourlyElectricitySaved.getPanel().getId()).isEqualTo(panelSaved.getId());
		assertThat(hourlyElectricitySaved.getPanel()).isEqualTo(panelSaved);
		
		Pageable pageable = PageRequest.of(0, 5);
		Page<HourlyElectricity> allHourlyElectricityByPanelId = hourlyElectricityService.getAllHourlyElectricityByPanelId(hourlyElectricitySaved.getPanel().getId(), pageable);
		
		assertThat(allHourlyElectricityByPanelId.getTotalElements(), greaterThan(0L));
		
	}
}
