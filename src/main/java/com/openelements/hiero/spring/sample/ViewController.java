package com.openelements.hiero.spring.sample;

import com.hedera.hashgraph.sdk.AccountId;
import com.openelements.hiero.base.HieroContext;
import com.openelements.hiero.base.data.Nft;
import com.openelements.hiero.base.data.Page;
import com.openelements.hiero.base.mirrornode.NftRepository;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private final HieroContext hieroContext;

    private final NftRepository nftRepository;

    @Autowired
    public ViewController(HieroContext hieroContext, NftRepository nftRepository) {
        this.hieroContext = hieroContext;
        this.nftRepository = nftRepository;
    }

    private List<Nft> getAllNfts(AccountId owner) throws Exception {
        return getAllNfts(nftRepository.findByOwner(owner), 10);
    }

    private List<Nft> getAllNfts(Page<Nft> nftPage, int maxDepth) throws Exception {
        final List<Nft> result = new ArrayList<>();
        result.addAll(nftPage.getData());
        if (nftPage.hasNext() && maxDepth > 0) {
            final Page<Nft> nextNftPage = nftPage.next();
            result.addAll(getAllNfts(nextNftPage, maxDepth - 1));
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(final Model model) throws Exception {
        AccountId owner = hieroContext.getOperatorAccount().accountId();
        List<NftModel> nfts = getAllNfts(owner).stream()
                .map(nft -> new NftModel(nft.tokenId().toString(),
                        Long.toString(nft.serial()),
                        new String(nft.metadata(), StandardCharsets.UTF_8)))
                .toList();
        model.addAttribute("nfts", nfts);
        return "index";
    }

    public record NftModel(String name, String serial, String metadata) {
    }
}
