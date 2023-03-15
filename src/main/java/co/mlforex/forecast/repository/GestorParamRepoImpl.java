package co.mlforex.forecast.repository;

import co.mlforex.forecast.model.Mensaje;
import co.mlforex.forecast.model.TransaccionInfo;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class GestorParamRepoImpl implements GestorParamRepo{

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    @Override
    public Optional<TransaccionInfo> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void guardarInfo(TransaccionInfo gestorParamInfo) {

    }
}
