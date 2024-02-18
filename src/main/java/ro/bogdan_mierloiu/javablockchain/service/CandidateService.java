package ro.bogdan_mierloiu.javablockchain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;
import ro.bogdan_mierloiu.javablockchain.repository.CandidateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Transactional
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getInitialCandidates() {
        return candidateRepository.findAll().isEmpty() ? this.saveCandidates() : candidateRepository.findAll();
    }

    public List<Candidate> saveCandidates() {
        Candidate bogdan = Candidate.builder()
                .name("Bogdan")
                .description("software engineer")
                .votes(0L)
                .build();
        Candidate andreea = Candidate.builder()
                .name("Andreea")
                .description("artist")
                .votes(0L)
                .build();
        return candidateRepository.saveAll(List.of(bogdan, andreea));
    }
}
