package com.example.springdata.services;

import com.example.springdata.Repositories.ProductRepository;
import com.example.springdata.entity.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Productos> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Productos> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Productos save(Productos product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<Productos> update(Long id, Productos product) {
        Optional<Productos> productosOptional = productRepository.findById(id);
        if(productosOptional.isPresent()){
            Productos productoDB = productosOptional.orElseThrow();
            productoDB.setNombreProducto(product.getNombreProducto());
            productoDB.setDescripcion(product.getDescripcion());
            productoDB.setPrecio(product.getPrecio());
            productoDB.setCategoria(product.getCategoria());
            productoDB.setCantidadEnStock(product.getCantidadEnStock());
           // productoDB.setPedidos(product.getPedidos());
            return Optional.of(productRepository.save(productoDB));
        }

        return productosOptional;
    }

    @Override
    @Transactional
    public Optional<Productos> delete(Long id) {
        Optional<Productos> productosOptional = productRepository.findById(id);
        productosOptional.ifPresent(productosDB -> productRepository.delete(productosDB));
        return productosOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> findAllByNombreProducto(String nombre) {
        return productRepository.findAllByNombreProducto(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> findProductosByPrecioBetween(float precionMin, float precioMax) {
        return productRepository.findProductosByPrecioBetween(precionMin,precioMax);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> findProductosByCategoria(String categoria) {
        return productRepository.findProductosByCategoria(categoria);
    }

}
