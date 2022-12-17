Master: [![CI](https://github.com/Br0mm/SoftwareEngineeringLabs/actions/workflows/default.yml/badge.svg?branch=master)]
(https://github.com/Br0mm/SoftwareEngineeringLabs/actions/workflows/default.yml)

Develop: [![CI](https://github.com/Br0mm/SoftwareEngineeringLabs/actions/workflows/default.yml/badge.svg?branch=develop)]
(https://github.com/Br0mm/SoftwareEngineeringLabs/actions/workflows/default.yml)

# TimestampApi
Api to get timestamp
## How to run
Clone repository:
```console
git clone https://github.com/Br0mm/SoftwareEngineeringLabs.git
```
Change directory to project directory
```
cd SoftwareEngineeringLabs
```
Build docker image:
```console
docker build -t timestampapi .
```
Run docker image:
```console
docker run -p 8080:8080 timestampapi
```
## How to use
1. To get current timestamp in ISO format in UTC timezone
```
http://localhost:8080/timestamp
```
2. To get current timestamp in given format use parameter ```format```, for example:
```
http://localhost:8080/timestamp?format=yyyy-MM-dd HH-mm
```
3. To get current timestamp in given time zone use parameter ```time_zone```, for example:
```
http://localhost:8080/timestamp?time_zone=Europe/Moscow
http://localhost:8080/timestamp?format=YYYY-MM-dd HH-mm&time_zone=Cuba
```
4. To format given timestamp in given format (by default: ISO) and time zone (by default: UTC) use 
parameter ```timestamp``` with timestamp in milliseconds, for example:
```
http://localhost:8080/timestamp?timestamp=1391547061000
http://localhost:8080/timestamp?format=YYYY-MM-dd%20HH:mm&timestamp=1391547061000
http://localhost:8080/timestamp?time_zone=Cuba&timestamp=1391547061000
http://localhost:8080/timestamp?format=YYYY-MM-dd%20HH-mm-ss&time_zone=Europe/Moscow&timestamp=1391547061000
```
5. To convert formatted timestamp to timestamp in milliseconds use ```http://localhost:8080/convertTimestamp``` with all 
3 parameters:
   1. ```timestamp``` to give formatted timestamp
   2. ```format``` to give format of the timestamp
   3. ```time_zone``` to give timezone of the timestamp

   For example:
```
http://localhost:8080/convertTimestamp?format=yyyy-MM-dd HH-mm-ss&time_zone=UTC&timestamp=2014-02-04 23-51-01
```