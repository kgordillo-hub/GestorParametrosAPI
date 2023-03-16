package co.mlforex.forecast.gestorParametrosAPI.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import java.io.Serializable;

@DynamoDBDocument
public class Mensaje implements Serializable {

    @DynamoDBAttribute
    private String linkRepo;
    @DynamoDBAttribute
    private String branchRepoName;
    @DynamoDBAttribute
    private String lastCommitHash;
    @DynamoDBAttribute
    private String idUsuario;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute
    private Boolean openAPIFileCorrect;
    @DynamoDBAttribute
    private String openAPIFileContent;

    public Mensaje(){

    }

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


    public Boolean getOpenAPIFileCorrect() {
        return openAPIFileCorrect;
    }

    public void setOpenAPIFileCorrect(Boolean openAPIFileCorrect) {
        this.openAPIFileCorrect = openAPIFileCorrect;
    }

    public String getOpenAPIFileContent() {
        return openAPIFileContent;
    }

    public void setOpenAPIFileContent(String openAPIFileContent) {
        this.openAPIFileContent = openAPIFileContent;
    }
}
