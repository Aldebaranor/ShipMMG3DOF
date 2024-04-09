package song.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import song.model.Input;
import song.service.Simulation;

@Controller
@RequestMapping("/free")
public class motionController {

    @Autowired
    private Simulation simulation;

    //n_box =  [13.5] # [2,3,4,5,6,7,8,9,10,11,12, 13, 13.5, 14,15, 16,17,18,19,20]      # 模型试验是13.5转/s
    //Dertabox = [10, 35]  #np.arange(1,35)  #np.arange(25,35,5)  #[1,5,10,15,20,25,30,35]   #5,10,15,20,25,30,35
    //

    @PostMapping(value = "/oneround")
    public void oneround(@RequestBody Input input){
        simulation.oneround(input.getN(),input.getDerta(),input.getPathFinal());
    }

    @PostMapping (value ="/oneZ")
    public void oneZ(@RequestBody Input input){
        simulation.oneZ(input.getN(),input.getDerta(),input.getPathFinal());
    }

}
