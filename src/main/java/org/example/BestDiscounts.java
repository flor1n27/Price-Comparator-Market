package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestDiscounts {
    public static Map<String, Float> best_discounts() {

        Map<String, Float> highest_discounts = new HashMap<>();             //to store the product_name and the discount percentage

        for (String csv_file : CsvReader.current_week_discounts) {

            List<String[]> data = CsvReader.readAllDataAtOnce(csv_file);    //to read the csv data
            assert data != null;                                            //to check that the data is not null

            for (String[] row : data) {
                String product_name = row[1];                                    //product_name index in the csv is 1
                float discount_percentage = Float.parseFloat(row[8]);            //discount percentage index is 8, converting it to Float to compare the value later

                if (!highest_discounts.containsKey(product_name)) {              //in case the product was not found before, product and percentage are added to the Map
                    highest_discounts.put(product_name, discount_percentage);
                } else {
                    float previous_discount = highest_discounts.get(product_name);      //in case the product was found before, we get hold of the previous discount
                    if (previous_discount < discount_percentage) {                      //if the discount percentage is bigger, it replaces the old one
                        highest_discounts.replace(product_name, discount_percentage);
                    }
                }
            }
        }
        return highest_discounts;
    }
}
