package info.schefczyk.mcp.weather_mcp_server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Example Data in good response:
 * @formatter:off
 * <pre>
 *     {
 *   "coord": {
 *     "lon": 8.3597,
 *     "lat": 49.6356
 *   },
 *   "weather": [
 *     {
 *       "id": 804,
 *       "main": "Clouds",
 *       "description": "overcast clouds",
 *       "icon": "04d"
 *     }
 *   ],
 *   "base": "stations",
 *   "main": {
 *     "temp": 292.01,
 *     "feels_like": 290.84,
 *     "temp_min": 289.25,
 *     "temp_max": 292.17,
 *     "pressure": 1014,
 *     "humidity": 34,
 *     "sea_level": 1014,
 *     "grnd_level": 998
 *   },
 *   "visibility": 10000,
 *   "wind": {
 *     "speed": 1.34,
 *     "deg": 59,
 *     "gust": 5.36
 *   },
 *   "clouds": {
 *     "all": 95
 *   },
 *   "dt": 1746716207,
 *   "sys": {
 *     "type": 2,
 *     "id": 2006221,
 *     "country": "DE",
 *     "sunrise": 1746676333,
 *     "sunset": 1746730433
 *   },
 *   "timezone": 7200,
 *   "id": 2806142,
 *   "name": "Worms",
 *   "cod": 200
 * }
 *
 * </pre>
 * @formatter:on
 *
 * remember: "Die Celsius-Skala der Temperatur ist so definiert, dass die Temperatur in Grad Celsius gemessen gegenüber der Temperatur in Kelvin um exakt 273,15 verschoben ist"
 * 0 °C (273,15 K)
 * 100 °C (373,15 K).
 */
class WeatherServiceTest {

    @Test
    void happyFlow() {
        // Arrange
        WeatherService weatherService = new WeatherService();

        // Act
        String result = weatherService.getWeatherForecastByLocation("49.640556", "8.278889");

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Worms"));
//        assertTrue(result.contains("Wind speed"));
    }
}