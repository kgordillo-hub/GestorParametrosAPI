package co.mlforex.forecast.logic;

import co.mlforex.forecast.model.Mensaje;

public class GitManager {

    public String getOpenAPIFileLink(Mensaje mensaje){
        String ubicacionArchivo = clonarRepo(mensaje.getLinkRepo(), mensaje.getBranchRepoName());
        String linkAPIFile = subirArchivoAPI(ubicacionArchivo);
        return linkAPIFile;
    }

    //Clona el repositorio y devuelve la ubicacion del archivo de spec. API
    private String clonarRepo(String repoLink, String branchName){
        //Implementar logica
        return "";
    }

    public String getLastCommitHash(Mensaje mensaje){
        return "";
    }

    //Sube el archivo de spec. a un repositorio remoto
    private String subirArchivoAPI(String ubicacionArchivo){
        //implementar logica
        return "";
    }
}
