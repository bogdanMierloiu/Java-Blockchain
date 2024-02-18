package ro.bogdan_mierloiu.javablockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;

public interface BlockChainRepository extends JpaRepository<BlockChain, Long> {
}
