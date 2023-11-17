package com.shield.entity;
import java.util.ArrayList;
import java.util.List;

import com.shield.enums.NotificationChannel;

public class Avenger {
    private String name;
    private int limit=0;
    private String personName;
    private String abilities;
    private List<String> assignedMissions=new ArrayList<>();
    private NotificationChannel notificationChannel;

    public Avenger(String name,int limit, String personName, String abilities, NotificationChannel channel) {
        this.name = name;
        this.limit = limit;
        this.personName = personName;
        this.abilities = abilities;
        this.notificationChannel = channel;
    }

    public String getName() {
        return name;
    }
    
    public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getPersonName() {
        return personName;
    }

    public String getAbilities() {
        return abilities;
    }

    public List<String> getAssignedMissions() {
        return assignedMissions;
    }

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public void assignMission(String missionName) {
        assignedMissions.add(missionName);
    }
}