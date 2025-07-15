Dispatch Planning: Matches delivery orders with vehicles based on proximity (using the Haversine formula) and vehicle capacity.

Weather Information: Retrieves and caches weather data for Indian PIN codes using the OpenWeather API.
Data Persistence: Stores delivery orders, vehicle details, and weather data in a local database.
Geo Decoding: Converts Indian PIN codes to latitude and longitude for weather data retrieval.

Architecture
The application follows a modular design with two primary services:

Dispatch Service: Handles order and vehicle management, generating optimized dispatch plans.
Weather Info Service: Manages weather data retrieval, caching, and storage.

Both services interact with a local database for data persistence and use external APIs (OpenWeather) for weather data.
Modules


1. Dispatch Service
The DispatchServiceImpl class provides the core functionality for dispatch planning:

Save Orders: Persists a list of delivery orders to the database.
Save Vehicles: Stores vehicle details, including location and capacity.
Generate Dispatch Plan:
Assigns one optimal delivery order per vehicle.
Considers vehicle capacity and nearest distance (calculated using the Haversine formula).
Outputs a list of DispatchPlanResponseDTO objects containing:
Vehicle ID
Assigned order details
Load information
Distance to delivery location





2. Weather Info Service
The WeatherInfoServiceImpl class manages weather data operations:

Get Weather Data by PIN Code and Date:
Checks the local database for cached weather data.
If not found, retrieves data from the OpenWeather API (Geo decoding + Weather).
Caches the result in the database for future queries.


Geo Decoding: Converts Indian PIN codes to latitude and longitude.
Data Storage:
Weather data stored in the WeatherData entity.
Location information stored in the Pincodes entity.



Technologies Used

Java: Core programming language.
Spring Framework: For dependency injection and service management.
OpenWeather API: For weather data retrieval.
Haversine Formula: For calculating distances between locations.
Maven: For dependency management and build automation.
Local Database: For persisting orders, vehicles, and weather data.

Setup and Installation

Clone the Repository:git clone https://github.com/sdhananjaya1012/FreightFox.git



Install Dependencies:Ensure you have Maven installed, then run:mvn clean install


Configure OpenWeather API:
Obtain an API key from OpenWeather.
Add the API key to the application.yml :openweather.api.key=your_api_key_here




Set Up the Database:
Configure the database connection in application.properties.
Example:spring.datasource.url=jdbc:mysql://localhost:3306/freightfox
spring.datasource.username=root
spring.datasource.password=your_password


Run database migrations 
saved in DB scripts


Run the Application:mvn spring-boot:run
