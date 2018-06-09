package com.crossover.techtrial.service;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.Panel;

import net.bytebuddy.utility.RandomString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DailyElectricityServiceTest {
	
	@Autowired
	private PanelService panelService;
	
	@Autowired
	private HourlyElectricityService hourlyElectricityService;
	
	@Test
	public void testAllDailyElectricityFromYesterday() {
		Panel panel = new Panel();
		String serial = new RandomString(16).nextString();
		
		panel.setBrand("XPTO");
		panel.setLatitude(new BigDecimal("10.000000"));
		panel.setLongitude(new BigDecimal("10.000000"));
		panel.setSerial(serial);
		
		panelService.register(panel);
		
		List<DailyElectricity> allAverageElectricity = hourlyElectricityService.getAllAverageElectricity(panel.getId());
		
		assertThat(allAverageElectricity.size(), greaterThan(0));
		
	}

}
