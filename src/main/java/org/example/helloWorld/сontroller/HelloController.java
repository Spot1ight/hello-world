package org.example.helloWorld.сontroller;

import com.itextpdf.text.DocumentException;
import org.example.helloWorld.model.Hello;
import org.example.helloWorld.service.HelloService;
import org.example.helloWorld.utils.HelloPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class HelloController {

    @Autowired
    private HelloPDF helloPDF;
    @Autowired
    private HelloService helloService;


    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping("/")
    public String getHello() {
        return "Hello World";
    }

    @GetMapping("/create")
    public String getLol() throws IOException, DocumentException {
        helloPDF.createPDF();
        return "successfully created!";
    }

    @PostMapping("/create")
    public void createFile(@RequestBody Hello hello){
        helloService.createFile(hello);
    }
}
