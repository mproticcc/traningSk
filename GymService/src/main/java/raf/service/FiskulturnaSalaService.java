package raf.service;

import raf.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



//CRUD operacije
public interface FiskulturnaSalaService {

    Page<FiskulturnaSalaDto> findAll(Pageable pageable); //read

    FiskulturnaSalaDto addSala(FiskulturnaSalaCreateDto fiskulturnaSalaCreateDto); //create

    FiskulturnaSalaDto update(Long id, FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto); //update

    void deleteById(Long id); //delete

    FiskulturnaSalaDto updateManager(FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto);
}
