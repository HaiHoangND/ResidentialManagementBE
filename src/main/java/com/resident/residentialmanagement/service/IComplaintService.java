package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.ComplaintDto;
import com.resident.residentialmanagement.entity.Complaint;
import org.springframework.data.domain.Page;

public interface IComplaintService {
    Page<Complaint> getAll(int pageNumber, int pageSize);

    Complaint getById(int id);

    int create(ComplaintDto complaintDto);

    int update(int id, ComplaintDto complaintDto);

    Boolean delete(int id);
}
