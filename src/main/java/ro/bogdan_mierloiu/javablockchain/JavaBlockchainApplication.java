package ro.bogdan_mierloiu.javablockchain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.bogdan_mierloiu.javablockchain.core.Block;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;
import ro.bogdan_mierloiu.javablockchain.core.Miner;
import ro.bogdan_mierloiu.javablockchain.util.Constant;

@SpringBootApplication
@Slf4j
public class JavaBlockchainApplication {

    public static void main(String[] args) {

        BlockChain blockChain = new BlockChain();
        Miner miner = new Miner();

        Block block0 = new Block(0, "Transaction 1", Constant.GENESIS_PREV_HASH);
        miner.mine(block0, blockChain);

        Block block1 = new Block(1, "Transaction 2", blockChain.getBlockChain().get(blockChain.size() - 1).getHash());
        miner.mine(block1, blockChain);

        Block block = new Block(2, "Transaction 3", blockChain.getBlockChain().get(blockChain.size() - 1).getHash());
        miner.mine(block, blockChain);

        log.info("\nBlockchain:\n" + blockChain);
        log.info("Miner reward: " + miner.getReward());
    }
}
