package ro.bogdan_mierloiu.javablockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
