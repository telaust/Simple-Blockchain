package chain;


import java.util.*;


public class Block {

    public String hash; // contain digital signature
    public String previousHash;
    private String data; // simple message
    private long timeStamp;
    private int nonce;

    Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();

    }

    public String calculateHash(){

        String calculatedHash = StringUtil.applySHA256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedHash;
    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined! - " + hash);
    }
}
