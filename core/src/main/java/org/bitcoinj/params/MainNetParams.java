/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1e0fffffL);
        dumpedPrivateKeyHeader = 128 + 15;
        addressHeader = 15;
        p2shHeader = 9;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 29948;
        packetMagic = 0x04050504L;
        bip32HeaderPub = 0x4d554550; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x4d554553; //The 4 byte header that serializes in base58 to "xprv"

        genesisBlock.setMerkleRoot(Sha256Hash.wrap("a2cc3c1d8ab6e50e80464693199eefdd172d45c4129998394e636e47b5621364"));
        genesisBlock.setDifficultyTarget(0x1e0fffffL);
        genesisBlock.setTime(1404668205L);
        genesisBlock.setNonce(139785);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 0;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("0000070e6b650e7a6f20e015031b74c1f7e2b25ed4e419d8825ab9cc7eccfa92"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        // checkpoints.put(91722, Sha256Hash.wrap("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        // checkpoints.put(91812, Sha256Hash.wrap("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        // checkpoints.put(91842, Sha256Hash.wrap("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        // checkpoints.put(91880, Sha256Hash.wrap("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        // checkpoints.put(200000, Sha256Hash.wrap("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));

        dnsSeeds = new String[] {
          "dnsseed.exapool.com",
          "dnsseed.muewallet.com"
        };

        httpSeeds = new HttpDiscovery.Details[] {
          // // Mike Hearn
          // new HttpDiscovery.Details(
          //         ECKey.fromPublicOnly(Utils.HEX.decode("027a79143a4de36341494d21b6593015af6b2500e720ad2eda1c0b78165f4f38c4")),
          //         URI.create("http://main.seed.vinumeris.com/peers")
          // ),
          // // Andreas Schildbach
          // new HttpDiscovery.Details(
          //         ECKey.fromPublicOnly(Utils.HEX.decode("0238746c59d46d5408bf8b1d0af5740fe1a6e1703fcb56b2953f0b965c740d256f")),
          //         URI.create("http://httpseed.bitcoin.schildbach.de/peers")
          // )
          // monetaryunit
          new HttpDiscovery.Details(
                  ECKey.fromPublicOnly(Utils.HEX.decode("027c3b3592eecbc91cf0e0bb0525903f0a913ad9d0f787b5417ae7ebcb2dfdf01f")),
                  URI.create("https://chain.muewallet.com/peers")
          )
        };

        addrSeeds = new int[] {

        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
