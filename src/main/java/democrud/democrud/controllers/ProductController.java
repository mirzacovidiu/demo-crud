package democrud.democrud.controllers;

import com.sun.istack.NotNull;
import democrud.democrud.enums.CategoryType;
import democrud.democrud.model.Product;
import democrud.democrud.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> readAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> readProductById(@PathVariable Long id) {
        Product prod = productService.findProductById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        return ResponseEntity.ok(prod);
        //sau
        // return new ResponseEntity<Product>(prod, HttpStatus.OK);

    }


    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        Product newProduct = productService.createNewProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newProduct);

    }
//    @PutMapping("/products/{id}")
//    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product product){
//        Optional<Product> productData = productService.findProductById(id);
//        if(productData.isPresent()){
//            Product productToUpdate = productData.get();
//            productToUpdate.setName(product.getName());
//            productToUpdate.setPrice(product.getPrice());
//            productToUpdate.setDescription(product.getDescription());
//            return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct());
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        Optional<Product> product=productService.findProductById(id);
//        String response = productService.deleteProductById(id);
        if (product.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProductById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productService.deleteProductById(id));
        }
    }

    @GetMapping("/productsByCategory")
    public ResponseEntity<List<Product>> findProductsByCategory(@RequestParam String categoryType){
        List<Product> products = productService.findProductsByCategoryType(CategoryType.valueOf(categoryType));
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/updateProduct/")
    public ResponseEntity<String>  updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
                .body(productService.updateProduct(product));
    }
}

