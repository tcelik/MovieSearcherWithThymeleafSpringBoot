package org.csystem.thymeleafmodelsenderapp.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*

        İstek geldiğinde controller'dan geçmek zorunda gelen istek (HTTP request gelicek)
        GET, POST Olarak gelir.

*/
@RestController
@RequestMapping("my") //end-point'ler oluşturmak
public class MyController {

    @GetMapping("senget")
    public String sender1()
    {
        return "sender1"; //server bu metodu çağırdı ve response olarak sender1 dönülüyor. response bodyde string var.
    }

}
