package song.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import song.service.Simulation;

@Controller
@RequestMapping("/free")
public class motionController {

    @Autowired
    private Simulation simulation;

//    @PostMapping("/oneround")
//    @ResponseBody
//    public void oneround(){
//        simulation.oneround(1,0.0,0.0);
//    }

    @GetMapping("/oneround")
    @ResponseBody
    public void oneround(){
        simulation.oneround(1,0.0,0.0);
    }

}
