package ua.kiev.prog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.kiev.prog.entity.*;
import ua.kiev.prog.repository.CategoryRepository;
import ua.kiev.prog.service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends GlobalMethodSecurityConfiguration {


    @Autowired
    CategoryRepository categoryRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner demo(final UserService userService,
                                  final ProductImagesService productImagesService,
                                  final PasswordEncoder encoder) {
        return new CommandLineRunner() {

            @Override
            public void run(String... strings) throws Exception {
                CustomUser customUser = new CustomUser("admin", encoder.encode("password"), UserRole.ADMIN);
                userService.updateUser(customUser);


                Category mens = new Category("Men's shoes", "male");
                Category womens = new Category("Women's shoes", "female");
                Category children = new Category("Children's shoes", "children");


                List<CategorySizes> mensCategorySizes = new ArrayList<>();
                CategorySizes categorySizes;
                for (int i = 40; i <= 45; i++) {
                    categorySizes = new CategorySizes(mens, Integer.toString(i));
                    mensCategorySizes.add(categorySizes);
                }
                mens.setCategorySizes(mensCategorySizes);

                List<CategorySizes> womensCategorySizes = new ArrayList<>();
                for (int i = 36; i <= 41; i++) {
                    categorySizes = new CategorySizes(womens, Integer.toString(i));
                    womensCategorySizes.add(categorySizes);
                }
                womens.setCategorySizes(womensCategorySizes);

                List<CategorySizes> childrenCategorySizes = new ArrayList<>();
                for (int i = 19; i <= 24; i++) {
                    categorySizes = new CategorySizes(children, Integer.toString(i));
                    childrenCategorySizes.add(categorySizes);
                }
                children.setCategorySizes(childrenCategorySizes);

                // 10 products for Men category
                ProductImages productImages;
                List<Product> products = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    Product product = new Product("The Lifestyle Co Men White Sneakers " + i, mens,
                            "A pair of round toe white sneakers ,has regular styling,\n" +
                                    "Lace-ups detail\n" +
                                    "Synthetic leather upper\n" +
                                    "Cushioned footbed\n" +
                                    "Textured and patterned outsole\n" +
                                    "Warranty: 3 months",
                            new BigDecimal("180"));
                    //sizes
                    for (CategorySizes catsSizes : mensCategorySizes) {
                        product.addProductSizes(catsSizes);
                    }
                    products.add(product);

                    //images
                    List<ProductImages> listProductImages = new ArrayList<>();
                    productImages = new ProductImages(null, product, null);
                    productImagesService.saveImage("/Users/alex/IdeaProjects/testShop/pics/men/1.jpeg", productImages);
                    listProductImages.add(productImages);
                    productImages = new ProductImages(null, product, null);
                    productImagesService.saveImage("/Users/alex/IdeaProjects/testShop/pics/men/2.jpeg", productImages);
                    listProductImages.add(productImages);

                    product.setProductImages(listProductImages);

                    mens.addProduct(product);
                }
                categoryRepository.save(mens);


                // 10 products for Women category
                products = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    Product product = new Product("Women's Air Max Interlock 75 Light Casual Sneakers from Finish Line " + i, womens,
                            "Enjoy the experience of walking on air with the Nike Women's Air Max Interlock 75 Light Casual Sneakers.",
                            new BigDecimal("65"));
                    //sizes
                    for (CategorySizes catsSizes : womensCategorySizes) {
                        product.addProductSizes(catsSizes);
                    }
                    products.add(product);

                    //images
                    List<ProductImages> listProductImages = new ArrayList<>();
                    productImages = new ProductImages(null, product, null);
                    productImagesService.saveImage("/Users/alex/IdeaProjects/testShop/pics/women/1.jpeg", productImages);
                    listProductImages.add(productImages);
                    productImages = new ProductImages(null, product, null);
                    productImagesService.saveImage("/Users/alex/IdeaProjects/testShop/pics/men/1.jpeg", productImages);
                    listProductImages.add(productImages);

                    product.setProductImages(listProductImages);

                    womens.addProduct(product);
                }
                categoryRepository.save(womens);

                // 10 products for Children category
                products = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    Product product = new Product("CROSS JUMP " + i, children,
                            "The Cross Jump sandal is a strappy style that provides plenty of breathability while protecting delicate little toes. The strap closure, featuring the signature Bobux stud, is easily adjustable and a padded heel provides extra comfort.",
                            new BigDecimal("35"));
                    //sizes
                    for (CategorySizes catsSizes : childrenCategorySizes) {
                        product.addProductSizes(catsSizes);
                    }
                    products.add(product);

                    //images
                    List<ProductImages> listProductImages = new ArrayList<>();
                    productImages = new ProductImages(null, product, null);
                    productImagesService.saveImage("/Users/alex/IdeaProjects/testShop/pics/children/1.jpeg", productImages);
                    listProductImages.add(productImages);
                    productImages = new ProductImages(null, product, null);
                    productImagesService.saveImage("/Users/alex/IdeaProjects/testShop/pics/children/1.jpeg", productImages);
                    listProductImages.add(productImages);

                    product.setProductImages(listProductImages);

                    children.addProduct(product);
                }
                categoryRepository.save(children);
            }
        };
    }
}