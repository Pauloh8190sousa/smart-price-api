package com.phsousa.smart_price_api.service;
import com.phsousa.smart_price_api.entity.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    Store create(Store store);

    List<Store> findAll();

    Store findById(UUID id);

    Store update (UUID id, Store updated);

    void delete(UUID id);


}
