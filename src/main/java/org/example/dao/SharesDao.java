package org.example.dao;

import org.example.model.Shares;
import org.example.model.SharesType;

public interface SharesDao {
    void setValue(Integer price, Shares element);

    String getMinPriceAndNotZeroSizeByType(SharesType type);

    String getMaxPriceAndNotZeroSizeByType(SharesType type);

    String getByPrice(Integer price);
}
