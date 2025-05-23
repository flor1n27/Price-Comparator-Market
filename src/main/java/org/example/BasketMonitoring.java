package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class BasketMonitoring {
    public static JSONObject basket_monitoring(String[] basket) {

        JSONObject shopping_list = new JSONObject();
        JSONArray lidl_array = new JSONArray();
        JSONArray kaufland_array = new JSONArray();
        JSONArray profi_array = new JSONArray();

        for (String product_name : basket) {

            Float min_price = null;
            String product_store = "";
            LinkedHashMap<String, Object> product_details = new LinkedHashMap<>();

            for (String csv_file : CsvReader.stores) {
                List<String[]> data = CsvReader.readAllDataAtOnce(csv_file);
                assert data != null;

                for (String[] row : data) {                                         //parsing every row of the csv
                    if (Objects.equals(row[1], product_name)) {                     //product_name index
                        float product_price = Float.parseFloat(row[6]);             //price index
                        if (min_price == null || product_price < min_price) {
                            min_price = product_price;
                            product_store = CommonUtils.select_store(csv_file);

                            product_details = CommonUtils.get_product_details(row);
                        }
                    }
                }
            }
            if (Objects.equals(product_store, "Lidl") && min_price != null) {
                lidl_array.add(product_details);
            }
            else if (Objects.equals(product_store, "Kaufland") && min_price != null) {
                kaufland_array.add(product_details);
            }
            else if (Objects.equals(product_store, "Profi") && min_price != null) {
                profi_array.add(product_details);
            }
        }
        shopping_list.put("Lidl",lidl_array);
        shopping_list.put("Kaufland",kaufland_array);
        shopping_list.put("Profi",profi_array);
        return shopping_list;
    }
}
