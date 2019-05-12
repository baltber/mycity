package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.ComplaintDto;
import ru.mycity.core.service.dao.IComplaintDao;
import ru.mycity.core.service.dao.model.Complaint;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    @Autowired
    private IComplaintDao complaintDao;

    public List<ComplaintDto> getComplaints(String category){
        return complaintDao.getComplaints(category, null, null)
                .stream()
                .map(Complaint::toDto)
                .collect(Collectors.toList());
    }
}
