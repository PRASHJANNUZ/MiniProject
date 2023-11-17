package com.shield.entity;

import java.util.ArrayList; 
import java.util.List;

import com.shield.enums.MissionStatus;

public class Mission {
    private String name;
    private String details;
    private MissionStatus status;
    private List<String> assignedAvengers=new ArrayList<>();

    public Mission(String name, String details) {
        this.name = name;
        this.details = details;
        this.status = MissionStatus.ASSIGNED;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public List<String> getAssignedAvengers() {
        return assignedAvengers;
    }

    public void assignAvenger(String avengerName) {
        assignedAvengers.add(avengerName);
    }

    public void completeMission() {
        status = MissionStatus.COMPLETED;
    }
}
