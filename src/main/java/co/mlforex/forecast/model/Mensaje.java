package co.mlforex.forecast.model;

import com.amazonaws.util.json.JSONObject;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private String linkRepo;
    private String branchRepoName;
    private String lastCommitHash;
    private String idUsuario;
    private String openAPIFileLink;
    private Boolean openAPIFileCorrect;
    private JSONObject openAPIFileContent;

    public String getLinkRepo() {
        return linkRepo;
    }

    public void setLinkRepo(String linkRepo) {
        this.linkRepo = linkRepo;
    }

    public String getBranchRepoName() {
        return branchRepoName;
    }

    public void setBranchRepoName(String branchRepoName) {
        this.branchRepoName = branchRepoName;
    }

    public String getLastCommitHash() {
        return lastCommitHash;
    }

    public void setLastCommitHash(String lastCommitHash) {
        this.lastCommitHash = lastCommitHash;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getOpenAPIFileLink() {
        return openAPIFileLink;
    }

    public void setOpenAPIFileLink(String openAPIFileLink) {
        this.openAPIFileLink = openAPIFileLink;
    }

    public Boolean getOpenAPIFileCorrect() {
        return openAPIFileCorrect;
    }

    public void setOpenAPIFileCorrect(Boolean openAPIFileCorrect) {
        this.openAPIFileCorrect = openAPIFileCorrect;
    }

    public JSONObject getOpenAPIFileContent() {
        return openAPIFileContent;
    }

    public void setOpenAPIFileContent(JSONObject openAPIFileContent) {
        this.openAPIFileContent = openAPIFileContent;
    }
}
