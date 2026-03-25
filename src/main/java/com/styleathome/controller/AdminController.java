package com.styleathome.controller;

import com.styleathome.entity.Product;
import com.styleathome.entity.ServiceOffering;
import com.styleathome.repository.ProductRepository;
import com.styleathome.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductRepository productRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;

    @PostMapping("/seed-products")
    public ResponseEntity<String> seedProducts() {
        if (productRepository.count() > 0) {
            return ResponseEntity.badRequest().body("Products already seeded.");
        }

        List<Product> products = Arrays.asList(
            Product.builder().name("Classic Navy Blazer").description("Premium wool-blend unstructured blazer perfect for both formal and smart-casual occasions.").price(129.99).rating(4.8).category("Jackets").imageUrl("https://images.unsplash.com/photo-1507679799987-c73779587ccf?auto=format&fit=crop&q=80&w=400").build(),
            Product.builder().name("Slim Fit Chinos").description("Comfortable stretch-cotton chinos in an earthen olive hue. Suitable for all-day wear.").price(49.99).rating(4.5).category("Pants").imageUrl("https://images.unsplash.com/photo-1624378439575-d8705ad7ae80?auto=format&fit=crop&q=80&w=400").build(),
            Product.builder().name("Silk Evening Dress").description("Elegant full-length silk slip dress with a draped cowl neckline.").price(189.99).rating(5.0).category("Dresses").imageUrl("https://images.unsplash.com/photo-1566150905458-1bf1fc113f0d?auto=format&fit=crop&q=80&w=400").build(),
            Product.builder().name("White Oxford Shirt").description("The essential crisp white button-down, crafted from breathable organic cotton.").price(59.99).rating(4.6).category("Shirts").imageUrl("https://images.unsplash.com/photo-1596755094514-f87e32f85e2c?auto=format&fit=crop&q=80&w=400").build(),
            Product.builder().name("Leather Chelsea Boots").description("Handcrafted Italian leather boots with elastic side panels and durable soles.").price(149.99).rating(4.7).category("Shoes").imageUrl("https://images.unsplash.com/photo-1620189507195-68309c04c4d0?auto=format&fit=crop&q=80&w=400").build(),
            Product.builder().name("Floral Midi Skirt").description("Lightweight A-line midi skirt with a vintage floral print and side slit.").price(65.00).rating(4.4).category("Skirts").imageUrl("https://images.unsplash.com/photo-1582142306909-195724d33ffc?auto=format&fit=crop&q=80&w=400").build()
        );
        productRepository.saveAll(products);
        return ResponseEntity.ok("Fashion Products seeded successfully.");
    }

    @PostMapping("/seed-services")
    public ResponseEntity<String> seedServices() {
        if (serviceOfferingRepository.count() > 0) {
            return ResponseEntity.badRequest().body("Services already seeded.");
        }

        List<ServiceOffering> services = Arrays.asList(
            ServiceOffering.builder().name("Personal Wardrobe Consult").description("A comprehensive 1-on-1 virtual session with a pro stylist to revamp your everyday wardrobe.").price(99.00).durationMinutes(60).imageUrl("https://images.unsplash.com/photo-1558769132-cb1fac085049?auto=format&fit=crop&q=80&w=400").build(),
            ServiceOffering.builder().name("Event Styling").description("Got a big event? Our experts will help you pick the perfect outfit down to the accessories.").price(150.00).durationMinutes(90).imageUrl("https://images.unsplash.com/photo-1490481651871-ab68de25d43d?auto=format&fit=crop&q=80&w=400").build(),
            ServiceOffering.builder().name("Color Analysis Session").description("Discover which hues complement your natural skin tone with a detailed visual analysis.").price(75.00).durationMinutes(45).imageUrl("https://images.unsplash.com/photo-1550989460-0adf9ea622e2?auto=format&fit=crop&q=80&w=400").build()
        );
        serviceOfferingRepository.saveAll(services);
        return ResponseEntity.ok("Fashion Services seeded successfully.");
    }
}
