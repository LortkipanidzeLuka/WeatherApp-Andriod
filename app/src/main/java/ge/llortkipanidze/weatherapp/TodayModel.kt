package ge.llortkipanidze.weatherapp

data class WeatherMainData(val main:String, val icon:String)

data class WeatherDetails(val temp: Double, val feels_like: Double, val pressure: Int, val humidity: Int)

data class TodayModel(val weather: List<WeatherMainData>, val main: WeatherDetails, val dt: Long)