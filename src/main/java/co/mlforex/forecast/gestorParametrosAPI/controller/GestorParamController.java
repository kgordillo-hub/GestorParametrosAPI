package co.mlforex.forecast.gestorParametrosAPI.controller;

import co.mlforex.forecast.gestorParametrosAPI.model.TransaccionInfo;
import co.mlforex.forecast.gestorParametrosAPI.service.ServicioGestorParamAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestorParamController {

    Logger logger = LoggerFactory.getLogger(GestorParamController.class);

    @Autowired
    ServicioGestorParamAPI servicioGestorParamAPI;

    @PostMapping("/validarAPIConfig")
    public ResponseEntity<String> validarAPIConfig(@RequestBody TransaccionInfo transacInfo) {
        try {
            if(servicioGestorParamAPI.validarAPISpec(transacInfo)){
                return new ResponseEntity<>("Saved", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Invalid JSON spec", HttpStatus.NO_CONTENT);
            }
        } catch (final Exception e) {
            logger.error("Error en GestorParamController:saveAPIConfig " + e.getMessage());
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getAPIConfig")
    public ResponseEntity<TransaccionInfo> getAPIConfig(@RequestBody TransaccionInfo transacInfo) {
        try{
            TransaccionInfo msg = servicioGestorParamAPI.getParametrosAPI(transacInfo);
            return new ResponseEntity<>(msg, HttpStatus.OK);

        }catch(final Exception e){
            logger.error("Error en GestorParamController:getAPIConfig " + e.getMessage());
            return new ResponseEntity<>(new TransaccionInfo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
