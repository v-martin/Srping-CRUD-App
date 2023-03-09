package lab4.lab4.controllers;

import lab4.lab4.dto.CustomUserDetails;
import lab4.lab4.dto.request.CreateHitDTO;
import jakarta.validation.Valid;
import lab4.lab4.dto.response.AllHitsDTO;
import lab4.lab4.dto.response.ResponseHitDTO;
import lab4.lab4.services.*;
import lab4.lab4.entity.Hit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hits")
public class HitController {

    private final HitService hitService;
    private final UserService userService;

    @Autowired
    public HitController(HitService hitService, UserService userService) {
        this.hitService = hitService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseHitDTO> create(@Valid CreateHitDTO createHitDto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(email);
        System.out.println(user.getEmail());
        Hit hit = this.hitService.createHit(user.getId(), createHitDto);
        return ResponseEntity.ok(
                new ResponseHitDTO(hit.getX(), hit.getY(), hit.getR(), hit.isStatus(), hit.getBeginDate(),
                        hit.getCodeTime())
        );
    }

    @GetMapping
    public ResponseEntity<List<ResponseHitDTO>> get() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(email);
        ModelMapper mapper = new ModelMapper();
        List<ResponseHitDTO> deptDTO = Arrays.asList(mapper.map(hitService.getMyHits(user.getId()), ResponseHitDTO[].class));
        return ResponseEntity.ok(deptDTO);
    }
}