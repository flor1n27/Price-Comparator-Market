package org.example;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PriceComparatorController {

    @PostMapping("/basket-monitoring")
    public JSONObject getBasketMonitoring(@RequestBody List<String> basket) {
        return BasketMonitoring.basket_monitoring(basket.toArray(new String[0]));
    }

    @GetMapping("/best-discounts")
    public Map<String,Float> getBestDiscounts(){
        return BestDiscounts.best_discounts();
    }

    @GetMapping("/new-discounts")
    public JSONObject getNewDiscounts() {
        return NewDiscounts.new_discounts();
    }

    @GetMapping("/value-per-unit/{product}")
    public JSONObject getValuePerUnit(@PathVariable String product){
        return ValuePerUnit.get_best_value_per_unit(product);
    }

    @PostMapping("/product-target-price")
    public JSONObject getProductTargetPrice(@RequestParam String product, Float price){
        return CustomPriceAlert.get_product_target_price(product,price);
    }


}