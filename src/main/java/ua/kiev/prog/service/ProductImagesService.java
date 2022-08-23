package ua.kiev.prog.service;

import ua.kiev.prog.entity.ProductImages;

import java.util.List;


public interface ProductImagesService {

    void saveImage(String filepath, ProductImages productImages);

    List<ProductImages> getAllImages();

    List<ProductImages> getProductImages(Long id);

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
