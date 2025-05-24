# Price-Comparator-Market

-> a Java-based application built with **Spring Boot** that allows users to monitor product prices to find the cheapest prices, detect the best discounts, new discounts and receive custom price alerts using data from CSV files. It’s structured for modular clarity and is easily extendable for various price tracking needs.The project follows a standard Maven layout.

# Project Structure

PriceComparatorServer.java: The main entry point of the Spring Boot application.
PriceComparatorController.java: contains the core logic and handles the endpoints.
BasketMonitoring.java: handles the user basket and splits the products into a shopping list for price efficiency.
BestDiscounts.java: compares the data from each csv and returns the products with the highest discount percentage.
NewDiscounts.java: checks if the data was added at maximum a day ago and returns the products data if the condition is True.
ValuePerUnit.java: calculates the value of price divided by the quantity. It works for different measurment units, such as: kg, g, piece, ml, L.
CustomPriceAlert.java: performs a search of the product chosen by the user and returns data based on the price that was set as well by the user.
CsvReader.java: Reads the data from CSV files and returns it as a String Array, which is then processed row by row.
CommonUtils.java: contains methods that are used in the other classes: get_csv_date (important for NewDiscounts.java), select_store, get_product_details,get_discounted_product_details


# Tech Stack

**Java 17**
**Spring Boot** - framework
**Maven** - build tool
**OpenCSV** for CSV parsing
**json-simple** for JSON handling


# Build the Application

-navigate where you want to save the project:
e.g.: cd C:\JavaProjects

-clone the repository:
git clone "https://github.com/flor1n27/Price-Comparator-Market"

- open the Project in your IDE.

- to run the application use the "PriceComparatorServer.java" file

# INFOs

- the CSVs need to be in the resources directory and added to the their variables in the CSVReader.java file.
- the Filename format of the CSV needs to be respected:  storename_date.csv (e.g., lidl_2025-05-08.csv) and storename_discountdate.csv (e.g., lidl_discount_2025-05-08.csv) 

# Testing the Endpoints
- can be done by using Postman
- the prefix of the url is: "http://localhost:8080" followed by Endpoint.

# Get Endpoints

"/best-discounts" - no params needed
"/new-discounts" - no params needed
"/value-per-unit/{product}" - need to specify the product in the path variable product (e.g.: http://localhost:8080/value-per-unit/biscuiți cu unt)

# Post Endpoints

"/basket-monitoring" - in the Request Body, select raw and insert the basket as a string array (e.g.: ["lapte zuzu", "iaurt grecesc", "piept pui"])
"/product-target-price" - in the Params section add the keys: product and price and their desired value (e.g.: http://localhost:8080/product-target-price?product=lapte zuzu&price=10)
