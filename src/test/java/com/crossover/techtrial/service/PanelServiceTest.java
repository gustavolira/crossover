package com.crossover.techtrial.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.model.Panel;

import net.bytebuddy.utility.RandomString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PanelServiceTest {
	
	@Autowired
	private PanelService panelService;
	
	@Test
	public void testAllDailyElectricityFromYesterday() {
		Panel panel = new Panel();
		String serial = new RandomString(16).nextString();
		
		panel.setBrand("Test");
		panel.setLatitude(new BigDecimal("25.000000"));
		panel.setLongitude(new BigDecimal("12.000000"));
		panel.setSerial(serial);
		panelService.register(panel);
		
		Panel found = panelService.findBySerial(serial);
		
		assertNotNull(found);
		assertThat(found.getBrand()).isEqualTo("Test");
		assertThat(found.getLatitude()).isEqualTo("25");
		assertThat(found.getLongitude()).isEqualTo("12");
		assertThat(found.getSerial()).isEqualTo(serial);
		
	}

}
