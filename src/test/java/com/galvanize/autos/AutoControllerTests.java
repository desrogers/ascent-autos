package com.galvanize.autos;

public class AutoControllerTests {
    /*
        GET /api/autos
            /api/autos - Returns list of all autos in the database
            /api/autos - 204 if no autos exist in db

            /api/autos?color={var} - returns all cars with var color
            /api/autos?color={var} - 204 if no autos exist in db with var color

            /api/autos?make={var} - returns all cars with var make
            /api/autos?make={var} - 204 if no autos exist in db with var make

            api/autos?color={var1}&make={var2} - returns all cars with var1 color and var2 make
            api/autos?color={var1}&make={var2} - 204 if no autos exist in db with var1 color and var2 make

        POST /api/autos
            /api/autos - adds an auto to the database and returns the car's details
            /api/autos - 400 bad request

        GET /api/autos/{vin}
            /api/autos/{vin} - returns the car's detail with the matching vin
            /api/autos/{vin} - 204 if no car with matching vin exists


        PATCH /api/autos/{vin}
            /api/autos/{vin} - updates and returns car's detail with new information
            /api/autos/{vin} - 204 if no car with matching vin exists
            /api/autos/{vin} - 400 bad request returns error message

        DELETE /api/autos/{vin}
            /api/autos/{vin} - 202 if car successfully deleted
            /api/autos/{vin} - 204 if no car with matching vin exists

     */

}
