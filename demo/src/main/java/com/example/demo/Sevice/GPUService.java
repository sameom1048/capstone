package com.example.demo.Sevice;

import com.example.demo.Model.GPU;
import com.example.demo.Repository.GPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GPUService {

    @Autowired
    private GPURepository gpuRepository;

    public List<GPU> findAll() {
        return gpuRepository.findAll();
    }

    public Optional<GPU> findById(Long id) {
        return gpuRepository.findById(id);
    }

    public GPU save(GPU gpu) {
        return gpuRepository.save(gpu);
    }

    public void deleteById(Long id) {
        gpuRepository.deleteById(id);
    }

}