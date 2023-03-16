package co.mlforex.forecast.gestorParametrosAPI.service;

import co.mlforex.forecast.gestorParametrosAPI.logic.GitManager;
import co.mlforex.forecast.gestorParametrosAPI.logic.notification.NotificadorSns;
import co.mlforex.forecast.gestorParametrosAPI.model.Mensaje;
import co.mlforex.forecast.gestorParametrosAPI.model.TransaccionInfo;
import co.mlforex.forecast.gestorParametrosAPI.repository.GestorParamRepo;
import com.google.gson.GsonBuilder;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ServicioGestorParamAPIImp implements ServicioGestorParamAPI {

    Logger logger = LoggerFactory.getLogger(ServicioGestorParamAPIImp.class);
    @Autowired
    GestorParamRepo gestorParamRepo;

    @Value("${git.repo.folder}")
    String repoFolder;

    @Value("${aws.sns.topic.gestorParamAPIOut.arn}")
    String snsTopic;

    @Override
    public Boolean validarAPISpec(TransaccionInfo transacInfo) {
        try {
            if (findByAppV(transacInfo) == null) {
                logger.info("Entra a la validar el API Spec");
                Mensaje mensaje = transacInfo.getMensaje();
                GitManager gitManager = new GitManager(repoFolder);
                String lastCommitHash = gitManager.getLastCommitHash(mensaje);
                String folderName = mensaje.getLinkRepo().substring(mensaje.getLinkRepo().lastIndexOf('/') + 1);
                String localRepoLink = repoFolder + folderName;
                File repoDir = new File(localRepoLink);
                if (repoDir.exists()) {
                    FileUtils.deleteDirectory(repoDir);
                }
                Boolean state = Boolean.FALSE;
                if (gitManager.validateOpenApiFile(mensaje, folderName)) {

                    mensaje.setOpenAPIFileContent(gitManager.getContentArchivoAPI(new File(localRepoLink + "/SpecOpenAPI.json")));
                    mensaje.setLastCommitHash(lastCommitHash);
                    mensaje.setOpenAPIFileCorrect(Boolean.TRUE);
                    //Se limpia el fichero
                    transacInfo.setUID(transacInfo.generateUID());
                    gestorParamRepo.save(transacInfo);
                    state = Boolean.TRUE;
                } else {
                    mensaje.setOpenAPIFileCorrect(Boolean.FALSE);
                    //Se limpia el fichero
                    transacInfo.setUID(transacInfo.generateUID());
                    gestorParamRepo.save(transacInfo);
                    state = Boolean.FALSE;
                }
                final String message = new GsonBuilder().disableHtmlEscaping().create().toJson(transacInfo);
                new NotificadorSns().publishMessageSns(message, snsTopic);

                return state;
            }
        } catch (final Exception e) {
            //e.printStackTrace();
            logger.error("Error en ServicioGestorParamAPIImp:validarAPISpec: " + e.getMessage());
        }

        return Boolean.FALSE;
    }

    @Override
    public TransaccionInfo getParametrosAPI(TransaccionInfo transacInfo) {
        Optional<TransaccionInfo> value = gestorParamRepo.findById(transacInfo.getMensaje().getLinkRepo());
        if (!value.isEmpty())
            return value.get();
        else
            return null;
    }

    private TransaccionInfo findByAppV(TransaccionInfo transaccionInfo) {
        final Iterable<TransaccionInfo> transactions = gestorParamRepo.findAll();
        if (transactions != null) {
            final Iterator<TransaccionInfo> it = transactions.iterator();
            while (it.hasNext()) {
                TransaccionInfo ti = it.next();
                String nombreApp = ti.getNombreApp().toLowerCase();
                String version = ti.getVersion().toLowerCase();
                if (transaccionInfo.getNombreApp().equals(nombreApp) && transaccionInfo.getVersion().equals(version)
                        && ti.getMensaje().getOpenAPIFileCorrect()) {
                    return ti;
                }
            }
        }
        return null;
    }
}
