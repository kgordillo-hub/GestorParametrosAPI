package co.mlforex.forecast.repository;


import co.mlforex.forecast.model.Mensaje;
import co.mlforex.forecast.model.TransaccionInfo;

import java.util.Optional;

public interface GestorParamRepo {

    Optional<TransaccionInfo> findById(String id);
    void guardarInfo(TransaccionInfo gestorParamInfo);
}
