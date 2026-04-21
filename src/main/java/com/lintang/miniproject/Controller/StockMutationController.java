package com.lintang.miniproject.Controller;

import com.lintang.miniproject.Model.StockMutation;
import com.lintang.miniproject.Request.StockMutationRequest;
import com.lintang.miniproject.Response.WebResponse;
import com.lintang.miniproject.Service.StockMutationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-mutation")
public class StockMutationController {

    private final StockMutationService stockMutationService;

    public StockMutationController(StockMutationService stockMutationService){
        this.stockMutationService = stockMutationService;
    }

    // POST /api/stock-mutation
    @PostMapping
    public WebResponse<StockMutation> tambahStockMutation(@Valid @RequestBody StockMutationRequest request){
        StockMutation stockMutation = stockMutationService.tambahStockMutation(request);
        return new WebResponse<>("success", "Stock Mutation Berhasil Ditambahkan", stockMutation);
    }

    // GET /api/stock-mutation
    @GetMapping
    public WebResponse<List<StockMutation>> getAllStockMutation(){
        List<StockMutation> stockMutations = stockMutationService.getAllStockMutation();
        return new WebResponse<>("success", "Data Stock Mutation Berhasil Di Ambil", stockMutations);
    }
}
