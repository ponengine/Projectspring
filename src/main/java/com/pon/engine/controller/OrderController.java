package com.pon.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pon.engine.service.OrderService;



@Controller
public class OrderController {
@Autowired
private OrderService orderService;
@RequestMapping("/order") 
public String process() {
orderService.processOrder("P1234",10);
System.out.println("send value");
return "redirect:/products";
}
}
