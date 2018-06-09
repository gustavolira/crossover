package com.crossover.techtrial.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import net.bytebuddy.utility.RandomString;

public class PanelTest {

	@Test
	public void testEqualsAndHashCode() {
		RandomString randomString = new RandomString(16);
		String serial = randomString.nextString();
		Panel panel1 = createPanel(serial);
		Panel panel2 = createPanel(serial);
		
		String panelToString = "Panel [id=1, serial="+serial+", longitude=10.000000, latitude=10.000000, brand=TEST]";
		
		assertEquals(panel1.toString(), panelToString);
		assertEquals(panel1, panel2);
		assertTrue( panel1.hashCode() == panel2.hashCode() );
	}

	private Panel createPanel(String serial) {
		Panel panel = new Panel();
		panel.setBrand("TEST");
		panel.setLatitude(new BigDecimal("10.000000"));
		panel.setLongitude(new BigDecimal("10.000000"));
		panel.setSerial(serial);
		panel.setId(1L);

		return panel;
	}

}
