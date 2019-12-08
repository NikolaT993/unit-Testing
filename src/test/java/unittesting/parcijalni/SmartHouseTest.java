package unittesting.parcijalni;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.time.DateTimeException;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import unittesting.parcijalni.CurrentTime;
import unittesting.parcijalni.SmartHouse;
import unittesting.parcijalni.TimeProvider;

public class SmartHouseTest {
	
	SmartHouse smartHouse;
	TimeProvider mockedTimeProvider;
	
	@BeforeMethod 
	public void setUp() {
		smartHouse = new SmartHouse();
		mockedTimeProvider = Mockito.mock(TimeProvider.class);
		smartHouse.setTimeProvider(mockedTimeProvider);
	}

	@Test(expectedExceptions = DateTimeException.class)
	public void testException() {
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(-1, 0, 0));
		smartHouse.GetTimeOfDay();
		
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(25, 0, 0));
		smartHouse.GetTimeOfDay();
	}
	
	@Test
	public void testNight() {
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(0, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Night");
		
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(5, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Night");
	}
	
	@Test
	public void testMorning() {
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(6, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Morning");
		
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(11, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Morning");
	}
	
	@Test
	public void testNoon() {
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(12, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Noon");
		
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(17, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Noon");
	}
	
	@Test
	public void testEvening() {
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(18, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Evening");
		
		when(mockedTimeProvider.getCurrentTime()).thenReturn(new CurrentTime(24, 0, 0));
		assertEquals(smartHouse.GetTimeOfDay(), "Evening");
	}
}
