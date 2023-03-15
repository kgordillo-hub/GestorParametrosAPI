package co.mlforex.forecast.controller;

import co.mlforex.forecast.model.Mensaje;
import co.mlforex.forecast.model.TransaccionInfo;
import co.mlforex.forecast.service.ServicioGestorParamAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestorParamController {

    @Autowired
    ServicioGestorParamAPI servicioGestorParamAPI;

    @PostMapping("/saveAPIConfig")
    public ResponseEntity<String> saveAPIConfig(TransaccionInfo transacInfo){
        servicioGestorParamAPI.guardarParametrosAPI(transacInfo);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @PostMapping("/getAPIConfig")
    public ResponseEntity<TransaccionInfo> getAPIConfig(TransaccionInfo transacInfo){
        Mensaje msg = servicioGestorParamAPI.getParametrosAPI(transacInfo);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
