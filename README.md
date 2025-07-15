Dispatch Planning - Matches delivery orders with available vehicles based on distance and capacity.

Weather Information - Retrieves and caches weather data for a given Indian PIN code using OpenWeather API.


1. Dispatch Service
   
DispatchServiceImpl handles the planning of deliveries:

Save Orders: Persists a list of delivery orders.

Save Vehicles: Persists a list of vehicles with location and capacity.

Generate Dispatch Plan: Assigns one optimal order per vehicle based on:

Vehicle capacity

Nearest distance (using Haversine formula)

Output: List of DispatchPlanResponseDTO objects, including vehicle ID, assigned order, load, and distance.

2. Weather Info Service
   
WeatherInfoServiceImpl handles weather retrieval and storage:

Get Weather Data by PIN Code and Date:

Checks local DB first.

If not found, calls OpenWeather API (Geo decoding + Weather).

Saves result to DB for caching.

Geo Decoding: Converts Indian PIN code to latitude and longitude.

Stores:

Weather data (WeatherData)

Location info (Pincodes)



