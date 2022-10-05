package org.example.dao;

import java.util.Optional;
import org.example.model.Cost;
import org.example.model.Shares;
import org.example.model.SharesType;

public interface SharesDao {
    void setValue(Integer price, Shares element);

    Shares getValue(Integer price);

    Optional<Cost> getMinPriceAndNotZeroSizeByType(SharesType type);

    Optional<Cost> getMaxPriceAndNotZeroSizeByType(SharesType type);

    Integer getSizeByPrice(Integer price);
}
