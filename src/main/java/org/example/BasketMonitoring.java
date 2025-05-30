package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class BasketMonitoring {
    public static JSONObject basket_monitoring(String[] basket) {

        JSONObject shopping_list = new JSONObject();        //to return the shopping_list
        JSONArray lidl_array = new JSONArray();             //declaring a JSONArray for every store to store the products and be able to assign them to a key
        JSONArray kaufland_array = new JSONArray();
        JSONArray profi_array = new JSONArray();
        JSONArray no_results = new JSONArray();

        for (String product_name : basket) {                //going through every product in the basket

            Float min_price = null;                         //reassigning the min_price at the start of the loop to reset it for every product
            String product_store = "";
            LinkedHashMap<String, Object> product_details = new LinkedHashMap<>();          //to maintain the order of the product details (keys and values)
            product_name = CommonUtils.convert_characters(product_name).toLowerCase();      //converting romanian letter (e.g. ă to a) and letter to lower case

            for (String csv_file : CsvReader.current_week_offers) {                         //going through every csv to search for the product and compare the price
                List<String[]> data = CsvReader.readAllDataAtOnce(csv_file);                //to read the csv
                assert data != null;                                                        //to check that the data is not null

                for (String[] row : data) {                                                             //going through every row of the csv in search of the products from the basket
                    String csv_product_name = CommonUtils.convert_characters(row[1]).toLowerCase();
                    if (csv_product_name.strip().equals(product_name.strip())) {        //product_name index is [1], strip method is used to remove extra spaces
                        float product_price = Float.parseFloat(row[6]);                 //price index is [6]
                        if (min_price == null || product_price < min_price) {
                            min_price = product_price;
                            product_store = CommonUtils.select_store(csv_file);         //selecting the store based on the name of the csv
                            product_details = CommonUtils.get_product_details(row);     //to get the details of the product
                        }
                    }
                }
            }
            if (Objects.equals(product_store, "Lidl") && min_price != null) {        //adding the product details to their corresponded store
                lidl_array.add(product_details);
            }
            else if (Objects.equals(product_store, "Kaufland") && min_price != null) {
                kaufland_array.add(product_details);
            }
            else if (Objects.equals(product_store, "Profi") && min_price != null) {
                profi_array.add(product_details);
            }
            else no_results.add(product_name);
        }
        shopping_list.put("Lidl",lidl_array);                                       //adding the products details to their store/key
        shopping_list.put("Kaufland",kaufland_array);
        shopping_list.put("Profi",profi_array);
        shopping_list.put("No results found",no_results);

        return shopping_list;
    }
}
