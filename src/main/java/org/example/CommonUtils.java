package org.example;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class CommonUtils {

    public static LocalDate get_csv_date(String store_csv_name){    //as input the name of the csv will be passed

        StringBuilder csv_date = new StringBuilder();               //to store the csv name to be processed
        try {
            int starting_index = store_csv_name.indexOf('2');       //to get the index where the date begins in the name format of the csv

            for (int i = starting_index; i < starting_index + 10; i++) {   //the date has the length of 10 -> starting_index + 10
                csv_date.append(store_csv_name.charAt(i));                 //adding every character of the date from "starting_index" to "starting_index"+10
            }
            return LocalDate.parse(csv_date);                              //converts the string csv_date to a LocalDate format to be processed later
        } catch (Exception e) {
            return null;
        }
    }

    public static String select_store(String file) {

        String store;
                                                                    //based on the format of the csv the name of the store will begin at index 0
        if (file.toLowerCase().charAt(0) == 'l') {                  //first letter will determine the store name
            store = "Lidl";
        } else if (file.toLowerCase().charAt(0) == 'k') {
            store = "Kaufland";
        } else {
            store = "Profi";
        }
        return store;
    }

    public static LinkedHashMap<String,Object> get_product_details(String[] row){

        LinkedHashMap<String, Object> product_details = new LinkedHashMap<>();          //the LinkedHashMap is used to maintain the order of the details
        product_details.put("product_id",row[0]);                                       //every key and value is according to the csv and added using "put"
        product_details.put("product_name",row[1]);
        product_details.put("product_category",row[2]);
        product_details.put("brand",row[3]);
        product_details.put("package_quantity",Float.parseFloat(row[4]));
        product_details.put("package_unit",row[5]);
        product_details.put("price",Float.parseFloat(row[6]));
        product_details.put("currency",row[7]);

        return product_details;
    }

    public static LinkedHashMap<String,Object> get_discounted_product_details(String[] row){

        LinkedHashMap<String, Object> product_details = new LinkedHashMap<>();       //the LinkedHashMap is used to maintain the order of the details
        product_details.put("product_id",row[0]);
        product_details.put("product_name",row[1]);
        product_details.put("brand",row[2]);
        product_details.put("package_quantity",Float.parseFloat(row[3]));
        product_details.put("package_unit",row[4]);
        product_details.put("product_category",row[5]);
        product_details.put("from_date",row[6]);
        product_details.put("to_date",row[7]);
        product_details.put("percentage_of_discount",Float.parseFloat(row[8]));
        return product_details;
    }

    public static String convert_characters(String word){
        word = word.replace("ă", "a").replace("â", "a")
                .replace("î", "i").replace("ș", "s").replace("ț", "t")
                .replace("Ă", "A").replace("Â", "A")
                .replace("Î", "I").replace("Ș", "S").replace("Ț", "T");
        return word;
    }
}
