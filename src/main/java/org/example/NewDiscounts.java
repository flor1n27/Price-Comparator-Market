package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;

public class NewDiscounts {
    public static JSONObject new_discounts(){

        JSONObject new_discounts_list = new JSONObject();
        JSONArray lidl_array = new JSONArray();
        JSONArray kaufland_array = new JSONArray();
        JSONArray profi_array = new JSONArray();

        for (String csv_file : CsvReader.stores_discounts) {                                    //going through each csv from the string array that contains the csv names
            List<String[]> data = CsvReader.readAllDataAtOnce(csv_file);                        //reading the csv data
            assert data != null;
            LocalDate current_date = LocalDate.now();                                           //getting the current date
            LocalDate csv_date = CommonUtils.get_csv_date(csv_file);                            //getting the date of the csv

            if (!(csv_date ==null)){                                                            //to check that the format of the csv contains the date
                double days_difference = ChronoUnit.DAYS.between(csv_date, current_date);       //calculating the difference of the dates

                if (days_difference<=1){                                                        //only adding data if the days difference is <=1
                    for(String[] row:data){

                        String store = CommonUtils.select_store(csv_file);
                        LinkedHashMap<String, Object> product_details = CommonUtils.get_discounted_product_details(row);    //getting the product data

                        if (store.equals("Lidl")){                                              //the data is added to their corresponded store
                            lidl_array.add(product_details);
                        }
                        else if(store.equals("Kaufland")){
                            kaufland_array.add(product_details);
                        }
                        else profi_array.add(product_details);
                    }
                }
            }
        }
        if (!lidl_array.isEmpty()) {                                // checking that the arrays are empty or not to determine what should be assigned to each store
            new_discounts_list.put("Lidl", lidl_array);
        }
        else new_discounts_list.put("Lidl","0 new discounts");
        if (!kaufland_array.isEmpty()) {
            new_discounts_list.put("Kaufland", kaufland_array);
        }
        else new_discounts_list.put("Kaufland","0 new discounts");
        if (!profi_array.isEmpty()) {
            new_discounts_list.put("Profi",profi_array);
        }
        else new_discounts_list.put("Profi","0 new discounts");

        return new_discounts_list;
    }
}
