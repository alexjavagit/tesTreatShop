package ua.kiev.prog.service;

import org.springframework.web.multipart.MultipartFile;
import ua.kiev.prog.entity.ProductImages;

import java.io.File;
import java.util.List;


public interface ProductImagesService {

    void saveImage(String filepath, ProductImages productImages);

    void saveUploadedImage(MultipartFile file, ProductImages productImages);

    List<ProductImages> getAllImages();

    List<ProductImages> getProductImages(Long id);

    void deleteImage(Long id);
}
