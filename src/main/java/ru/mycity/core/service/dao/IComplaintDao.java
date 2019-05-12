package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.Complaint;

import java.sql.Timestamp;
import java.util.List;

public interface IComplaintDao {
    List<Complaint> getComplaints(String category, Timestamp creationTimeStart, Timestamp creationTimeEnd);
}
