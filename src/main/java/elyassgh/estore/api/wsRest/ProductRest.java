package elyassgh.estore.api.wsRest;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductImage;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Services.ProductImageService;
import elyassgh.estore.api.Services.ProductObjectService;
import elyassgh.estore.api.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public int saveProduct(@RequestParam(name = "sku") String sku, @RequestParam(name = "name") String name,
            @RequestParam(name = "brand") String brand, @RequestParam(name = "type") String type,
            @RequestParam(name = "category") String category, @RequestParam(name = "desc") String description,
            @RequestParam(name = "phrase") String phrase) {
        return productService.save(sku, name, brand, type, category, description, phrase);
    }

    @PutMapping("/setThumbImage")
    public int updateThumbImage(@RequestParam(name = "sku") String sku, @RequestParam(name = "imgId") Long imgId) {
        return productService.updateThumbImage(sku, productImageService.findById(imgId));
    }

    @PutMapping("/updateDescOrPhrase")
    public int updateDescAndPhrase(@RequestParam(name = "sku") String sku,
            @RequestParam(name = "desc") String description, @RequestParam(name = "phrase") String phrase) {
        return productService.updateDescAndPhrase(sku, description, phrase);
    }

    @GetMapping(value = "/find", params = "sku")
    public Product findBySKU(@RequestParam(name = "sku") String sku) {
        return productService.findBySKU(sku);
    }

    @GetMapping(value = "/find", params = "brand")
    public List<Product> findProductsByBrand(@RequestParam(name = "brand") String brand) {
        return productService.findProductsByBrand(brand);
    }

    @GetMapping(value = "/find", params = "category")
    public List<Product> findProductsByCategory(@RequestParam(name = "category") String category) {
        return productService.findProductsByCategory(category);
    }

    @GetMapping(value = "/find", params = { "category", "brand" })
    public List<Product> findProductByCatAndBrand(@RequestParam(name = "category") String category,
            @RequestParam(name = "brand") String brand) {
        return productService.findProductByCatAndBrand(category, brand);
    }

    @GetMapping(value = "/find", params = { "category", "brand", "type" })
    public List<Product> findProductsByCatAndBrandAndType(@RequestParam(name = "category") String category,
            @RequestParam(name = "brand") String brand, @RequestParam(name = "type") String type) {
        return productService.findProductsByCatAndBrandAndType(category, brand, type);
    }

    @DeleteMapping("/delete")
    public int deleteProduct(@RequestParam(name = "sku") String sku) {
        return productService.delete(productService.findBySKU(sku));
    }

    @PostMapping(value = "/uploadImage", params = { "image", "poId" })
    public int uploadImg(@RequestParam(name = "image") MultipartFile uploadedImage,
            @RequestParam(name = "poId") Long productObjectId) throws IOException {
        return productImageService.upload(uploadedImage, productObjectId);
    }

    @GetMapping(value = "/getImage")
    public ProductImage getImage(@RequestParam(name = "poId") Long productObjectId,
            @RequestParam(name = "imgName") String name) throws IOException {
        return productImageService.getImage(productObjectId, name);
    }

    @GetMapping(value = "/getImages")
    public List<ProductImage> getImages(@RequestParam(name = "poId") Long productObjectId) throws IOException {
        return productImageService.getImages(productObjectId);
    }

    @DeleteMapping(value = "/deleteImg")
    public int deleteImg(@RequestParam(name = "imgName") Long id) {
        return productImageService.delete(id);
    }

    @PostMapping(value = "/creatObject")
    public int saveObject(
        @RequestParam(name = "sku") String sku,
        @RequestParam(name = "size") String size,
        @RequestParam(name = "colour") String colour,
        @RequestParam(name = "quantity") Integer quantity,
        @RequestParam(name = "price") Double price) {
        return productObjectService.save(sku, size, colour, quantity, price);
    }

    @GetMapping(value = "/findObject")
    public Optional<ProductObject> findPOById(
        @RequestParam(name = "poId") Long productObjectId) {
        return productObjectService.findPOById(productObjectId);
    }

    @GetMapping(value = "/findObjects")
    public List<ProductObject> findPOsOfProduct(
        @RequestParam(name = "sku") String sku) {
        return productObjectService.findPOsOfProduct(sku);
    }

    @GetMapping(value = "/findObjectWhere")
    public List<ProductObject> findPOsOfProductAndQtyGE(
        @RequestParam(name = "sku") String sku,
        @RequestParam(name = "qty") Integer quantity) {
        return productObjectService.findPOsOfProductAndQtyGE(sku, quantity);
    }

    @GetMapping(value = "/findSoldObjects")
    public List<ProductObject> findSoldPOs() {
        return productObjectService.findSoldPOs();
    }

    @GetMapping(value = "/findSoonSoldObjects")
    public List<ProductObject> findSoonSoldPOs() {
        return productObjectService.findSoonSoldPOs();
    }

    @GetMapping(value = "/findObjectsBetween")
    public List<ProductObject> findAvailablePOs(
        @RequestParam(name = "start") Double start,
        @RequestParam(name = "end") Double end) {
        return productObjectService.findAvailablePOs(start, end);
    }

    @DeleteMapping(value = "/deleteObject")
    public int deleteObject(@RequestParam(name = "poId") Long productObjectId) {
        return productObjectService.delete(productObjectId);
    }

}
