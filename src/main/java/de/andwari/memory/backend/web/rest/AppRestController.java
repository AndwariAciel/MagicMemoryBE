package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.service.SetUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("app")
@RequiredArgsConstructor
public class AppRestController {

    private final SetUpdateService setUpdateService;

    @GetMapping("/test")
    public void test() {
        setUpdateService.updateSets();
    }

}
