package co.mlforex.forecast.gestorParametrosAPI.repository;


import co.mlforex.forecast.gestorParametrosAPI.model.TransaccionInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface GestorParamRepo extends CrudRepository<TransaccionInfo, String> {


}
