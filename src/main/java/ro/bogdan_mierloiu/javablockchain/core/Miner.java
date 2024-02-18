package ro.bogdan_mierloiu.javablockchain.core;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ro.bogdan_mierloiu.javablockchain.util.Constant;

@Slf4j
@Getter
public class Miner {
    private double reward;

    public void mine(Block block, BlockChain blockChain) {
        while (notGoldenHash(block)) {
            block.generateHash();
            block.incrementNonce();
        }

        log.info("Block has been mined...");
        log.info("Hash is: " + block.getHash());

        blockChain.addBlock(block);
        reward += Constant.MINER_REWARD;
    }

    public boolean notGoldenHash(Block block) {

        String leadingZeros = new String(new char[Constant.DIFFICULTY]).replace('\0', '0');
        return !block.getHash().substring(0, Constant.DIFFICULTY).equals(leadingZeros);
    }

}
