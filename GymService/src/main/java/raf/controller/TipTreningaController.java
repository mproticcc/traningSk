package raf.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.dto.*;
import raf.security.CheckSecurity;
import raf.service.TipTreningaService;

@RestController
@RequestMapping("/tiptreninga")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TipTreningaController {

    private TipTreningaService tipTreningaService;

    @GetMapping("/getAllTypes")
//    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT","ROLE_MANAGER"})
    public ResponseEntity<Page<TipTreningaDto>> getAllTipovi( Pageable pageable){
        return new ResponseEntity<>(tipTreningaService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles={"ROLE_ADMIN,ROLE_MANAGER"})
    public ResponseEntity<TipTreningaDto> saveTip(@RequestBody @Valid TipTreningaCreateDto tipTreningaCreateDto){
        return new ResponseEntity<>(tipTreningaService.add(tipTreningaCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    @CheckSecurity(roles={"ROLE_ADMIN,ROLE_MANAGER"})
    public ResponseEntity<TipTreningaDto> updateTip(@RequestBody @Valid TipTreningaUpdateDto tipTreningaUpdateDto){
        return new ResponseEntity<>(tipTreningaService.update(tipTreningaUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping
    @CheckSecurity(roles={"ROLE_ADMIN,ROLE_MANAGER"})
    public ResponseEntity<TipTreningaDto> deleteTip(@RequestBody @Valid TipTreningaUpdateDto tipTreningaUpdateDto){
        tipTreningaService.deleteById(tipTreningaUpdateDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
