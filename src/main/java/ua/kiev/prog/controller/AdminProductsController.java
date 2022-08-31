package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CategorySizes;
import ua.kiev.prog.entity.POJO.DeleteProductModel;
import ua.kiev.prog.entity.POJO.ProductData;
import ua.kiev.prog.entity.POJO.UploadModel;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.entity.ProductImages;
import ua.kiev.prog.exception.ProductNotFoundException;
import ua.kiev.prog.service.CategoryService;
import ua.kiev.prog.service.CategorySizesService;
import ua.kiev.prog.service.ProductImagesService;
import ua.kiev.prog.service.ProductService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductImagesService productImagesService;
    @Autowired
    private CategorySizesService categorySizesService;

    @GetMapping("/products")
    //@PreAuthorize("hasRole('ADMIN')")
    public String productsList(Model model,
                               @RequestParam(value = "sortBy", required = false) String sortBy,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "searchCategory", required = false) Long searchCategory,
                               @RequestParam(value = "searchName", required = false) String searchName,
                               @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "size", required = false) String size
    ) {

        int ipage = (page == null) ? 0 : Integer.parseInt(page)-1;
        int isize = (size == null) ? 10 : Integer.parseInt(size);
        Sort.Direction direction = (order == null) ? Sort.Direction.DESC : ((order.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC);
        String sSortBy = (sortBy == null) ? "id" : ((sortBy.equals("price") || sortBy.equals("name")) ? sortBy :  "id");
        Pageable pagingSort = PageRequest.of(ipage, isize, direction, sSortBy);

        Long icategory = 0L;
        if (searchCategory != null) {
            Category category = categoryService.getCategory(searchCategory.longValue());
            if (category != null) {
                icategory = category.getId();
            }
        }
        Page<Product> productList;
        if (!icategory.equals(0L) && (searchName != null && searchName.length()>0)) {

            productList = productService.findByCategoryAndName(icategory, searchName, pagingSort);
        } else if (!icategory.equals(0L)) {

            productList = productService.findByCategory(icategory, pagingSort);
        } else if (searchName != null && searchName.length()>0) {

            productList = productService.findByName(searchName, pagingSort);
        } else {

            productList = productService.getAllProducts(pagingSort);
        }

        model.addAttribute("products", productList.getContent());
        model.addAttribute("endpage", productList.getTotalPages());
        model.addAttribute("startpage", 1);
        model.addAttribute("currentPage", productList.getNumber()+1);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "products";
    }

    @GetMapping("/products/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(Model model, @PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/new")
    //@PreAuthorize("hasRole('ADMIN')")
    public String newProduct(Model model) {

        model.addAttribute("action", "/admin/products/new");
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "product_edit";
    }

    @GetMapping("/products/update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public String updateFormProduct(Model model, @PathVariable Long id) throws ParseException {

        Product product = productService.getById(id);
        if (product == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("action", "/admin/products/update");
        model.addAttribute("theProduct", product);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "product_edit";
    }

    @PostMapping("/products/updateData")
    public ResponseEntity<?> updateProduct(@RequestBody ProductData productData) {
        Category category = categoryService.getById(productData.getCategory());
        Integer discount;
        try {
            discount = Integer.parseInt(productData.getDiscount());
        } catch(NumberFormatException ex){
            discount = 0;
        }

        String message;
        if (productData.getId() != null) {
            Category oldCategory = productService.getById(productData.getId()).getCategory();
            Product product = productService.getById(productData.getId());
            productService.updateProduct(productData.getId(), productData.getName(), category, productData.getDescription(), new BigDecimal(productData.getPrice()), discount);
            if (!oldCategory.equals(category)) {
                // delete old
                List<CategorySizes> oldCategorySizes = categorySizesService.getSizesForCategory(oldCategory);
                for (CategorySizes catsSizes : oldCategorySizes) {
                    product.removeProductSizes(catsSizes);
                }
                //add new
                List<CategorySizes> categorySizes = categorySizesService.getSizesForCategory(category);
                for (CategorySizes catsSizes : categorySizes) {
                    product.addProductSizes(catsSizes);
                }
            }
            message = "{\"message\" : \"Product Updated\"}";
        } else {
            Product product = productService.addProduct(productData.getName(), category, productData.getDescription(), new BigDecimal(productData.getPrice()), Integer.parseInt(productData.getDiscount()));
            List<CategorySizes> categorySizes = categorySizesService.getSizesForCategory(category);
            //sizes
            for (CategorySizes catsSizes : categorySizes) {
                product.addProductSizes(catsSizes);
            }
            message = "{\"id\" : \""+product.getId()+"\"}";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/products/updateFile")
    public ResponseEntity<?> handleFileUpload(@ModelAttribute UploadModel model) {
        Product product = productService.getById(Long.parseLong(model.getId()));
        List<ProductImages> listProductImages = new ArrayList<>();

        ProductImages productImages = new ProductImages(null, product, null);
        productImagesService.saveUploadedImage(model.getFile(), productImages);
        listProductImages.add(productImages);

        product.setProductImages(listProductImages);

        //return new ResponseEntity<String>(productImages.getId().toString(), HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        String message = "{\"id\" : \""+productImages.getId()+"\"}";
        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/products/deleteFile")
    public ResponseEntity<?> deleteFileProduct(@RequestBody DeleteProductModel deleteProductModel) {
        Long lid = Long.parseLong(deleteProductModel.getId());

        productImagesService.deleteImage(lid);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        String message = "{\"message\" : \"Product image Deleted\"}";
        return new ResponseEntity<String>(message, headers, HttpStatus.OK);

        //return new ResponseEntity<String>("Product image Deleted", HttpStatus.OK);
    }
}
