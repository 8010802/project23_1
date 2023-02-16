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
public class AdminController {

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
    public AdminController(
            ProductValidator productValidator,
            ProductService productService,
            OrderService orderService,
            CategoryRepository categoryRepository,
            PersonService personService,
            OrderRepository orderRepository,
            PersonRepository personRepository1) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.orderService = orderService;
        this.personService = personService;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.personRepository = personRepository1;
    }
    //////////

    // Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping("/admin")
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();

        if (role.equals("ROLE_USER")) {
            return "redirect:/userPage";
        } if (role.equals("ROLE_SELLER")){
            return "redirect:/seller";
        }
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }

    // Метод по отображению формы добавление
    @GetMapping("/admin/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

    // Метод по добавлению объекта с формы в таблицу product
    @PostMapping("/admin/add")
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
            return "product/addProduct";
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
        return "redirect:/admin";
    }

    // Метод по удалению товара по id
    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // Метод по получению товара по id и отображение шаблона редактирования
    @GetMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("editProduct", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/admin/edit/{id}")
    public String editProduct(
            @ModelAttribute("editProduct") Product product,
            @PathVariable("id") int id
    ) {
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    // вывод заказов
    @GetMapping("/admin/orderList")
    public String showOrders(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/orderList";
    }

    // удалению заказа по id
    @GetMapping("/admin/orderList/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/orderList";
    }

    // изменение статуса заказа
    @GetMapping("/admin/orderList/editStatus1/{id}")
    public String editOrderStatus1(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus1(orderStatus);
        return "redirect:/admin/orderList";
    }

    @GetMapping("/admin/orderList/editStatus2/{id}")
    public String editOrderStatus2(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus2(orderStatus);
        return "redirect:/admin/orderList";
    }

    @GetMapping("/admin/orderList/editStatus3/{id}")
    public String editOrderStatus3(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus3(orderStatus);
        return "redirect:/admin/orderList";
    }

    @GetMapping("/admin/orderList/editStatus4/{id}")
    public String editOrderStatus4(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus4(orderStatus);
        return "redirect:/admin/orderList";
    }
    //////////
    // вывод пользователей
    @GetMapping("/admin/personList")
    public String showUsers(Model model) {
        model.addAttribute("orders", orderService.geAllOrders());
        model.addAttribute("persons", personRepository.findAll());
        return "admin/personList";
    }

    // изменение роли пользователей
    @GetMapping("/admin/personList/setRole1/{id}")
    public String editPersonStatus1(@PathVariable() int id) {
        Person personStatus = personService.getPersonId(id);
        personService.updateRole1(personStatus);
        return "redirect:/admin/personList";
    }

    @GetMapping("/admin/personList/setRole2/{id}")
    public String editPersonStatus2(@PathVariable() int id) {
        Person personStatus = personService.getPersonId(id);
        personService.updateRole2(personStatus);
        return "redirect:/admin/personList";
    }

    @GetMapping("/admin/personList/setRole3/{id}")
    public String editPersonStatus3(@PathVariable() int id) {
        Person personStatus = personService.getPersonId(id);
        personService.updateRole3(personStatus);
        return "redirect:/admin/personList";
    }

    @GetMapping("/admin/personList/setRole4/{id}")
    public String editPersonStatus4(@PathVariable() int id) {
        Person personStatus = personService.getPersonId(id);
        personService.updateRole4(personStatus);
        return "redirect:/admin/personList";
    }

    // удаление пользователя по id
    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        personService.deleteUser(id);
        return "redirect:/admin/personList";
    }

//    @GetMapping("/admin/delete/{id}")
//    public String deleteProduct1(@PathVariable("id") int id) {
//        productService.deleteProduct(id);
//        return "redirect:/admin";
//    }
    //////////

    //добавлен поиск по id заказа
//    @PostMapping("/admin/orderList/searchId")
//    public String orderIdSearch(
//            @RequestParam("search") String search, Model model) {
//        System.out.println(search);
//        if (search.isEmpty()) {
//            return "redirect:/admin/orderList";
//        } else {
//            model.addAttribute("search_number", orderRepository.findOrderById(Integer.parseInt(search)));
//            model.addAttribute("value_search", search);
//        }
//        return "admin/orderList";
//    }

    // поиск по 4 цифрам
        @PostMapping("/admin/orderList/search")
        public String findOrderNumber(@RequestParam("search") String search, Model model) {
            if (search.length()>4) {
                search = search.substring(search.length() - 4);
            }
            model.addAttribute("orders", orderService.findOrderNumber(search));
            model.addAttribute("value_search", search);
            return "admin/orderList";
        }
    //////////
    // просмотр определенного товара
    @GetMapping("/admin/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product/infoProduct";
    }


}
