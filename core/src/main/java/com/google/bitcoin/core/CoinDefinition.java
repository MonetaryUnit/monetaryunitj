package com.google.bitcoin.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Map;

public class CoinDefinition {
    private static final Logger log = LoggerFactory.getLogger(CoinDefinition.class);

    public static final String coinName = "MonetaryUnit";
    public static final String coinTicker = "MUE";
    public static final String coinURIScheme = "monetaryunit";
    public static final String coinURIScheme2 = "monetaryunit";
    public static final String coinInternalName = "monetaryunit";
    public static final String cryptsyMarketId = "2";
    public static final String cryptsyMarketCurrency = "BTC";
    public static final String PATTERN_PRIVATE_KEY_START = "[5]";

    public static String lowerCaseCoinName() { return coinName.toLowerCase(); }

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://muechain.info/";
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://muechain.info/";
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/";
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/";
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/";

    public static final String BLOCKEXPLORER_PATH_URL_TEST = "block/";


    public static final String DONATION_ADDRESS = "7NYjAqzXNdtBhRxM3Jw2XxjVsVsyP9LMjM";  //HashEngineering donation QRK address

    enum CoinHash {
        SHA256,
        scrypt,
        quark
    };
    public static final CoinHash coinHash = CoinHash.quark;

    public static boolean checkpointFileSupport = true;
    public static int checkpointDaysBack = 1;
    //Original Values
    public static final int TARGET_TIMESPAN = (int)(10 * 40);  // 10 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(40);  // 30 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //20 blocks

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;
    }
    public static final int getIntervalCheckpoints() {
            return INTERVAL*900;

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            return TARGET_TIMESPAN;
    }
    public static int getMaxTimeSpan(int value, int height, boolean testNet)
    {
            return value * 40 / 100;
    }
    public static int getMinTimeSpan(int value, int height, boolean testNet)
    {
            return value / 2;
    }
    public static int spendableCoinbaseDepth = 100; //main.h: static const int COINBASE_MATURITY
    public static BigInteger COIN = BigInteger.valueOf(100000);
    public static final BigInteger MAX_MONEY = BigInteger.valueOf(1000000000000000L).multiply(COIN);                 //main.h:  MAX_MONEY
    //public static final String MAX_MONEY_STRING = "200000000";     //main.h:  MAX_MONEY


    public static BigInteger CENT = BigInteger.valueOf(1000);
    public static BigInteger mCOIN = BigInteger.valueOf(100);

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(10);   // MIN_TX_FEE
    public static final BigInteger DEFAULT_MIN_RELAY_TX_FEE = BigInteger.valueOf(100);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = CoinDefinition.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 70002;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 70000;        //version.h MIN_PROTO_VERSION

    public static final int BLOCK_CURRENTVERSION = 112;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 5 * 1000 * 1000;


    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client
    public static boolean supportsIrcDiscovery() {
        return PROTOCOL_VERSION <= 70000;
    }

    public static final int Port    = 29948;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 39948;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 15;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 9;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS

    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0x04050504;      //0xfb, 0xc0, 0xb6, 0xdb

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0fffffL);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1404668205L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (139785);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "0000070e6b650e7a6f20e015031b74c1f7e2b25ed4e419d8825ab9cc7eccfa92"; //main.cpp: hashGenesisBlock
    static public int genesisBlockValue = 0;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer

    static public String genesisTxInBytes = "a2cc3c1d8ab6e50e80464693199eefdd172d45c4129998394e636e47b5621364";
    static public String genesisTxOutBytes = "a2cc3c1d8ab6e50e80464693199eefdd172d45c4129998394e636e47b5621364";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "dnsseed.monetaryunit.tk",
            "dnsseed2.monetaryunit.tk",
            "server4.cryptex.biz",
            "104.131.125.97",
            "104.236.152.29",
            "64.111.58.118",
            "162.243.102.105",
            "119.242.148.23",
            "178.62.63.61",
            "162.255.116.196",
            "151.80.206.100",
            "107.150.39.42",
    };
    public static int minBroadcastConnections = 0;   //0 for default; we need more peers.

    //
    // TestNet - monetaryunit - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 119;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 199;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x04050504; //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "0000070e6b650e7a6f20e015031b74c1f7e2b25ed4e419d8825ab9cc7eccfa92";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0fffffL);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1404668205L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (139785);                         //main.cpp: LoadBlockIndex


    static final long _COIN = 1000000000;
    static final BigInteger nGenesisBlockRewardCoin = BigInteger.valueOf(0 * _COIN);
    static final BigInteger nBlockRewardStartCoin = BigInteger.valueOf(40 * _COIN);
    static final BigInteger nBlockRewardMinimumCoin = COIN;

    //main.cpp GetBlockValue(height, fee)
    static final BigInteger GetBlockValue(int nHeight)
    {
        if (nHeight == 0)
        {
            return nGenesisBlockRewardCoin;
        }

        BigInteger nSubsidy = BigInteger.valueOf(40L * 1000000000L);

        // Subsidy is cut in half every 60480 blocks (21 days)
        //nSubsidy >>= (nHeight / 60480);
        nSubsidy = nSubsidy.shiftRight(nHeight / 60480); //TODO

        // Minimum subsidy
        if (nSubsidy.compareTo(nBlockRewardMinimumCoin) < 0)
        {
            nSubsidy = nBlockRewardMinimumCoin;
        }

        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 60480;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // digitalcoin: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "044160a269d0250990b4517566b47e24d2a94ba458edfe856d4a63c9c62cc3aa6354cddbc9371313af65616675fa009ab13aac2bb0f5ce6bf7b8e91861765144dc";
    public static final String TESTNET_SATOSHI_KEY = "04f98a3941171ec7de8b83593aa6f7459a7605fb055c3e82c330ef89e7a46b78efeaa1e15580dddf694cf668fcc57b3aa0a47555e33aa7b0cb86ef6393975dc8f4";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.monetaryunit.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.monetaryunit.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.monetaryunit.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put( 0,      new Sha256Hash("0000070e6b650e7a6f20e015031b74c1f7e2b25ed4e419d8825ab9cc7eccfa92"));
        checkpoints.put( 1,      new Sha256Hash("000008a6e7c1c9547c5b2e7bc1e30f813cc9684428e11e8bbc2f0f270943f318"));
        checkpoints.put( 50000,  new Sha256Hash("00000010be6026f1f993dc2d51654ffeab948269cc067ea0bd3760c87ecfc7d0"));
        checkpoints.put( 100000, new Sha256Hash("000000380d10bb4bb67ff8ef4c193f9a83a9d390389138c8a690722fb1ee2094"));
        checkpoints.put( 150000, new Sha256Hash("000000020ee690f42fb7937038de393a734c7b0094b0185491c8483038b54a79"));
        checkpoints.put( 200000, new Sha256Hash("0000000089e209620a4819d46a7bdbba03924d3af1ae6f7668d8cc977d4e542c"));
        checkpoints.put( 250000, new Sha256Hash("000000010f69e2769cdacd033779acf44c7dc1e862eb5314b7179088d1c38b1e"));
        checkpoints.put( 281000, new Sha256Hash("00000000b1fb0f01e1eca91c8f6b869ec339f64210afeb793a5d75fc6c26b87b"));
        checkpoints.put( 350000, new Sha256Hash("000000019353d980a7110b404f57db814de288239bdfed90a77618b5b02cc125"));
        checkpoints.put( 400000, new Sha256Hash("00000002eea94d836af8165f504d5f6d4994fa91a9eb3a76ff6113fa7466c05b"));
        checkpoints.put( 450000, new Sha256Hash("00000002b5fec4173b250fe046fc7416321df7f89ee54bb8e74688b10358a2c5"));
        checkpoints.put( 470000, new Sha256Hash("00000001a7e2ebc09c3e8df413180b800c0a61f26fe744e539ddd339f89a468f"));
        checkpoints.put( 490000, new Sha256Hash("00000000dfba6baf869f8b5b43568b3008531046fef5a6caf83ca2082b264e21"));
        checkpoints.put( 500000, new Sha256Hash("0000000041e7a379e70a0bd8e2fcb97e9badd40199df9d4232ede49c87706a88"));
        checkpoints.put( 510000, new Sha256Hash("000000000249a0638e89196a806350de4c302687c8939486875dd26faf102838"));
        checkpoints.put( 520000, new Sha256Hash("000000006f3915dd9fceb71a3bcd7db79f069a7a3f81f0292eb69422628bb7b5"));
        checkpoints.put( 530000, new Sha256Hash("0000000028aa5e234f9923e3b600ed15faf597e9adea8c0a63cc407122fd5452"));
        checkpoints.put( 540000, new Sha256Hash("0000000029695216538bc6f2ed706237a2a7fc120ab53f37a629d69735babde4"));

    }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "7JT3iZKJ2QcwjQ2bqS7bgqZsNR8JWtVY58";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "5nzSTUE1Z1RhhVxX2LGgfqRREC5SLavJMwgQzukE3iWn8V2sK9t";



}
