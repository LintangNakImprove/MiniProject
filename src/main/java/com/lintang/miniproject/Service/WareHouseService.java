package com.lintang.miniproject.Service;

import com.lintang.miniproject.Model.WareHouse;
import com.lintang.miniproject.Repository.WareHouseRepository;
import com.lintang.miniproject.Request.WareHouseRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class WareHouseService {
    private final WareHouseRepository wareHouseRepository;
     public WareHouseService (WareHouseRepository wareHouseRepository){
         this.wareHouseRepository = wareHouseRepository;
     }
     @Transactional
//     Create
     public WareHouse tambahWareHouse(WareHouseRequest wareHouseRequest){
         WareHouse wareHouse = new WareHouse();
         wareHouse.setCode(wareHouseRequest.getCode());
         wareHouse.setName(wareHouseRequest.getName());
         wareHouse = wareHouseRepository.save(wareHouse);
         return wareHouse;
     }

//     Read
    public List<WareHouse> getWareHouse(){
         return wareHouseRepository.findAll();
    }
//    Read ById
    public WareHouse getWareHouseById(Long id){
         return wareHouseRepository.findById(id)
                 .orElseThrow(()-> new RuntimeException("Data WareHouse Dengan Id " + id + " Tidak Di Temukan"));
    }
    @Transactional
//    Update
    public  WareHouse updateWareHouse(Long id,WareHouseRequest wareHouseRequest){
         if (wareHouseRepository.existsByCodeAndIdNot(wareHouseRequest.getCode(),id)){
             throw new IllegalArgumentException("Kode Yang Sudah Di Gunakan" + wareHouseRequest.getCode());
         }

         WareHouse wareHouse = wareHouseRepository.findById(id)
                 .orElseThrow(()-> new RuntimeException("WareHouse Dengan Id:" +id+ "Tidak Di Temukan"));
         wareHouse.setCode(wareHouseRequest.getCode());
         wareHouse.setName(wareHouseRequest.getName());
         wareHouse = wareHouseRepository.save(wareHouse);
         return wareHouse;
    }
//    Delete
    public void deleteWareHouse(Long id){
         wareHouseRepository.deleteById(id);
    }
}
