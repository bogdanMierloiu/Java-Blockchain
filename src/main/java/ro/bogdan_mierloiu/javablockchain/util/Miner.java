package ro.bogdan_mierloiu.javablockchain.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.bogdan_mierloiu.javablockchain.core.Block;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;

import java.util.List;

@Slf4j
@Getter
@Component
public class Miner {

    private double reward;

    public void mine(Block block, BlockChain blockChain) {
        if (!isValidChain(blockChain)) {
            log.error("Blockchain is not valid...");
            return;
        }
        while (notGoldenHash(block)) {
            block.generateHash();
            block.incrementNonce();
        }

        log.info("Block has been mined...");
        log.info("Hash is: " + block.getHash());

        blockChain.addBlock(block);
        reward += Constant.MINER_REWARD;
    }

    public boolean isValidChain(BlockChain blockChain) {
        List<Block> chain = blockChain.getChain();
        if (chain.isEmpty()) {
            return true;
        }
        if (chain.get(0).getPreviousHash().equals(Constant.GENESIS_PREV_HASH)) {
            return true;
        }
        for (int i = 1; i <= chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
            if (!currentBlock.getHash().equals(
                    SHA256Helper.generateHash(
                            currentBlock.getId()
                                    + currentBlock.getPreviousHash()
                                    + currentBlock.getTimeStamp()
                                    + currentBlock.getNonce()
                                    + currentBlock.getData()))) {
                return false;
            }
        }
        return true;
    }

    public boolean notGoldenHash(Block block) {
        String leadingZeros = new String(new char[Constant.DIFFICULTY]).replace('\0', '0');
        return !block.getHash().substring(0, Constant.DIFFICULTY).equals(leadingZeros);
    }

}
