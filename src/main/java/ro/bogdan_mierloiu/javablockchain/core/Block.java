package ro.bogdan_mierloiu.javablockchain.core;

import lombok.Getter;
import lombok.Setter;
import ro.bogdan_mierloiu.javablockchain.util.SHA256Helper;

import java.util.Date;

@Getter
@Setter
public class Block {
    private int id;
    private int nonce;
    private long timeStamp;
    private String hash;
    private String previousHash;
    private String data;

    public Block(int id, String data, String previousHash) {
        this.id = id;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        generateHash();
    }

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
}