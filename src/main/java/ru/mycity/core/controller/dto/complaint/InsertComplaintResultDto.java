package ru.mycity.core.controller.dto.complaint;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mycity.core.controller.dto.ResultDto;

public class InsertComplaintResultDto {
    private ResultDto result;
    @JsonProperty(value = "complaint_id")
    private long complaintId;

    public ResultDto getResult() {
        return result;
    }

    public void setResult(ResultDto result) {
        this.result = result;
    }

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }
}
