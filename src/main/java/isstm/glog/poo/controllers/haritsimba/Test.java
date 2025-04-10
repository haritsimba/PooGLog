package isstm.glog.poo.controllers.haritsimba;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
@AllArgsConstructor
public class Test {
    @GetMapping(path = "")
    public String initializationTest(){
        return "It's working";
    }
}
