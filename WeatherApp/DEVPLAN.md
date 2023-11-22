App starts with WeatherApp class, then it will instantiate the services required (API caller service, FileReaderService, ...) then it will collect the data in a viewModel (which is a class that represent the UI values like, current weather temperature etc..) and apply the viewModel to the user interface fields.

The app will have a single view with sidebar for locations with a search. 
In the main container, we have current weather in the top, then a horizontal container for hourly forecast, and finally another horizontal container for daily forecast.

We will use the functional approach to exception handling (capturing exception in the spot and returning a result type)

Code Style:
function have camelCase
classes have PascalCase
local variable have camelCase
folders are lowercase


Design?
