package com.styleathome.config;

import com.styleathome.entity.Product;
import com.styleathome.entity.Role;
import com.styleathome.entity.ServiceOffering;
import com.styleathome.entity.SessionType;
import com.styleathome.entity.User;
import com.styleathome.repository.ProductRepository;
import com.styleathome.repository.ServiceOfferingRepository;
import com.styleathome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedAdminUser();
        seedProducts();
        seedServiceOfferings();
    }

    private void seedAdminUser() {
        if (userRepository.findByEmail("admin@styleathome.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@styleathome.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println("Admin user seeded.");
        }
    }

    private void seedProducts() {
        // Self-Healing Patch for any broken external image URLs currently in the DB
        List<Product> existing = productRepository.findAll();
        for (Product p : existing) {
            switch (p.getName()) {
                case "Casual Denim Jeans": p.setImageUrl("/images/mens_jeans.png"); break;
                case "Plain White T-Shirt": p.setImageUrl("/images/mens_tshirt.png"); break;
                case "Formal Oxford Shoes": p.setImageUrl("/images/mens_oxfords.png"); break;
                case "Slim Fit Formal Shirt": p.setImageUrl("/images/mens_formal_shirt.png"); break;
                case "Floral Summer Dress": p.setImageUrl("/images/womens_dress.png"); break;
                case "Designer Leather Handbag": p.setImageUrl("/images/womens_handbag.png"); break;
                case "Cotton Kurti Set": p.setImageUrl("/images/womens_kurti.png"); break;
                case "High-Waist Skinny Jeans": p.setImageUrl("/images/womens_skinny_jeans.png"); break;
                case "Crop Top & Skirt Co-ord": p.setImageUrl("/images/womens_coord.png"); break;
                case "Sports Running Sneakers": p.setImageUrl("/images/mens_sports_sneakers.png"); break;
                case "Classic White Sneakers": p.setImageUrl("/images/orig_white_sneakers.png"); break;
                case "Classic Leather Jacket": p.setImageUrl("/images/orig_leather_jacket.png"); break;
            }
        }
        productRepository.saveAll(existing);

        if (productRepository.count() < 25) {
            Product p1 = new Product();
            p1.setName("Banarasi Silk Saree");
            p1.setDescription("Authentic premium Banarasi silk saree with rich zari work.");
            p1.setPrice(12500.00D);
            p1.setCategory("Women");
            p1.setImageUrl("/images/saree.png");
            p1.setRating(4.9);

            Product p2 = new Product();
            p2.setName("Designer Lehenga Choli");
            p2.setDescription("Intricately embroidered designer lehenga for weddings and festive wear.");
            p2.setPrice(18999.00D);
            p2.setCategory("Women");
            p2.setImageUrl("/images/lehenga.png");
            p2.setRating(4.8);

            Product p3 = new Product();
            p3.setName("Royal Blue Men's Sherwani");
            p3.setDescription("Premium velvet sherwani with silver threadwork for formal occasions.");
            p3.setPrice(15000.00D);
            p3.setCategory("Men");
            p3.setImageUrl("/images/sherwani.png");
            p3.setRating(4.7);

            Product p4 = new Product();
            p4.setName("Classic Cotton Kurta Pajama");
            p4.setDescription("Crisp white cotton kurta pajama set for traditional, elegant daily wear.");
            p4.setPrice(2500.00D);
            p4.setCategory("Men");
            p4.setImageUrl("/images/kurta.png");
            p4.setRating(4.5);
            
            Product p5 = new Product();
            p5.setName("Casual Denim Jeans");
            p5.setDescription("Premium quality slim fit classic blue denim jeans.");
            p5.setPrice(1899.00D);
            p5.setCategory("Men");
            p5.setImageUrl("/images/mens_jeans.png");
            p5.setRating(4.6);

            Product p6 = new Product();
            p6.setName("Plain White T-Shirt");
            p6.setDescription("100% cotton breathable everyday white t-shirt.");
            p6.setPrice(499.00D);
            p6.setCategory("Men");
            p6.setImageUrl("/images/mens_tshirt.png");
            p6.setRating(4.4);

            Product p7 = new Product();
            p7.setName("Formal Oxford Shoes");
            p7.setDescription("Genuine leather lace-up formal oxford shoes in tan.");
            p7.setPrice(3499.00D);
            p7.setCategory("Men");
            p7.setImageUrl("/images/mens_oxfords.png"); 
            p7.setRating(4.8);

            Product p8 = new Product();
            p8.setName("Sports Running Sneakers");
            p8.setDescription("Lightweight running shoes with memory foam insoles.");
            p8.setPrice(2200.00D);
            p8.setCategory("Men");
            p8.setImageUrl("/images/mens_sports_sneakers.png");
            p8.setRating(4.7);
            
            Product p9 = new Product();
            p9.setName("Slim Fit Formal Shirt");
            p9.setDescription("Crisp light blue button-down shirt for office wear.");
            p9.setPrice(1299.00D);
            p9.setCategory("Men");
            p9.setImageUrl("/images/mens_formal_shirt.png"); 
            p9.setRating(4.5);

            Product p10 = new Product();
            p10.setName("Designer Party Tuxedo");
            p10.setDescription("Modern fit tuxedo blazer for evening events and parties.");
            p10.setPrice(6500.00D);
            p10.setCategory("Men");
            p10.setImageUrl("https://images.unsplash.com/photo-1507679799987-c73779587ccf?w=500&q=80");
            p10.setRating(4.9);

            Product p11 = new Product();
            p11.setName("Floral Summer Dress");
            p11.setDescription("Flowy midi dress with bright floral prints.");
            p11.setPrice(1750.00D);
            p11.setCategory("Women");
            p11.setImageUrl("/images/womens_dress.png"); 
            p11.setRating(4.7);

            Product p12 = new Product();
            p12.setName("Designer Leather Handbag");
            p12.setDescription("Spacious beige leather tote for daily essentials.");
            p12.setPrice(4500.00D);
            p12.setCategory("Women");
            p12.setImageUrl("/images/womens_handbag.png");
            p12.setRating(4.8);

            Product p13 = new Product();
            p13.setName("Stiletto Party Heels");
            p13.setDescription("Classic black 4-inch stiletto premium heels.");
            p13.setPrice(2500.00D);
            p13.setCategory("Women");
            p13.setImageUrl("https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=500&q=80");
            p13.setRating(4.6);

            Product p14 = new Product();
            p14.setName("Cotton Kurti Set");
            p14.setDescription("Vibrant yellow kurti with matching comfortable palazzo pants.");
            p14.setPrice(1999.00D);
            p14.setCategory("Women");
            p14.setImageUrl("/images/womens_kurti.png");
            p14.setRating(4.5);

            Product p15 = new Product();
            p15.setName("High-Waist Skinny Jeans");
            p15.setDescription("Stretchable black high-waist women's denim.");
            p15.setPrice(1499.00D);
            p15.setCategory("Women");
            p15.setImageUrl("/images/womens_skinny_jeans.png");
            p15.setRating(4.4);

            Product p16 = new Product();
            p16.setName("Crop Top & Skirt Co-ord");
            p16.setDescription("Chic two-piece coordinated beige set for casual outings.");
            p16.setPrice(2200.00D);
            p16.setCategory("Women");
            p16.setImageUrl("/images/womens_coord.png");
            p16.setRating(4.3);

            productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16));
            System.out.println("Products seeded.");
        }
    }

    private void seedServiceOfferings() {
        if (serviceOfferingRepository.count() == 0) {
            ServiceOffering s1 = new ServiceOffering();
            s1.setName("Comprehensive Wardrobe Styling");
            s1.setDescription("A 90-minute online session with a professional stylist to completely overhaul your wardrobe and personal brand.");
            s1.setPrice(150.00D);
            s1.setDurationMinutes(90);
            s1.setImageUrl("https://images.unsplash.com/photo-1490481651871-ab68de25d43d");
            
            ServiceOffering s2 = new ServiceOffering();
            s2.setName("Event Outfit Consultation");
            s2.setDescription("Have a big event coming up? Book a 45-minute quick consultation to pick the perfect outfit.");
            s2.setPrice(75.00D);
            s2.setDurationMinutes(45);
            s2.setImageUrl("https://images.unsplash.com/photo-1550614000-4b95d4ebfa1d");

            serviceOfferingRepository.save(s1);
            serviceOfferingRepository.save(s2);
            System.out.println("Service offerings seeded.");
        }
    }
}
