package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvReader {

    static String[] stores = {"lidl_2025-05-01.csv", "kaufland_2025-05-01.csv", "profi_2025-05-01.csv"};
    static String[] stores_discounts = {"lidl_discounts_2025-05-01.csv", "kaufland_discounts_2025-05-01.csv", "profi_discounts_2025-05-22.csv"};

    public static List<String[]> readAllDataAtOnce(String file) {
        try {

            InputStream inputStream = CsvReader.class.getClassLoader().getResourceAsStream(file);   // getting the file from the resources root

            if (inputStream == null) {
                throw new FileNotFoundException("File not found in resources: " + file);            // in case there is not a file found with the searched name
            }

            InputStreamReader reader = new InputStreamReader(inputStream);                          // to get the characters (text) inside the file
            CSVReader csvReader = new CSVReaderBuilder(reader)                                      // creates a builder for CSVReader using the created "reader".
                    .withSkipLines(1)                                                               // 1 to Skip header, 0 to keep the header
                    .build();                                                                       // constructs the CSVReader instance with the configured options

            return csvReader.readAll();                                                             // reads the data and returns it

        } catch (Exception e) {                                                                     // to catch all the exceptions
            return null;
        }
    }
}
