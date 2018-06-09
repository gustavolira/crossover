package com.crossover.techtrial.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class DailyElectricityTest {
	
	@Test
	public void shouldToStringBeReturnedAsAPIExpected() {
		LocalDate date = LocalDate.of(2018, 06, 9);
		DailyElectricity dailyElectricity = new DailyElectricity(date, 100L, 50.0, 10L, 20L);
		String APIREturn = "DailyElectricity [date=2018-06-09, sum=100, average=50.0, min=10, max=20]";
		assertThat(dailyElectricity.toString()).isEqualTo(APIREturn);
	}

}
