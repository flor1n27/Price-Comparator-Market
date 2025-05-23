package org.example;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//Spring Framework

@RestController
@RequestMapping("/")
public class PriceComparatorController {

    @PostMapping("/basket-monitoring")
    public JSONObject getBasketMonitoring(@RequestBody List<String> basket) {              //a POST HTTP Request with the basket transmitted in the Request Body
        return BasketMonitoring.basket_monitoring(basket.toArray(new String[0]));          //example of basket: ["lapte zuzu", "iaurt grecesc", "cașcaval", "ciocolată neagră 70%", "piept pui"]
    }

    @GetMapping("/best-discounts")
    public Map<String,Float> getBestDiscounts(){                               //Get request, the data is returned by just accessing the specified path
        return BestDiscounts.best_discounts();                                 //no extra params are needed
    }

    @GetMapping("/new-discounts")
    public JSONObject getNewDiscounts() {                                      //Get request, the data is returned by just accessing the specified path
        return NewDiscounts.new_discounts();                                   //no extra params are needed
    }

    @GetMapping("/value-per-unit/{product}")
    public JSONObject getValuePerUnit(@PathVariable String product){           //Get request with specified path + product name
        return ValuePerUnit.get_best_value_per_unit(product);                  //example: http://localhost:8080/value-per-unit/biscuiți cu unt
    }

    @PostMapping("/product-target-price")
    public JSONObject getProductTargetPrice(@RequestParam String product, Float price){          //POST HTTP Request with the product_name and price specified in the params
        return CustomPriceAlert.get_product_target_price(product,price);                         //example: http://localhost:8080/product-target-price?product=lapte zuzu&price=10
    }


}