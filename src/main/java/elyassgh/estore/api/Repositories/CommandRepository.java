package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
}
