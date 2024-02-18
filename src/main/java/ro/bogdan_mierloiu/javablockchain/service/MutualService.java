package ro.bogdan_mierloiu.javablockchain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;
import ro.bogdan_mierloiu.javablockchain.repository.CandidateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MutualService {

    private final CandidateRepository candidateRepository;

    public List<Candidate> getInitialCandidates() {
        return candidateRepository.findAll().isEmpty() ? this.saveInitialCandidates() : candidateRepository.findAll();
    }

    public List<Candidate> saveInitialCandidates() {
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
