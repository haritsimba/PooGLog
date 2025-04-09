package isstm.glog.poo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
@AllArgsConstructor
public class FrTest {
    @GetMapping(path = "")
    public String initializationTest(){
        return "It's working";
    }
}
