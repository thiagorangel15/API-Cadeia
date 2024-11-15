package com.apicadeia.apicadeia.repositories;

import com.apicadeia.apicadeia.model.Cela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CelaRepository extends JpaRepository<Cela, Integer> {
}
