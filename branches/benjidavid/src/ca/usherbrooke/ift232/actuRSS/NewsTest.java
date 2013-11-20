package ca.usherbrooke.ift232.actuRSS;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NewsTest {

	News news;

	@Before
	public void setUp() throws Exception {
		news = new News("Vive la France", "http://edf.com",
				"Christian Jeanpierre", null,
				"c'est génial ils sont au Brésil", false, false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNews() {
		assertNotNull("L'instance a été créée", news);
	}

}