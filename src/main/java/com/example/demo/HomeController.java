package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String listCategory(Model model){
        model.addAttribute("category", categoryRepository.findAll());
        return "listCategory";
    }

    @GetMapping("/addcategory")
    public String categoryForm(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processcategory")
    public String processCategory(@Valid Category category, BindingResult result){
        if (result.hasErrors()){
            return "categoryform";
        }
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("/delete/{id}")
    public String delCategory(@PathVariable("id") long id){
        categoryRepository.deleteById(id);
        return "redirect:/";
    }

    //--------------------------------------------------------------------

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String listCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String carForm(Model model){
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Car car, BindingResult result){
        if (result.hasErrors()){
            return "carform";
        }
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCars(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String delCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }


//    @RequestMapping("/")
//    public String index(Model model){
//        Category category = new Category();
//
//        category.setName("Sedan");
//        category.setId(category.getId());
//
//        Car car = new Car();
//        car.setManufacturer("Toyota");
//        car.setModel("Camry");
//        car.setYear(1997);
//        car.setNumOfDoors(4);
//
//        Set<Car> cars = new HashSet<Car>();
//        cars.add(car);
//
//        car = new Car();
//        car.setManufacturer("Honda");
//        car.setModel("Accord");
//        car.setYear(2017);
//        car.setNumOfDoors(4);
//        cars.add(car);
//
//        category.setCars(cars);
//
//        categoryRepository.save(category);
//
//        model.addAttribute("categories", categoryRepository.findAll());
//        return "index";
//    }
}
