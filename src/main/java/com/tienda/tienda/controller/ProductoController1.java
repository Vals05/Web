package com.tienda.tienda.controller;

import com.tienda.tienda.Service.CategoriaService;
import com.tienda.tienda.Service.ProductoService;
import com.tienda.tienda.Service.FirebaseStorageService;
import com.tienda.tienda.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class ProductoController1 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());

        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/producto/listado";
    }
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String guardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            String rutaImagen = firebaseStorageService.cargaImagen(imagenFile,
                    "producto", producto.getIdProducto());
            producto.setRutaImagen(rutaImagen);
        }
        productoService.save(producto);
        return "redirect:/producto/listado";

    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String modificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
         var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "producto/modifica";
    }
}
