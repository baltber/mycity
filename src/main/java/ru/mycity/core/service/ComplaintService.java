package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.complaint.ComplaintDto;
import ru.mycity.core.controller.dto.complaint.InsertComplaintResultDto;
import ru.mycity.core.controller.dto.ResultDto;
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

    public InsertComplaintResultDto addComplaint(ComplaintDto dto){
        return createInsertResult(complaintDao.insertComplaint(dto));

    }

    private InsertComplaintResultDto createInsertResult(long complaintId){
        InsertComplaintResultDto complaintResultDto = new InsertComplaintResultDto();
        ResultDto resultDto = new ResultDto();
        if (complaintId != 0L){
            resultDto.setStatusCode("200");
            resultDto.setMessage("OK");
            complaintResultDto.setComplaintId(complaintId);
            complaintResultDto.setResult(resultDto);
            return complaintResultDto;
        } else {
            resultDto.setStatusCode("500");
            resultDto.setMessage("ERROR");
            complaintResultDto.setResult(resultDto);
            return complaintResultDto;
        }
    }
}
