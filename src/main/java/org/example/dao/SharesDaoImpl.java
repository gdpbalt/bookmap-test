package org.example.dao;

import java.util.Map;
import org.example.db.SharesStorage;
import org.example.model.Shares;
import org.example.model.SharesType;

public class SharesDaoImpl implements SharesDao {

    @Override
    public void setValue(Integer price, Shares element) {
        SharesStorage.shares.put(price, element);
    }

    @Override
    public String getMinPriceAndNotZeroSizeByType(SharesType type) {
        for (Map.Entry<Integer, Shares> entry : SharesStorage.shares.entrySet()) {
            if (entry.getValue().getSize() > 0 && entry.getValue().getType().equals(type)) {
                return entry.getKey().toString() + "," + entry.getValue().getSize();
            }
        }
        return "";
    }

    @Override
    public String getMaxPriceAndNotZeroSizeByType(SharesType type) {
        String output = "";
        for (Map.Entry<Integer, Shares> entry : SharesStorage.shares.entrySet()) {
            if (entry.getValue().getSize() > 0 && entry.getValue().getType().equals(type)) {
                output = entry.getKey().toString() + "," + entry.getValue().getSize();
            }
        }
        return output;
    }

    @Override
    public String getByPrice(Integer price) {
        if (SharesStorage.shares.containsKey(price)) {
            return String.valueOf(SharesStorage.shares.get(price).getSize());
        }
        return "";
    }
}
