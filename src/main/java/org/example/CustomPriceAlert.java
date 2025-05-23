package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class CustomPriceAlert {
    public static JSONObject get_product_target_price(String target_product, Float target_price) {

        JSONObject product_data = new JSONObject();     //to store the returned data

        for (String csv_file : CsvReader.stores) {
            List<String[]> data = CsvReader.readAllDataAtOnce(csv_file);
            assert data != null;
            String store;

            JSONArray data_array = new JSONArray();     //only an array is needed in this case, since here is only 1 product

            for (String[] row : data) {
                String csv_product = row[1];
                if (Objects.equals(csv_product, target_product)) {
                    float csv_price = Float.parseFloat(row[6]);
                    store = CommonUtils.select_store(csv_file);
                    if (csv_price <= target_price) {    //comparing the csv_price with the customer/user price and if it returns true the data is stored

                        LinkedHashMap<String, Object> product_details = CommonUtils.get_product_details(row);
                        data_array.add(product_details);
                        System.out.println(data_array);
                        product_data.put(store, data_array);
                    }
                    else {
                        data_array.add("No results found.");
                        product_data.put(store, data_array);
                    }
                }
            }
        }
        return product_data;
    }
}
