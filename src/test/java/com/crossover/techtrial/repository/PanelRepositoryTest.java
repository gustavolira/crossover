package com.crossover.techtrial.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
public class PanelRepositoryTest {

	@Autowired
	private PanelRepository repository;
	
	@Test
	public void testPanelShouldBeFinded() {
		
		Panel panel = new Panel();
		String serial = new RandomString(16).nextString();
		
		panel.setBrand("XPTO");
		panel.setLatitude(new BigDecimal("10.000000"));
		panel.setLongitude(new BigDecimal("10.000000"));
		panel.setSerial(serial);
		
		repository.save(panel);
		
		Panel found = repository.findBySerial(serial);
		
		assertThat(found.getBrand()).isEqualTo("XPTO");
		assertThat(found.getLatitude()).isEqualTo("10");
		assertThat(found.getLongitude()).isEqualTo("10");
		assertThat(found.getSerial()).isEqualTo(serial);
		
		
	}

}
