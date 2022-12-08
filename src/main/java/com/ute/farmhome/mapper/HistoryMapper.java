package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.entity.History;

public interface HistoryMapper {
    HistoryDTO map(History entity);
    History map(HistoryDTO historyDTO);
}
