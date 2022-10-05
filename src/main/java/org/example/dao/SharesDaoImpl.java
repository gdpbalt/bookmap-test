package org.example.dao;

import java.util.Map;
import java.util.Optional;
import org.example.db.SharesStorage;
import org.example.model.Cost;
import org.example.model.Shares;
import org.example.model.SharesType;

public class SharesDaoImpl implements SharesDao {
    @Override
    public void setValue(Integer price, Shares element) {
        SharesStorage.shares.put(price, element);
    }

    @Override
    public Shares getValue(Integer price) {
        return SharesStorage.shares.get(price);
    }

    @Override
    public Optional<Cost> getMinPriceAndNotZeroSizeByType(SharesType type) {
        for (Map.Entry<Integer, Shares> entry : SharesStorage.shares.entrySet()) {
            if (entry.getValue().getSize() > 0 && entry.getValue().getType().equals(type)) {
                return Optional.of(new Cost(entry.getKey(), entry.getValue().getSize()));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cost> getMaxPriceAndNotZeroSizeByType(SharesType type) {
        Optional<Cost> output = Optional.empty();
        for (Map.Entry<Integer, Shares> entry : SharesStorage.shares.entrySet()) {
            if (entry.getValue().getSize() > 0 && entry.getValue().getType().equals(type)) {
                output = Optional.of(new Cost(entry.getKey(), entry.getValue().getSize()));
            }
        }
        return output;
    }

    @Override
    public Optional<Integer> getSizeByPrice(Integer price) {
        if (SharesStorage.shares.containsKey(price)) {
            return Optional.of(SharesStorage.shares.get(price).getSize());
        }
        return Optional.empty();
    }
}
