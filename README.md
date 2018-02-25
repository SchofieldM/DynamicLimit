# DynamicLimit

A dynamic speed limit system driven by AI 
to be employed by cities.

Uses current, physical conditions compared against historical conditions 
to determine an appropriate speed limit
for every segment of road in a city.

This would be especially useful for self-driving cars of the future.

# Future Implementation
A car would send its GPS coordinates to a city's cenralized server.
This server would hold a table of appropriate speeds and return to the
car how fast it should go based on the road segment it is on.
The server maintains this data using current and historic data
regarding accidents, weather and time of day.
