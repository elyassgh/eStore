package elyassgh.estore.api.wsRest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductImage;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Services.ProductImageService;
import elyassgh.estore.api.Services.ProductObjectService;
import elyassgh.estore.api.Services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/eStoreApi/product")
@Api("Product Api Rest")

@CrossOrigin(origins = {"http://localhost"})
public class ProductRest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductObjectService productObjectService;
    @Autowired
    private ProductImageService productImageService;

    @ApiOperation("create new product")
    @PostMapping("/")
    public int saveProduct(@RequestParam(name = "sku") String sku, @RequestParam(name = "name") String name,
            @RequestParam(name = "brand") String brand, @RequestParam(name = "type") String type,
            @RequestParam(name = "category") String category, @RequestParam(name = "desc") String description,
            @RequestParam(name = "phrase") String phrase) {
        return productService.save(sku, name, brand, type, category, description, phrase);
    }

    @ApiOperation("set a thumb image to a product")
    @PutMapping("/setThumbImage")
    public int updateThumbImage(@RequestParam(name = "sku") String sku, @RequestParam(name = "imgId") Long imgId) {
        return productService.updateThumbImage(sku, productImageService.findById(imgId));
    }

    @ApiOperation("update description or app. phrase of a product")
    @PutMapping("/updateDescOrPhrase")
    public int updateDescAndPhrase(@RequestParam(name = "sku") String sku,
            @RequestParam(name = "desc") String description, @RequestParam(name = "phrase") String phrase) {
        return productService.updateDescAndPhrase(sku, description, phrase);
    }

    @ApiOperation("find all products")
    @GetMapping(value = "/findAll")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @ApiOperation("find a product by its sku")
    @GetMapping(value = "/find", params = "sku")
    public Product findBySKU(@RequestParam(name = "sku") String sku) {
        return productService.findBySKU(sku);
    }

    @ApiOperation("find prodcuts of a brand")
    @GetMapping(value = "/findbyc&b", params = "brand")
    public List<Product> findProductsByBrand(@RequestParam(name = "brand") String brand) {
        return productService.findProductsByBrand(brand);
    }

    @ApiOperation("find prodcuts of a category")
    @GetMapping(value = "/findbycat", params = "category")
    public List<Product> findProductsByCategory(@RequestParam(name = "category") String category) {
        return productService.findProductsByCategory(category);
    }

    @ApiOperation("find prodcuts of a category & a brand")
    @GetMapping(value = "/findbybrand", params = { "category", "brand" })
    public List<Product> findProductByCatAndBrand(@RequestParam(name = "category") String category,
            @RequestParam(name = "brand") String brand) {
        return productService.findProductByCatAndBrand(category, brand);
    }

    @ApiOperation("find prodcuts of a category & a brand & a type")
    @GetMapping(value = "/findbycbt", params = { "category", "brand", "type" })
    public List<Product> findProductsByCatAndBrandAndType(@RequestParam(name = "category") String category,
            @RequestParam(name = "brand") String brand, @RequestParam(name = "type") String type) {
        return productService.findProductsByCatAndBrandAndType(category, brand, type);
    }

    @ApiOperation("delete a product")
    @DeleteMapping("/delete")
    public int deleteProduct(@RequestParam(name = "sku") String sku) {
        return productService.delete(productService.findBySKU(sku));
    }

    @ApiOperation("upload an image of a product object")
    @PostMapping(value = "/uploadImage", params = { "image", "poId" })
    public int uploadImg(@RequestParam(name = "image") MultipartFile uploadedImage,
            @RequestParam(name = "poId") Long productObjectId) throws IOException {
        return productImageService.upload(uploadedImage, productObjectId);
    }

    @ApiOperation("get an image of a product object")
    @GetMapping(value = "/getImage")
    public ProductImage getImage(@RequestParam(name = "poId") Long productObjectId,
            @RequestParam(name = "imgName") String name) throws IOException {
        return productImageService.getImage(productObjectId, name);
    }

    @ApiOperation("get all images of a product objects")
    @GetMapping(value = "/getImages")
    public List<ProductImage> getImages(@RequestParam(name = "poId") Long productObjectId) throws IOException {
        return productImageService.getImages(productObjectId);
    }

    @ApiOperation("delete an image")
    @DeleteMapping(value = "/deleteImg")
    public int deleteImg(@RequestParam(name = "imgId") Long id) {
        return productImageService.delete(id);
    }

    @ApiOperation("create a product object")
    @PostMapping(value = "/createObject")
    public int saveObject(
        @RequestParam(name = "sku") String sku,
        @RequestParam(name = "size") String size,
        @RequestParam(name = "colour") String colour,
        @RequestParam(name = "quantity") Integer quantity,
        @RequestParam(name = "price") Double price) {
        return productObjectService.save(sku, size, colour, quantity, price);
    }

    @ApiOperation("find a product object")
    @GetMapping(value = "/findObject")
    public Optional<ProductObject> findPOById(
        @RequestParam(name = "poId") Long productObjectId) {
        return productObjectService.findPOById(productObjectId);
    }

    @ApiOperation("find product objects of a product")
    @GetMapping(value = "/findObjects")
    public List<ProductObject> findPOsOfProduct(
        @RequestParam(name = "sku") String sku) {
        return productObjectService.findPOsOfProduct(sku);
    }

    @ApiOperation("find product objects of a product where quantity greater or equal")
    @GetMapping(value = "/findObjectWhereQGE")
    public List<ProductObject> findPOsOfProductAndQtyGE(
        @RequestParam(name = "sku") String sku,
        @RequestParam(name = "qty") Integer quantity) {
        return productObjectService.findPOsOfProductAndQtyGE(sku, quantity);
    }

    @ApiOperation("find sold product objects")
    @GetMapping(value = "/findSoldObjects")
    public List<ProductObject> findSoldPOs() {
        return productObjectService.findSoldPOs();
    }

    @ApiOperation("find product objects that will be soon sold")
    @GetMapping(value = "/findSoonSoldObjects")
    public List<ProductObject> findSoonSoldPOs() {
        return productObjectService.findSoonSoldPOs();
    }

    @ApiOperation("find all available product objects where price is between two values")
    @GetMapping(value = "/findObjectsBetween")
    public List<ProductObject> findAvailablePOs(
        @RequestParam(name = "start") Double start,
        @RequestParam(name = "end") Double end) {
        return productObjectService.findAvailablePOs(start, end);
    }

    @ApiOperation("delete a product object")
    @DeleteMapping(value = "/deleteObject")
    public int deleteObject(@RequestParam(name = "poId") Long productObjectId) {
        return productObjectService.delete(productObjectId);
    }

}
