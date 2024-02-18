package ro.bogdan_mierloiu.javablockchain.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.bogdan_mierloiu.javablockchain.util.SHA256Helper;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timeStamp;

    private String previousHash;

    private String hash;

    @Column(length = 1024)
    private String data;

    private String description;

    private int nonce;
    @ManyToOne(cascade = CascadeType.ALL)
    private BlockChain blockChain;

    public void generateHash() {
        String dataToHash = id + previousHash + timeStamp + nonce + data;
        this.hash = SHA256Helper.generateHash(dataToHash);
    }

    public void incrementNonce() {
        this.nonce++;
    }

    @Override
    public String toString() {
        return this.id + "-" + this.data + "-" + this.hash + "-" + this.previousHash + "-";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(id, block.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}