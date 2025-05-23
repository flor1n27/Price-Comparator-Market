package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestDiscounts {
    public static Map<String, Float> best_discounts() {

        Map<String, Float> highest_discounts = new HashMap<>();

        for (String csv_file : CsvReader.stores_discounts) {
            List<String[]> data = CsvReader.readAllDataAtOnce(csv_file);
            assert data != null;

            for (String[] row : data) {
                String product = row[1];
                float discount_percentage = Float.parseFloat(row[8]);

                if (!highest_discounts.containsKey(product)) {
                    highest_discounts.put(product, discount_percentage);
                } else {
                    float previous_discount = highest_discounts.get(product);
                    if (previous_discount < discount_percentage) {
                        highest_discounts.replace(product, discount_percentage);
                    }
                }
            }
        }
        return highest_discounts;
    }
}
