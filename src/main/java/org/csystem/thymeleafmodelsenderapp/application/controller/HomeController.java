package org.csystem.thymeleafmodelsenderapp.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test") // localhost:8080 -> GET /test/welcome1 olarak aslında geliyor, hangi str'de hangi metot çağrılacak.
public class HomeController {

    //Default mapping
    @GetMapping("/")
    public String defaultOlarakBurayaGeliyor()
    {
        return "DEFAULT GELİŞ";
    }

    @GetMapping("welcome1")
    public String welcome1()
    {
        return "welcome1";
    }

    @GetMapping("welcome2")
    public String welcome2()
    {
        return "welcome2";
    }

    @GetMapping("{str}") // GET /test/1, test/2 için ayrı ayrı metot yok path variable olarak alıyoruz.
    public String gelbakalim(@PathVariable("str") String str)
    {
        return str;
    }

    @GetMapping("/get/{id}") // test/get/1
    public String yeni(@PathVariable("id") long id)
    {
        return id + "";
    }

    @GetMapping("/get/{id1}/{id2}/{str1}") // test/get/1/10 istediğin kadar path alabilirsin.
    public String yeni2(@PathVariable("id1") long id1, @PathVariable("id2") long id2, @PathVariable("str1") String str2)
    {
        return id1 + id2 + str2;
    }

    //Request Param ?
    @GetMapping("eleman") // /test/eleman?name=tugberk&start=2015&end=2020
    public String tugberk(@RequestParam(name = "name") String str, @RequestParam(name = "start") int start, @RequestParam(name = "end") int end)
    {
        int kalan = start - end;
        StringBuilder sb = new StringBuilder();
        sb.append("Hoşgeldiniz" + str).append("\n\n\n\n" + kalan);

        return sb.toString();
    }

    //localhost:8080/test/postilegel/pathssss?asdasdadsdsdak=5asdasdasdsda&l=3ffff&f&aa=aa&t=10 ilginç ama çalışıyor bu da.
    //Post Request ile gelmek. Post ile gelirse datayı alabiliyorum
    @PostMapping("postilegel/{path}") // /test/postilegel
    public String postmap1(@RequestBody String str, @RequestParam(name = "t") int t, @PathVariable("path") String path)
    {
        int result = t + 1;
        return result + str + path;
    }


}
