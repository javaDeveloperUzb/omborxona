package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputDTO;

import java.util.List;

public interface InputService{
    ApiResponse addInput(InputDTO inputDTO);

    ApiResponse getInputById(Integer id);

    List<Input> getAllInput();

    ApiResponse editInput(Integer id, InputDTO inputDTO);

    ApiResponse deleteInput(Integer id);
}
