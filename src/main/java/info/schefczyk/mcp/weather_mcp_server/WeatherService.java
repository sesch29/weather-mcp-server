package info.schefczyk.mcp.weather_mcp_server;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class WeatherService {

    private final RestClient restClient;
    @Value("${openweathermapapikey}")
    private String appId;

    public WeatherService() {
        restClient = RestClient.builder()
                .defaultHeader("Accept", "application/geo+json")
                .build();
    }

    @PostConstruct
    public void init() {
        System.out.println("App ID: " + appId);
    }

    @Tool(description = "Get weather forecast for a specific latitude/longitude (format like '49.640556' and '8.278889')")
    public String getWeatherForecastByLocation(
            @ToolParam(description = "Latitude in form like '51.5073219'") String latitude,   // Latitude coordinate
            @ToolParam(description = "Longitude in form like '0.1276474'") String longitude   // Longitude coordinate
    ) {
        var uri = UriComponentsBuilder
                .fromUriString("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", appId)
                .build()
                .toUriString();

        System.out.println("Computed URI: " + uri);

        var response = restClient
                .get()
                .uri(uri)
                .accept(APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);

//        var response = restClient
//                .get()
//                .uri("https://api.openweathermap.org/data/2.5/weather?lat={latitude}&lon={longitude}&appid={appId}", latitude, longitude, appId)
//                .accept(APPLICATION_JSON)
//                .retrieve()
//                .toEntity(String.class);
        System.out.println(response);
        return response.getBody();
    }

    /**
     * Coordinates by location name
     */
    @Tool(description = "Coordinates by location name. Use comma separation in scheme '{city name},{state code},{country code}'")
    public String getLocation(
            @ToolParam(description = "Search string in scheme '{city name},{state code},{country code}'") String searchQuery
    ) {
        var response = restClient
                .get()
                .uri("https://api.openweathermap.org/geo/1.0/direct?q={searchQuery}&limit=1&appid={appId}", searchQuery, appId)
                .accept(APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);
        System.out.println(response);
        System.out.println(response.getBody());
        return response.getBody();
    }

    // TODO more endpoints, see https://openweathermap.org/api/air-pollution
}