package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.EntryExitDto;
import com.resident.residentialmanagement.entity.EntryExit;
import org.springframework.data.domain.Page;

public interface IEntryExitService {
    Page<EntryExit> getAll(int pageNumber, int pageSize);

    EntryExit getById(int id);

    int create(EntryExitDto entryExitDto);

    int update(int id, EntryExitDto entryExitDto);

    Boolean delete(int id);
}
