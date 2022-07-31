package com.jun.shop.controller;

import com.jun.shop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class TestController {

    @GetMapping("/ex01")
    public String Test01(Model model){
        ItemDto itemDto = new ItemDto();
        itemDto.setItemNm("테스트 상품1");
        itemDto.setPrice(10000);
        itemDto.setItemDetail("제품 상세 설명");
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);
        return "/testHello";
    }

    @GetMapping("/ex02")
    public String Test02(Model model){
        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i = 1; i <= 10; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("테스트 상품1");
            itemDto.setPrice(10000);
            itemDto.setItemDetail("제품 상세 설명");
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "/testHello2";
    }

    @GetMapping("/ex03")
    public String Test03(){
        return "thymeleaf";
    }
}
