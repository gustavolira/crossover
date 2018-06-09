package com.crossover.techtrial.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.HourlyElectricityRepository;

import net.bytebuddy.utility.RandomString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HourlyElectricityRepositoryTest {

	@Autowired
	private HourlyElectricityRepository repository;

	@Test
	public void testHourlyElectricityShoudBeFinded() {

		// given
		RandomString randomString = new RandomString(16);
		String serial = randomString.nextString();
		HourlyElectricity hourlyElectricity = new HourlyElectricity();
		Panel panel = new Panel();
		panel.setBrand("Test");
		panel.setLatitude(new BigDecimal("20.000000"));
		panel.setLongitude(new BigDecimal("30.000000"));
		panel.setSerial(serial);
		panel.setId(99L);

		Random rand = new Random();
		long randomLong = rand.nextLong();
		
		LocalDateTime now = LocalDateTime.now();
		hourlyElectricity.setGeneratedElectricity(randomLong);
		hourlyElectricity.setReadingAt(now);
		repository.save(hourlyElectricity);

		// when
		List<HourlyElectricity> founds = repository.findByGeneratedElectricity(randomLong);

		// then
		assertThat(founds.get(0).getGeneratedElectricity()).isEqualTo(randomLong);
		
	}
}
