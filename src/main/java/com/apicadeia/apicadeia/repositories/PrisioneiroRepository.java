package com.apicadeia.apicadeia.repositories;

import com.apicadeia.apicadeia.model.Prisioneiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrisioneiroRepository extends JpaRepository<Prisioneiro, Integer> {
}
