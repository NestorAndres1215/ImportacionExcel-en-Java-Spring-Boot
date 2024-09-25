package com.importacion.demo.service;

import com.importacion.demo.model.Producto;
import com.importacion.demo.repository.ProductoRepository;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;
    @Override
    public List<Producto> importarExcel(MultipartFile file) throws IOException {
        List<Producto> productos = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(0);

            if (sheet.getPhysicalNumberOfRows() > 1) {
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // Comenzar desde 1 para omitir el encabezado
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Producto producto = new Producto();

                        // Manejo del código
                        Cell codigoCell = row.getCell(0);
                        String codigo;

                        System.out.println("Tipo de celda para código: " + codigoCell.getCellType());

                        if (codigoCell.getCellType() == CellType.STRING) {
                            codigo = codigoCell.getStringCellValue();
                        } else if (codigoCell.getCellType() == CellType.NUMERIC) {
                            // Convierte el número a String
                            codigo = String.valueOf((int) codigoCell.getNumericCellValue());
                        } else {
                            codigo = "";
                        }
                        producto.setCodigo(codigo);

                        // Manejo del nombre
                        String nombre = row.getCell(1).getCellType() == CellType.STRING ? row.getCell(1).getStringCellValue() : "";
                        producto.setNombre(nombre);

                        // Manejo del precio
                        double precio = row.getCell(2).getCellType() == CellType.NUMERIC ? row.getCell(2).getNumericCellValue() : 0.0;
                        producto.setPrecio(precio);

                        // Manejo de la descripción
                        String descripcion = row.getCell(3).getCellType() == CellType.STRING ? row.getCell(3).getStringCellValue() : "";
                        producto.setDescripcion(descripcion);

                        // Manejo de la cantidad
                        int cantidad = (int) (row.getCell(4).getCellType() == CellType.NUMERIC ? row.getCell(4).getNumericCellValue() : 0);
                        producto.setCantidad(cantidad);

                        // Manejo de la categoría
                        String categoria = row.getCell(5).getCellType() == CellType.STRING ? row.getCell(5).getStringCellValue() : "";
                        producto.setCategoria(categoria);

                        // Manejo de la disponibilidad
                        int disponibleValue = 0; // Valor por defecto
                        if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                            disponibleValue = (int) row.getCell(6).getNumericCellValue();
                        }
                        boolean disponible = (disponibleValue == 1); // 1 es true, 2 es false
                        producto.setDisponible(disponible);

                        productos.add(producto);
                    }
                }
            }
        }

        // Guardar todos los productos en la base de datos
        return productoRepository.saveAll(productos);
    }

}
