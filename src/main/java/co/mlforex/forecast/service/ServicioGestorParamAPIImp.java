package co.mlforex.forecast.service;

import co.mlforex.forecast.logic.GitManager;
import co.mlforex.forecast.model.Mensaje;
import co.mlforex.forecast.model.TransaccionInfo;
import co.mlforex.forecast.repository.GestorParamRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ServicioGestorParamAPIImp implements ServicioGestorParamAPI{
    @Autowired
    GestorParamRepoImpl gestorParamRepo;

    @Override
    public void guardarParametrosAPI(TransaccionInfo transacInfo) {
        Mensaje mensaje = transacInfo.getMensaje();
        GitManager gitManager = new GitManager();
        String lastCommitHash = gitManager.getLastCommitHash(mensaje);
        mensaje.setOpenAPIFileLink(gitManager.getOpenAPIFileLink(mensaje));
        mensaje.setLastCommitHash(lastCommitHash);
        mensaje.setLinkRepo(mensaje.getLinkRepo()); //hash
        gestorParamRepo.guardarInfo(transacInfo);
    }

    @Override
    public TransaccionInfo getParametrosAPI(TransaccionInfo transacInfo) {
        Optional<TransaccionInfo> value = gestorParamRepo.findById(transacInfo.getMensaje().getLinkRepo());
        if(!value.isEmpty())
            return value.get();
        else
            return null;
    }
}
