# smart-thermostat-management-system

# Info
I have developed Smart thermostat management system, where user can add,view,update and delete thermostats under his control (by userId).
I have implemented in the Thermostat constructor random number generator in range of 5-35 to generate and throw random value of temperature to the server.
Added updateCondition() method - if temperature exceeds threshold value, device will be marked as CRITICAL, otherwise NORMAL.
Added PasswordEncoder with BCryptPasswordEncoder.


# Dependencies
In this project I have used those dependencies: Lombok, Spring Web, Spring Data JPA, PostgreSQL Driver, Spring Security and other dependencies for Jwt

# How to use

# Register
First of all we have to register new user - POST localhost:3000/api/v1/auth/register  and write body, for example: 
{
    "firstname": "nikoloz",
    "lastname": "kiladze",
    "email": "blackwhissh@gmail.com",
    "password": "1234"
}

# Authenticate
Next we have to authenticate added user - GET localhost:3000/api/v1/auth/authenticate , write body with "email" and "password" and then copy active_token and use 
for other request authentications as bearer.

# Add New Thermostat
After this user is eligible to add new thermostat - POST localhost:3000/api/v1/thermostat and write body

# Get All Thermostats
If we add multiple thermostat, we can display all of them - GET localhost:3000/api/v1/thermostat  , but it will display thermostats only added by current user

# Get Thermostat
Therefore we can display single device too - GET localhost:3000/api/v1/thermostat/{thermostatId}

# Update Thermostat
By the way, adding threshold to the thermostat when we are creating the new one is optional. If we want to add threshold or change device name later
it's easy peasy with the following request - PUT localhost:3000/api/v1/thermostat/5?threshold=16

# Delete Thermostat
Thus we can delete thermostat with given thermostatId - DELETE localhost:3000/api/v1/thermostat/{thermostatId}
