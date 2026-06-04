package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.model.rest.ShapeModel;
import de.andwari.memory.backend.service.ShapeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shapes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ShapeRestController {

    private final ShapeService shapeService;

    @GetMapping(produces = "application/json")
    public List<ShapeModel> getShapes() {
        return shapeService.getAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ShapeModel createShape(@RequestBody ShapeModel shape) {
        return shapeService.create(shape);
    }
}
