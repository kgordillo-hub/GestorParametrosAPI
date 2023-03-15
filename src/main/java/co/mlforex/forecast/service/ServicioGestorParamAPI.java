package co.mlforex.forecast.service;

import co.mlforex.forecast.model.Mensaje;
import co.mlforex.forecast.model.TransaccionInfo;

public interface ServicioGestorParamAPI {

    void guardarParametrosAPI(TransaccionInfo transacInfo);

    TransaccionInfo getParametrosAPI(TransaccionInfo transacInfo);
}
