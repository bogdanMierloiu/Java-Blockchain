package ro.bogdan_mierloiu.javablockchain.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@AllArgsConstructor
public class BlockChain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "blockChain")
    private List<Block> chain;

    public BlockChain() {
        this.chain = new ArrayList<>();
    }

    public void addBlock(Block block) {
        this.chain.add(block);
    }

    public int size() {
        return this.chain.size();
    }

    @Override
    public String toString() {
        return "BlockChain{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockChain that = (BlockChain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
