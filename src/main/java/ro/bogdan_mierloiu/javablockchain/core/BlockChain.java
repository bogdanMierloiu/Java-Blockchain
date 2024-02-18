package ro.bogdan_mierloiu.javablockchain.core;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private List<Block> blockChain;

    public BlockChain() {
        this.blockChain = new ArrayList<>();
    }

    public void addBlock(Block block) {
        this.blockChain.add(block);
    }

    public List<Block> getBlockChain() {
        return this.blockChain;
    }

    public int size() {
        return this.blockChain.size();
    }

    @Override
    public String toString() {
        StringBuilder blockChain = new StringBuilder();
        for (Block block : this.blockChain)
            blockChain.append(block.toString()).append("\n");
        return blockChain.toString();
    }
}
