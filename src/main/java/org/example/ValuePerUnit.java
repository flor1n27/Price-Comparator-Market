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
                String csv_product = row[1];
                if (Objects.equals(csv_product, user_product)){

                    float package_quantity = Float.parseFloat(row[4]);
                    String package_unit = row[5];
                    float price = Float.parseFloat(row[6]);

                    if (Objects.equals(package_unit, "g") ) {
                        package_quantity = package_quantity / 1000;
                        package_unit = "kg";
                    }
                    else if(Objects.equals(package_unit, "ml")){
                        package_quantity = package_quantity / 1000;
                        package_unit = "L";
                    }

                    float value_per_unit = price/package_quantity;

                    LinkedHashMap<String, Object> product_details = CommonUtils.get_product_details(row);
                    product_details.put("value per unit",value_per_unit+" "+ row[7]+"/"+package_unit);

                    String store = CommonUtils.select_store(csv_file);

                    product_data.put(store,product_details);
                }
            }
        }
        return product_data;
    }
}
