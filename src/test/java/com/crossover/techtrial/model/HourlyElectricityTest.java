package com.crossover.techtrial.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.Test;

public class HourlyElectricityTest {
	
	@Test
	public void testEqualsAndHashCode() {
		
		LocalDateTime now = LocalDateTime.now();
		Random random = new Random();
		long generatedElectricity = random.nextLong();
		long id = random.nextLong();
		
		HourlyElectricity hourlyElectricity1 = createHourlyElectricity(now, generatedElectricity, id);
		HourlyElectricity hourlyElectricity2 = createHourlyElectricity(now, generatedElectricity, id);
		
		assertEquals(hourlyElectricity1, hourlyElectricity2);
		assertTrue( hourlyElectricity1.hashCode()==hourlyElectricity2.hashCode() );
		
	}
	
	public HourlyElectricity createHourlyElectricity(LocalDateTime now,long generatedElectricity, long id) {
		HourlyElectricity hourlyElectricity = new HourlyElectricity();
		
		
		hourlyElectricity.setGeneratedElectricity(generatedElectricity);
		hourlyElectricity.setReadingAt(now);
		hourlyElectricity.setId(id);
		return hourlyElectricity;
	}

}
