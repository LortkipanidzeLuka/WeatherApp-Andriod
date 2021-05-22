package ge.llortkipanidze.weatherapp

data class HourlyForecast(val main: WeatherDetails, val weather: List<WeatherMainData>, val dt:Long)
data class ForecastModel(val list: List<HourlyForecast>)