package com.importacion.demo.service;
import com.importacion.demo.model.Producto;
import com.importacion.demo.repository.ProductoRepository;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProductoService {
        List<Producto> importarExcel(MultipartFile file) throws IOException;
    }

