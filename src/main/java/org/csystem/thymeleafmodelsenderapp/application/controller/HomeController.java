package org.csystem.thymeleafmodelsenderapp.application.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.csystem.thymeleafmodelsenderapp.util.StringUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.StringTokenizer;

@RestController
@RequestMapping("test") // localhost:8080 -> GET /test/welcome1 olarak aslında geliyor, hangi str'de hangi metot çağrılacak.
public class HomeController {
    @Autowired
    Environment environment;

    //Default mapping
    @GetMapping("")
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
    @PostMapping("postilegel/{id}") // /test/postilegel
    public String postmap1(@RequestBody String str, @RequestParam(name = "t") int t, @PathVariable("id") String id) throws UnirestException
    {
        String fmt = "https://api.themoviedb.org/3/movie/%s?api_key=df6f5bc48cf129a2edbc1ad6bf4196e0";
        String url = String.format(fmt, id);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        //return response.getBody().getObject().get("original_title").toString();
        return Unirest.get(url).asJson().getBody().getObject().get("original_title").toString();
    }

    //localhost:8080/test/postilegel/pathssss?asdasdadsdsdak=5asdasdasdsda&l=3ffff&f&aa=aa&t=10 ilginç ama çalışıyor bu da.
    //Post Request ile gelmek. Post ile gelirse datayı alabiliyorum
    @PostMapping("postilegel") // /test/postilegel
    public String postmap1(@RequestBody String id) throws UnirestException
    {
        String fmt = "https://api.themoviedb.org/3/movie/%s?api_key=df6f5bc48cf129a2edbc1ad6bf4196e0";
        String url = String.format(fmt, id);

        //HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        //return response.getBody().getObject().get("original_title").toString();
        return Unirest.get(url).asJson().getBody().getObject().get("original_title").toString();
    }

    //oha ya get requeste bodu @Request body ile aldım herşey senaryona bağlı.
    //localhost:8080/test/postilegel/pathssss?asdasdadsdsdak=5asdasdasdsda&l=3ffff&f&aa=aa&t=10 ilginç ama çalışıyor bu da.
    //Post Request ile gelmek. Post ile gelirse datayı alabiliyorum
    @GetMapping("gettilegelip") // /test/gettilegelip
    //RequestParam'dan parametre ? alıp, aynı zamanda request body'den str alabilirsin.
    //gerçek request zaten string byte array.
    public String getmap1(@RequestParam(name = "id") String id, @RequestBody String id1) throws UnirestException
    {
        String fmt = "https://api.themoviedb.org/3/movie/%s?api_key=df6f5bc48cf129a2edbc1ad6bf4196e0";
        String url = String.format(fmt, id1);

        //HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        //return response.getBody().getObject().get("original_title").toString();
        return Unirest.get(url).asJson().getBody().getObject().get("original_title").toString();
    }

    //Ne yapmak istediğini yaz get, post her türlü yaparsın.
    //kendime istek atıcam
    /*
    @GetMapping("kendineistekat")
    public String kendineistekat() throws UnirestException
    {
        return Unirest.get("127.0.0.1:8080/test/welcome1/").asString().getBody();
        //return Unirest.get("https://api.themoviedb.org/3/movie/550?api_key=df6f5bc48cf129a2edbc1ad6bf4196e0").asString().getBody().toString();
    }*/

    /*
        1. Buraya post ile gelsin, api_key - body'de göndersin post ile geldi ya.
        2. test/3/search/movie olarak gelsin
        3. İstersen içeride parametre alabilirim. ? - name - value, sırası önemli değil
        4. %41 -> A in URL /%42%41%42%41+%20 server tarafta karaktere dönüşüyor.
        5. Boşluk karakteri + olarak gönderilse bile server tarafta boşluk karakterine dönüştürüldüğü için
        6. changeWhiteSpaceWithPlusSignForConfiguration böyle bir metot + ile diğer api'ye istek atılması lazım. url formatında istek atılması lazım.
    */
    @PostMapping("3/search/movie")
    public String searchMovie(@RequestBody String api_key, @RequestParam(name = "query") String query)
    {
        StringTokenizer tokenizer = new StringTokenizer(api_key, "-");
        System.out.println(tokenizer.nextToken());
        api_key = api_key.split("-")[0]; //fantezi
        System.out.println(api_key);
        System.out.println(query);

        //boşlukları + yapmak
        query = StringUtil.changeWhiteSpaceWithPlusSignForConfiguration(query);

        String fmtUrl = "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s";
        String url = String.format(fmtUrl, api_key, query);

        try {
            JSONArray results = (JSONArray) Unirest.get(url).asJson().getBody().getObject().get("results");

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < results.length(); ++i) {
                JSONObject result = (JSONObject) results.get(i);
                sb.append(result.get("title") + "\n");
            }
            return sb.toString();
        } catch (UnirestException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    //https://api.themoviedb.org/3/search/movie?api_key=df6f5bc48cf129a2edbc1ad6bf4196e0&query=Jack+Reacher




}
