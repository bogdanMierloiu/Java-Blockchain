package ro.bogdan_mierloiu.javablockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.bogdan_mierloiu.javablockchain.core.Block;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
