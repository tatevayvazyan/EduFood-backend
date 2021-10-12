package am.mse.eduFood.repository;

import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.domain.Order;
import com.sun.org.apache.bcel.internal.generic.LASTORE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    Asset findByUri(String uri);


}


