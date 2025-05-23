package org.example;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class ValuePerUnit {
    public static JSONObject get_best_value_per_unit(String user_product){

        JSONObject product_data = new JSONObject();

        for (String csv_file : CsvReader.stores) {
            List<String[]> data = CsvReader.readAllDataAtOnce(csv_file);
            assert data != null;

            for (String[] row : data) {
                String csv_product = row[1];                                       //index of product_name = 1 in the csv
                if (Objects.equals(csv_product, user_product)){

                    float package_quantity = Float.parseFloat(row[4]);             //index of the package_quantity = 5
                    String package_unit = row[5];                                  //index of the package_unit = 5
                    float price = Float.parseFloat(row[6]);                        //index of the price = 6

                    if (Objects.equals(package_unit, "g") ) {                   //converting grams to kg
                        package_quantity = package_quantity / 1000;
                        package_unit = "kg";
                    }
                    else if(Objects.equals(package_unit, "ml")){                //converting milliliters to liters
                        package_quantity = package_quantity / 1000;
                        package_unit = "L";
                    }

                    float value_per_unit = price/package_quantity;                                          //getting the price/per kg or /per L...
                    LinkedHashMap<String, Object> product_details = CommonUtils.get_product_details(row);
                    product_details.put("value per unit",value_per_unit+" "+ row[7]+"/"+package_unit);      //formatting to ex: "value per unit": "35.5 RON/kg"
                    String store = CommonUtils.select_store(csv_file);                                      //selecting the store to be used as a key
                    product_data.put(store,product_details);
                }
            }
        }
        return product_data;
    }
}
