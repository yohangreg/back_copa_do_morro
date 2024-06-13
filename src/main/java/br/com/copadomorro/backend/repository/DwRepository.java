package br.com.copadomorro.backend.repository;

import br.com.copadomorro.backend.entity.Dw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DwRepository extends JpaRepository<Dw, Long> {

}
