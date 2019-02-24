package chain;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static final int difficulty = 5;

    public static void main( String[] args )
    {
        blockchain.add(new Block("GENESIS", "0"));
        System.out.println("trying to mine 1..");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second", blockchain.get(0).hash));
        System.out.println("trying to mine 2");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third", blockchain.get(1).hash));
        System.out.println("trying to mine 3");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("blockchain is valid? - " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe blockchain: ");
        System.out.println(blockchainJson);



    }

    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 0; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("current hashes not equal");
                return false;
            }
            if (!previousBlock.hash.equals(previousBlock.calculateHash())) {
                System.out.println("previous hashes not equal");
                return false;
            }
        }

        return true;
    }
}
