package co.mlforex.forecast.gestorParametrosAPI.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.UUID;

@DynamoDBTable(tableName = "GestorAPIInfo")
public class TransaccionInfo implements Serializable {

    private String UID;
    @DynamoDBAttribute
    private String nombreApp;
    @DynamoDBAttribute
    private String version;
    @DynamoDBAttribute
    private String idTransaccion;

    //Attributes
    private Mensaje mensaje;

    public TransaccionInfo(){
    }

    public TransaccionInfo(String nombreApp, String version){
        this.nombreApp = nombreApp;
        this.version = version;
        this.idTransaccion = UUID.randomUUID()+"";
        this.UID = DigestUtils.md5Hex(nombreApp.toLowerCase()+":"+version+":"+idTransaccion);
    }

    public String getNombreApp() {
        return nombreApp;
    }

    public void setNombreApp(String nombreApp) {
        this.nombreApp = nombreApp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @DynamoDBAttribute(attributeName = "mensaje")
    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    @DynamoDBHashKey(attributeName = "UID")
    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String generateUID(){
        return DigestUtils.md5Hex(nombreApp.toLowerCase()+":"+version+":"+idTransaccion);
    }
}
