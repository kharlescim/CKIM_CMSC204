package ckim_lab1_junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {

	private GradeBook g1;
	private GradeBook g2;
	
	@BeforeEach
	void setUp() throws Exception {
		g1 = new GradeBook(5);
		g2 = new GradeBook(5);
		g1.addScore(50.0);
		g1.addScore(75.0);
		g1.addScore(100.0);
		g1.addScore(25.0);
		g1.addScore(50.0);

		g2.addScore(100.0);
		g2.addScore(80.0);
		g2.addScore(60.0);
		g2.addScore(40.0);
		g2.addScore(20.0);
	}

	@AfterEach
	void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(g1.toString().equals("50.0 75.0 100.0 25.0 50.0 "));
		assertTrue(g2.toString().equals("100.0 80.0 60.0 40.0 20.0 "));
		assertEquals(5, g1.getScoresSize(), .0001);
		assertEquals(5, g2.getScoresSize(), .0001);
	}
	
	@Test
	void testSum() {
		assertEquals(300.0, g1.sum(), .0001);
		assertEquals(300.0, g2.sum(), .0001);
	}
	
	@Test
	void testMinimum() {
		assertEquals(25.0, g1.minimum(), .0001);
		assertEquals(20.0, g2.minimum(), .0001);
	}
	
	@Test
	void testFinalScore() {
		assertEquals(275.0, g1.finalScore(), .0001);
		assertEquals(280.0, g2.finalScore(), .0001);
	}

}
