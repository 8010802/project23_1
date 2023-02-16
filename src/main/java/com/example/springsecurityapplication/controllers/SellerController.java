package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.repositories.PersonRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping()
public class SellerController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductValidator productValidator;
    private final ProductService productService;
    private final PersonService personService;
    private final OrderService orderService;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;


    @Autowired
    public SellerController(
            ProductValidator productValidator,
            ProductService productService,
            OrderService orderService,
            CategoryRepository categoryRepository,
            PersonService personService,
            OrderRepository orderRepository,
            PersonRepository personRepository) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.orderService = orderService;
        this.personService = personService;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }
    //////////

    // Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping("/seller")
    public String seller(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();

        if (role.equals("ROLE_USER")) {
            return "redirect:/userPage";
        } if (role.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }
        model.addAttribute("products", productService.getAllProduct());
        return "seller/seller";
    }

    // Метод по отображению формы добавление
    @GetMapping("/seller/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "seller/addProduct";
    }

    // Метод по добавлению объекта с формы в таблицу product
    @PostMapping("/seller/add")
    public String addProduct(
            @ModelAttribute("product") @Valid Product product,
            BindingResult bindingResult,
            @RequestParam("file_one") MultipartFile file_one,
            @RequestParam("file_two") MultipartFile file_two,
            @RequestParam("file_three") MultipartFile file_three,
            @RequestParam("file_four") MultipartFile file_four,
            @RequestParam("file_five") MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "seller/addProduct";
        }
        // Проверка на пустоту файла
        if (file_one != null) {
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if (!uploadDir.exists()) {
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_two != null) {
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if (!uploadDir.exists()) {
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_three != null) {
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if (!uploadDir.exists()) {
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_four != null) {
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if (!uploadDir.exists()) {
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_five != null) {
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if (!uploadDir.exists()) {
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/seller";
    }

    // Метод по удалению товара по id
    @GetMapping("/seller/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/seller";
    }

    // Метод по получению товара по id и отображение шаблона редактирования
    @GetMapping("/seller/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("editProduct", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "seller/editProduct";
    }

    @PostMapping("/seller/edit/{id}")
    public String editProduct(
            @ModelAttribute("editProduct") Product product,
            @PathVariable("id") int id
    ) {
        productService.updateProduct(id, product);
        return "redirect:/seller";
    }

    // вывод заказов
    @GetMapping("/seller/orderList")
    public String showOrders(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("orders", orderRepository.findAll());
        return "seller/orderList";
    }

//    // удалению заказа по id
//    @GetMapping("/seller/orderList/deleteOrder/{id}")
//    public String deleteOrder(@PathVariable("id") int id) {
//        orderService.deleteOrder(id);
//        return "redirect:/seller/orderList";
//    }

    // изменение статуса заказа
    @GetMapping("/seller/orderList/editStatus2/{id}")
    public String editOrderStatusSeller2(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus2(orderStatus);
        return "redirect:/seller/orderList";
    }

    @GetMapping("/seller/orderList/editStatus3/{id}")
    public String editOrderStatusSeller3(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus3(orderStatus);
        return "redirect:/seller/orderList";
    }

    @GetMapping("/seller/orderList/editStatus4/{id}")
    public String editOrderStatusSeller4(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus4(orderStatus);
        return "redirect:/seller/orderList";
    }

    //////////
    // поиск заказа по 4 последним цифрам
        @PostMapping("/seller/orderList/search")
        public String findOrderNumber(@RequestParam("search") String search, Model model) {
            if (search.length()>4) {
                search = search.substring(search.length() - 4);
            }
            model.addAttribute("orders", orderService.findOrderNumber(search));
            model.addAttribute("value_search", search);
            return "seller/orderList";
        }

    // просмотр определенного товара
    @GetMapping("/seller/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "seller/infoProduct";
    }


}
