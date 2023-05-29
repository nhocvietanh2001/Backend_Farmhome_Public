package com.ute.farmhome.service;

import com.ute.farmhome.entity.StatusProduct;

public interface StatusService {
    StatusProduct getPendingStatusProduct();
    StatusProduct getDealingStatusProduct();
    StatusProduct getCanceledStatusProduct();
}
