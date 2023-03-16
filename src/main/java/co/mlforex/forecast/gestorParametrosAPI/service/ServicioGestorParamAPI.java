package co.mlforex.forecast.gestorParametrosAPI.service;

import co.mlforex.forecast.gestorParametrosAPI.model.TransaccionInfo;

public interface ServicioGestorParamAPI {

    Boolean validarAPISpec(TransaccionInfo transacInfo);

    TransaccionInfo getParametrosAPI(TransaccionInfo transacInfo);
}
