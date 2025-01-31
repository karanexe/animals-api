package cx.catapult.animals.web;

import cx.catapult.animals.domain.Animal;
import cx.catapult.animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/1/animals", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    Collection<Animal> all(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String colour,
            @RequestParam(required = false) String type
    ) {
        return service.filter(name, description, colour, type);
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    Animal get(@PathVariable String id) {
        return service.get(id);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Animal create(@RequestBody Animal animal) {
        return service.create(animal);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Animal update(@RequestBody Animal animal) {
        return service.update(animal);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void delete(@PathVariable String id) {
        service.delete(id);
    }

}
