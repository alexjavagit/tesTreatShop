package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.entity.ProductImages;
import ua.kiev.prog.repository.ProductImagesRepository;
import ua.kiev.prog.repository.ProductRepository;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {
    private final ProductImagesRepository productImagesRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductImagesServiceImpl(ProductImagesRepository productImagesRepository, ProductRepository productRepository) {
        this.productImagesRepository = productImagesRepository;
        this.productRepository = productRepository;
    }

    public List<ProductImages> getAllImages() {
        return productImagesRepository.findAll();
    }

    public List<ProductImages> getProductImages(Long id) {
        return productImagesRepository.findProductImages(id);
    }

    List<Product> getProductsBySearchTerm(String searchString) {
        return productRepository.getProductsBySearchTerm(searchString);
    }

    List<Product> findByDiscount() {
        return productRepository.findByDiscount();
    }

    @Transactional
    public void saveImage(String filepath, ProductImages productImages) {
        File file = new File(filepath);
        String fileName = StringUtils.cleanPath(file.getName());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            productImages.setImage(Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void saveUploadedImage(MultipartFile file, ProductImages productImages) {
        byte [] byteArr= new byte[0];
        try {
            byteArr = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productImages.setImage(Base64.getEncoder().encodeToString(byteArr));
        productImagesRepository.save(productImages);
    }

    @Transactional
    public void deleteImage(Long id) {
        Optional<ProductImages> image = productImagesRepository.findById(id);
        if (image != null) {
            productImagesRepository.deleteById(id);
        }
    }

//    public void saveImage(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if(fileName.contains(".."))
//        {
//            System.out.println("not a a valid file");
//        }
//        try {
//            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
