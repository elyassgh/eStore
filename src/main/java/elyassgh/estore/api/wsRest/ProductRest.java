package elyassgh.estore.api.wsRest;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Services.ProductImageService;
import elyassgh.estore.api.Services.ProductObjectService;
import elyassgh.estore.api.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eStoreApi/product")
public class ProductRest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductObjectService productObjectService;
    @Autowired
    ProductImageService productImageService;

    @PostMapping("/")
    public int save(@RequestParam(name = "sku") String sku,
                    @RequestParam(name = "name") String name,
                    @RequestParam(name = "brand") String brand,
                    @RequestParam(name = "type") String type,
                    @RequestParam(name = "category") String category,
                    @RequestParam(name = "desc") String description,
                    @RequestParam(name = "phrase") String phrase)
    {
        return productService.save(sku,name,brand,type,category,description,phrase);
    }

    @PutMapping("/setThumbImage")
    public int updateThumbImage(@RequestParam(name = "sku") String sku, @RequestParam(name = "imgId") Long imgId) {
        return productService.updateThumbImage(sku, productImageService.findById(imgId));
    }

    @PutMapping("/updateDescOrPhrase")
    public int updateDescAndPhrase(@RequestParam(name = "sku") String sku,
                                   @RequestParam(name = "desc") String description,
                                   @RequestParam(name = "phrase") String phrase) {
        return productService.updateDescAndPhrase(sku, description, phrase);
    }

    @GetMapping(value = "/find",params = "sku")
    public Product findBySKU(@RequestParam(name = "sku") String sku) {
        return productService.findBySKU(sku);
    }

    @GetMapping(value = "/find",params = "brand")
    public List<Product> findProductsByBrand(@RequestParam(name = "brand") String brand) {
        return productService.findProductsByBrand(brand);
    }

    @GetMapping(value = "/find",params = "category")
    public List<Product> findProductsByCategory(@RequestParam(name = "category") String category) {
        return productService.findProductsByCategory(category);
    }

    @GetMapping(value = "/find",params = {"category","brand"})
    public List<Product> findProductByCatAndBrand(
            @RequestParam(name = "category") String category,
            @RequestParam(name= "brand") String brand) {
        return productService.findProductByCatAndBrand(category, brand);
    }

    @GetMapping(value = "/find",params = {"category","brand","type"})
    public List<Product> findProductsByCatAndBrandAndType(
            @RequestParam(name = "category") String category,
            @RequestParam(name= "brand") String brand,
            @RequestParam(name = "type") String type)
    {
        return productService.findProductsByCatAndBrandAndType(category, brand, type);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "sku") String sku)
    {
        return productService.delete(productService.findBySKU(sku));
    }




}
