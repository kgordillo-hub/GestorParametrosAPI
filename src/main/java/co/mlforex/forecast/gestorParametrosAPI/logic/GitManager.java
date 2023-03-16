package co.mlforex.forecast.gestorParametrosAPI.logic;

import co.mlforex.forecast.gestorParametrosAPI.controller.GestorParamController;
import co.mlforex.forecast.gestorParametrosAPI.model.Mensaje;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class GitManager {

    Logger logger = LoggerFactory.getLogger(GestorParamController.class);

    String gitFolder;

    public GitManager(String gitFolder) {
        this.gitFolder = gitFolder;
    }

    public Boolean validateOpenApiFile(Mensaje mensaje, String folderName) {
        Boolean cloneCompleted = clonarRepo(mensaje.getLinkRepo(), folderName);
        if(cloneCompleted){
            File jsonSpecFile = new File(gitFolder+"/SpecOpenAPI.json");
            if(jsonSpecFile.exists()){
                return validarJsonStructure(gitFolder+"/SpecOpenAPI.json");
            }
        }
        return Boolean.FALSE;
    }

    //Clona el repositorio y el contenido en B64 del archivo de spec. API
    private Boolean clonarRepo(String repoUrl, String folderName) {
        Git result=null;
        try {
            logger.info("Entra a clonar el repo");
            gitFolder = gitFolder+folderName;
            logger.info("Cloning " + repoUrl + " into " + gitFolder);
            result = Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(Paths.get(gitFolder).toFile())
                    .call();

            logger.info("Completed Cloning");
            return Boolean.TRUE;
        } catch (GitAPIException e) {
            logger.debug("Exception occurred while cloning repo");
            logger.debug(e.getMessage());
        }finally {
            if(result!=null){
                result.close();
            }
        }
        return Boolean.FALSE;
    }

    public String getLastCommitHash(Mensaje mensaje) {
        return "";
    }

    //Convierte archivo a B64
    public String getContentArchivoAPI(File archivo) throws IOException {
        return Base64.getEncoder().encodeToString(Files.readAllBytes(archivo.toPath()));
    }

    private Boolean validarJsonStructure(final String jsonFilePath) {
        String schema_path = System.getProperty("user.dir") + "/MLForex_schema_validator.json";
        try (final FileInputStream fipSchema = new FileInputStream(schema_path);
             final FileInputStream fipJson = new FileInputStream(jsonFilePath)) {
            // create instance of the ObjectMapper class
            final JSONObject jsonSchema = new JSONObject(new JSONTokener(fipSchema));
            final JSONObject jsonFile = new JSONObject(new JSONTokener(fipJson));
            final Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonFile);

            return Boolean.TRUE;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ValidationException e) {
            logger.error("Esquema invalido " + e.getMessage());
            return Boolean.FALSE;
        }
    }
}
